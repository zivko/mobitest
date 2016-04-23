package nz.co.trademe.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import nz.co.trademe.R;
import nz.co.trademe.activity.HomeActivity;
import nz.co.trademe.fragment.ListingFragment;
import nz.co.trademe.wrapper.model.Category;

public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryRecyclerAdapter.CategoryViewHolder> {
    private ArrayList<Category> categoryItemList;
    private Context mContext;


    public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        protected TextView textView;

        public CategoryViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            this.textView = (TextView) view.findViewById(R.id.categoryTitle);
        }

        @Override
        public void onClick(View view) {

            Category categoryItem = categoryItemList.get(getAdapterPosition());
            FragmentManager fragmentManager = ((HomeActivity) mContext).getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            ListingFragment fragment = ListingFragment.newInstance(categoryItem.getName(), categoryItem.getNumber());
            fragmentTransaction.replace(R.id.container, fragment).addToBackStack(fragment.getClassTag()).commit();

        }
    }

    public CategoryRecyclerAdapter(Context context, ArrayList<Category> categoryItemList) {
        this.categoryItemList = categoryItemList;
        this.mContext = context;

    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_list_row, null);
        CategoryViewHolder viewHolder = new CategoryViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder categoryViewHolder, int i) {

        Category categoryItem = categoryItemList.get(i);
        categoryViewHolder.textView.setText(categoryItem.getName());
    }

    @Override
    public int getItemCount() {
        return (null != categoryItemList ? categoryItemList.size() : 0);
    }

}