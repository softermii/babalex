package com.shakenbeer.babalex;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shakenbeer.babalex.data.Category;

import java.util.ArrayList;
import java.util.List;


class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private static final String TAG = "CategoryAdapter";
    private int selected;
    private Context context;
    private List<Category> items = new ArrayList<>();

    private final Typeface gillSansLight;

    CategoryAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.items = categories;
        gillSansLight = Typeface.createFromAsset(context.getAssets(), "GillSansLight.ttf");
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        Category category = items.get(position);
        holder.categoryTextView.setText(category.getName());
        if (position == selected) {
            holder.categoryTextView.setTypeface(gillSansLight, Typeface.BOLD);
            holder.categoryTextView.setTextSize(24);
            holder.categoryTextView.setTextColor(Color.RED);
            holder.categoryTextView.setPaintFlags(holder.categoryTextView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        } else {
            holder.categoryTextView.setTypeface(gillSansLight, Typeface.NORMAL);
            holder.categoryTextView.setTextSize(18);
            holder.categoryTextView.setTextColor(Color.BLACK);
            holder.categoryTextView.setPaintFlags(0);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    void setSelected(int activePosition) {
        // update views only on selected range
        if (activePosition < selected) {
            notifyItemRangeChanged(selected - 1, 2);
        } else {
            notifyItemRangeChanged(selected, 2);
        }
        selected = activePosition;
    }


    static class CategoryViewHolder extends RecyclerView.ViewHolder {

        TextView categoryTextView;

        CategoryViewHolder(View itemView) {
            super(itemView);
            categoryTextView = (TextView) itemView.findViewById(R.id.category_textView);
        }
    }
}
