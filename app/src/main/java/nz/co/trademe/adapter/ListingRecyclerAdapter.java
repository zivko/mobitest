package nz.co.trademe.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;
import nz.co.trademe.R;
import nz.co.trademe.wrapper.model.Listing;

public class ListingRecyclerAdapter extends RecyclerView.Adapter<ListingRecyclerAdapter.ListingViewHolders> {

    private List<Listing> itemList;
    private Context context;

    public class ListingViewHolders extends RecyclerView.ViewHolder   {

        public TextView countryName;
        public ImageView countryPhoto;

        public ListingViewHolders(View itemView) {
            super(itemView);
            countryName = (TextView) itemView.findViewById(R.id.ListingTitle);
            countryPhoto = (ImageView) itemView.findViewById(R.id.listingPhoto);
        }
    }

    public ListingRecyclerAdapter(Context context, List<Listing> itemList) {
        this.itemList = itemList;
        this.context = context;

    }

    @Override
    public ListingViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listing_list_row, null);
        ListingViewHolders rcv = new ListingViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(ListingViewHolders holder, int position) {

        holder.countryName.setText(itemList.get(position).getTitle());

        String pictureHref = itemList.get(position).getPictureHref();
        String tqImageUrl;

        if (pictureHref != null) {
            tqImageUrl = pictureHref.replace("thumb", "tq");
        }  else {
            tqImageUrl = null;
        }

        Picasso.with(context)
                .load(tqImageUrl)
                .placeholder(R.drawable.placeholder_nophoto_tablet)
                .into(holder.countryPhoto);
    }

    @Override
    public int getItemCount() {
        return (null != itemList ? itemList.size() : 0);
    }
}
