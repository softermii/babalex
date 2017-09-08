package com.shakenbeer.babalex.cart;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.shakenbeer.babalex.R;
import com.shakenbeer.babalex.SquareImageView;
import com.shakenbeer.babalex.common.Utils;
import com.shakenbeer.babalex.data.BabalexCartItem;

import java.util.List;

/**
 * Created by onos on 08.09.17.
 */

class CartOrderAdapter extends RecyclerView.Adapter<CartOrderAdapter.BabalexCartItemHolder> {

    private Context context;
    private List<BabalexCartItem> orderList;

    public CartOrderAdapter(Context context, List<BabalexCartItem> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @Override
    public BabalexCartItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new BabalexCartItemHolder(view);
    }

    @Override
    public void onBindViewHolder(BabalexCartItemHolder holder, int position) {
        BabalexCartItem currentItem = orderList.get(position);
        holder.image.setImageResource(Utils.getDrawableResIdByImageTitle(context,
                currentItem.getBabalexItem().getImageName()));
        holder.itemTitle.setText(currentItem.getBabalexItem().getTitle());
        holder.itemAmount.setText(Integer.toString(currentItem.getCount()));
        holder.itemPrice.setText(Double.toString(currentItem.getBabalexItem().getPrice()));


        // TODO make it intractable

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    void setOrderList(List<BabalexCartItem> orderList) {
        this.orderList = orderList;
        notifyDataSetChanged();
    }

    class BabalexCartItemHolder extends RecyclerView.ViewHolder {

        SquareImageView image;
        TextView dollarSign;
        TextView itemAmount;
        TextView itemPrice;
        TextView itemTitle;
        ImageButton addItemCount;
        ImageButton removeItemCount;

        BabalexCartItemHolder(View itemView) {
            super(itemView);
            image = (SquareImageView) itemView.findViewById(R.id.item_image);
            dollarSign = (TextView) itemView.findViewById(R.id.dollar_sign);
            itemAmount = (TextView) itemView.findViewById(R.id.item_amount);
            itemPrice = (TextView) itemView.findViewById(R.id.item_price);
            itemTitle = (TextView) itemView.findViewById(R.id.item_title);
            addItemCount = (ImageButton) itemView.findViewById(R.id.add_item_button);
            removeItemCount = (ImageButton) itemView.findViewById(R.id.remove_item_button);

            itemTitle.setTypeface(Typeface.createFromAsset(context.getAssets(), "BradHitc.ttf"), Typeface.BOLD);

        }
    }

}
