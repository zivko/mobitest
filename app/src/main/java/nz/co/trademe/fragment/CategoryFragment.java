package nz.co.trademe.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import nz.co.trademe.R;
import nz.co.trademe.widget.DividerItemDecoration;
import nz.co.trademe.adapter.CategoryRecyclerAdapter;
import nz.co.trademe.wrapper.ServiceGenerator;
import nz.co.trademe.wrapper.api.CatalogueApi;
import nz.co.trademe.wrapper.model.Category;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryFragment extends Fragment {

    public static final String TAG = CategoryFragment.class.getName();
    private RecyclerView categoryRecyclerView;
    private CategoryRecyclerAdapter adapter;

    public String getClassTag() {
        return TAG;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_category, container, false);


        categoryRecyclerView  = (RecyclerView) rootView.findViewById(R.id.category_recycler_view);
        categoryRecyclerView.setHasFixedSize(true);
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);
        categoryRecyclerView.addItemDecoration(itemDecoration);


        CatalogueApi catalogueApi = ServiceGenerator.createService(CatalogueApi.class);
        Call<Category> call = catalogueApi.retrieveCategories();

        call.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {

                if(response.isSuccessful()) {

                    ArrayList<Category> subcategories = response.body().getSubcategories();
                    adapter = new CategoryRecyclerAdapter(getActivity(),subcategories);
                    categoryRecyclerView.setAdapter(adapter);
                }
            }
            @Override
            public void onFailure(Call<Category> call, Throwable t) {

            }
        });

        return rootView;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}
