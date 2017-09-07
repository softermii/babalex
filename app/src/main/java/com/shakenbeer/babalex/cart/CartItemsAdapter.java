package com.shakenbeer.babalex.cart;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.shakenbeer.babalex.R;
import com.shakenbeer.babalex.SquareImageView;

/**
 * Created by onos on 07.09.17.
 */

class CartItemsAdapter extends RecyclerView.Adapter<CartItemsAdapter.ChosenSweetyViewHolder> {



    @Override
    public ChosenSweetyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_cart, parent, false);
        return new ChosenSweetyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChosenSweetyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ChosenSweetyViewHolder extends RecyclerView.ViewHolder {

        SquareImageView itemSquareImageView;
        TextView dollarTextView;
        TextView amountTextView;
        TextView priceTextView;
        TextView titleTextView;
        ImageButton addItemAmountButton;
        ImageButton removeItemAmountButton;


        ChosenSweetyViewHolder(View itemView) {
            super(itemView);

            itemSquareImageView = (SquareImageView) itemView.findViewById(R.id.item_image);
            dollarTextView = (TextView) itemView.findViewById(R.id.dollar_sign);
            amountTextView = (TextView) itemView.findViewById(R.id.item_amount);
            priceTextView = (TextView) itemView.findViewById(R.id.item_price);
            titleTextView = (TextView) itemView.findViewById(R.id.item_title);
            addItemAmountButton = (ImageButton) itemView.findViewById(R.id.add_item_button);
            removeItemAmountButton = (ImageButton) itemView.findViewById(R.id.remove_item_button);
        }
    }
}
