package nz.co.trademe.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import nz.co.trademe.R;
import nz.co.trademe.adapter.SuggestedCategoryRecyclerAdapter;
import nz.co.trademe.adapter.ListingRecyclerAdapter;
import nz.co.trademe.widget.DividerItemDecoration;
import nz.co.trademe.wrapper.ServiceGenerator;
import nz.co.trademe.wrapper.api.SearchApi;
import nz.co.trademe.wrapper.model.Listing;
import nz.co.trademe.wrapper.model.SearchResponse;
import nz.co.trademe.wrapper.model.SuggestedCategory;
import nz.co.trademe.wrapper.network.Credentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListingFragment extends Fragment {

    public static final String TAG = CategoryFragment.class.getName();
    public static final Integer PER_PAGE = 20;

    private String categoryName;
    private String categoryNumber;
    private RecyclerView suggestedCategoryRecyclerView;
    private RecyclerView listingRecyclerView;
    private SuggestedCategoryRecyclerAdapter suggestedCategoryRecyclerAdapter;
    private ListingRecyclerAdapter listingRecyclerAdapter;


    public static ListingFragment newInstance(String categoryName, String categoryNumber ) {
        ListingFragment listingFragment = new ListingFragment();
        Bundle args = new Bundle();
        args.putString("categoryName", categoryName);
        args.putString("categoryNumber", categoryNumber);
        listingFragment.setArguments(args);

        return listingFragment;
    }

    public String getClassTag() {
        return TAG;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        categoryName = getArguments().getString("categoryName");
        categoryNumber = getArguments().getString("categoryNumber");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, final Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_listing, parent, false);

        suggestedCategoryRecyclerView  = (RecyclerView) rootView.findViewById(R.id.suggested_category_recycler_view);
        suggestedCategoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);
        suggestedCategoryRecyclerView.addItemDecoration(itemDecoration);
        listingRecyclerView = (RecyclerView) rootView.findViewById(R.id.listing_recycler_view);
        listingRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        SearchApi searchApi = ServiceGenerator.createService(SearchApi.class, new Credentials());
        Call<SearchResponse> call = searchApi.searchGeneral(categoryNumber,PER_PAGE);

        call.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {

                List<SuggestedCategory> subcategories = response.body().getSuggestedCategories();
                List<Listing> listing = response.body().getListings();

                suggestedCategoryRecyclerAdapter = new SuggestedCategoryRecyclerAdapter(getActivity(),subcategories);
                listingRecyclerAdapter = new ListingRecyclerAdapter(getActivity(),listing);
                suggestedCategoryRecyclerView.setAdapter(suggestedCategoryRecyclerAdapter);
                listingRecyclerView.setAdapter(listingRecyclerAdapter);
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {

            }
        });

        return rootView;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView headingData = (TextView) view.findViewById(R.id.headingCat);
        headingData.setText("<<" + categoryName);
        headingData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    getFragmentManager().popBackStackImmediate();
            }
        });
    }

}
