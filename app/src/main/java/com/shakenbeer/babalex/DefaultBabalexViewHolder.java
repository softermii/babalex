package com.shakenbeer.babalex;


import android.view.View;

public class DefaultBabalexViewHolder extends BabalexViewHolder {

    private SquareImageView image;

    public DefaultBabalexViewHolder(View itemView) {
        super(itemView);
        image = (SquareImageView) itemView.findViewById(R.id.image);
    }
}
