package com.shakenbeer.babalex;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shakenbeer.babalex.data.Category;

import java.util.ArrayList;
import java.util.List;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private static final int count = 5;

    private int shift = 0;

    private int selected = 0;

    private List<Category> items = new ArrayList<>();

    public void setItems(List<Category> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        int index = position + shift;
        Category category = items.get(index);
        holder.categoryTextView.setText(category.getName());
        if (index == selected) {
            holder.categoryTextView.setTextColor(Color.RED);
        } else {
            holder.categoryTextView.setTextColor(Color.BLACK);
        }
    }

    @Override
    public int getItemCount() {
        return items.size() < count ? items.size() : count;
    }

    void setSelected(int categoryPos) {
        selected = categoryPos;
        updateShift();
        notifyDataSetChanged();
    }

    private void updateShift() {
        if (items.size() > 5) {
            if (selected < 2) {
                shift = 0;
            } else if (selected < items.size() - 2) {
                shift = selected - 2;
            } else {
                shift = items.size() - 5;
            }
        }
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {

        TextView categoryTextView;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            categoryTextView = (TextView) itemView.findViewById(R.id.category_textView);
        }
    }
}
