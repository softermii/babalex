package com.shakenbeer.babalex;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shakenbeer.babalex.data.Category;

import java.util.ArrayList;
import java.util.List;


class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private static final String TAG = "CategoryAdapter";
    private int selected = 0;
    private List<Category> items = new ArrayList<>();

    CategoryAdapter(List<Category> categories) {
        this.items = categories;
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
            holder.categoryTextView.setTextColor(Color.RED);
        } else {
            holder.categoryTextView.setTextColor(Color.BLACK);
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
//        notifyDataSetChanged();
    }


    static class CategoryViewHolder extends RecyclerView.ViewHolder {

        TextView categoryTextView;

        CategoryViewHolder(View itemView) {
            super(itemView);
            categoryTextView = (TextView) itemView.findViewById(R.id.category_textView);
        }
    }
}
