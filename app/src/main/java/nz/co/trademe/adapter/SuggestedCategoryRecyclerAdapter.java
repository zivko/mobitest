package nz.co.trademe.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import java.util.List;
import nz.co.trademe.R;
import nz.co.trademe.activity.HomeActivity;
import nz.co.trademe.fragment.ListingFragment;
import nz.co.trademe.wrapper.model.SuggestedCategory;

public class SuggestedCategoryRecyclerAdapter extends RecyclerView.Adapter<SuggestedCategoryRecyclerAdapter.ListingViewHolder>  {

    private List<SuggestedCategory> suggestedCategoriesItemList;
    private Context mContext;

    public class ListingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        protected TextView textView;

        public ListingViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            this.textView = (TextView) view.findViewById(R.id.suggestedCategoryTitle);
        }

        @Override
        public void onClick(View v) {

            SuggestedCategory categoryItem = suggestedCategoriesItemList.get(getAdapterPosition());
            FragmentManager fragmentManager = ((HomeActivity) mContext).getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            ListingFragment fragment = ListingFragment.newInstance(categoryItem.getName(), categoryItem.getMcat());
            fragmentTransaction.replace(R.id.container, fragment).addToBackStack(fragment.getClassTag()).commit();
        }
    }

    public SuggestedCategoryRecyclerAdapter(Context context, List<SuggestedCategory> suggestedCategoriesItemList) {
        this.suggestedCategoriesItemList = suggestedCategoriesItemList;
        this.mContext = context;
    }

    @Override
    public void onBindViewHolder(ListingViewHolder listingViewHolder, int i) {

        SuggestedCategory suggestedCategory = suggestedCategoriesItemList.get(i);
        listingViewHolder.textView.setText(suggestedCategory.getName() + " (" + suggestedCategory.getCount() + ") >");

    }

    @Override
    public ListingViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.suggested_category_list_row, null);
        ListingViewHolder viewHolder = new ListingViewHolder(view);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return (null != suggestedCategoriesItemList ? suggestedCategoriesItemList.size() : 0);
    }

}
