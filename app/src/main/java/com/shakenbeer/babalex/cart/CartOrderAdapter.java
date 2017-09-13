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

;

/**
 * Created by onos on 08.09.17.
 */

class CartOrderAdapter extends RecyclerView.Adapter<CartOrderAdapter.BabalexCartItemHolder> {

    private Context context;
    private CartOrderAdapter.OnOrderChangedListener orderChangedListener;
    private List<BabalexCartItem> orderList;
    private Typeface titleTypeface;
    private Typeface textRegularLightTypeface;
    private Typeface textRegularTypeface;

    CartOrderAdapter(Context context, List<BabalexCartItem> orderList,
                     CartOrderAdapter.OnOrderChangedListener orderChangedListener) {
        this.context = context;
        this.orderList = orderList;
        this.orderChangedListener = orderChangedListener;
        titleTypeface = Typeface.createFromAsset(context.getAssets(), "BradHitc.ttf");
        textRegularLightTypeface = Typeface.createFromAsset(context.getAssets(), "GillSansLight.ttf");
        textRegularTypeface = Typeface.createFromAsset(context.getAssets(), "GillSans.ttf");
    }

    @Override
    public BabalexCartItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new BabalexCartItemHolder(view);
    }

    @Override
    public void onBindViewHolder(final BabalexCartItemHolder holder, int position) {
        BabalexCartItem currentItem = orderList.get(position);
        holder.image.setImageResource(Utils.getDrawableResIdByImageTitle(context,
                currentItem.getBabalexItem().getImageName()));
        holder.itemTitle.setText(currentItem.getBabalexItem().getTitle());
        holder.itemAmount.setText(Integer.toString(currentItem.getCount()));
        holder.itemPrice.setText(Double.toString(currentItem.getBabalexItem().getPrice()));

        holder.addItemCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderChangedListener.onItemAdded(orderList.get(holder.getAdapterPosition()).getId());
            }
        });

        holder.removeItemCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderChangedListener.onItemRemoved(orderList.get(holder.getAdapterPosition()).getId());
            }
        });

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

            itemTitle.setTypeface(titleTypeface, Typeface.BOLD);
            dollarSign.setTypeface(textRegularLightTypeface);
            itemPrice.setTypeface(textRegularTypeface, Typeface.BOLD);
            itemAmount.setTypeface(textRegularTypeface, Typeface.BOLD);


        }
    }

    interface OnOrderChangedListener {
        void onItemAdded(int itemId);

        void onItemRemoved(int itemId);
    }

}
