package nz.co.trademe.wrapper;

import java.io.File;
import java.io.IOException;
import nz.co.trademe.TradeMeApp;
import nz.co.trademe.util.NetworkUtil;
import nz.co.trademe.wrapper.network.Credentials;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    public static final String API_BASE_URL = "https://api.tmsandbox.co.nz";
    public static final String CACHE_FILE_NAME = "httpcache";
    public static final long SIZE_OF_CACHE = 10 * 1024 * 1024;

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null);
    }

    public static <S> S createService(Class<S> serviceClass, final Credentials credentials) {
        if (credentials != null) {

            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request original = chain.request();

                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization",
                                    "OAuth oauth_consumer_key='" + credentials.getConsumerKey() + "', " +
                                            "oauth_signature_method='PLAINTEXT'," +
                                            "oauth_signature='" + credentials.getConsumerSecret() + "&'"
                            )
                            .method(original.method(), original.body());

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });

        }

        Cache cache = new Cache(new File(TradeMeApp.getContext().getCacheDir(),CACHE_FILE_NAME), SIZE_OF_CACHE);
        httpClient.cache(cache);
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request request = chain.request();
                if (NetworkUtil.isConnected(TradeMeApp.getContext())) {
                    request = request.newBuilder().header("Cache-Control", "public, max-age=" + 60).build();
                } else {
                    request = request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build();
                }
                return chain.proceed(request);
            }
        }).build();


        OkHttpClient client = httpClient.build();
        Retrofit retrofit = builder.client(client).build();

        return retrofit.create(serviceClass);

    }
}
