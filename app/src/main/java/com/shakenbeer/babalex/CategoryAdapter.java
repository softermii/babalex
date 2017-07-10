package com.shakenbeer.babalex;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shakenbeer.babalex.data.Category;

import java.util.ArrayList;
import java.util.List;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

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
        Category category = items.get(position);
        holder.categoryTextView.setText(category.getName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {

        TextView categoryTextView;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            categoryTextView = (TextView) itemView.findViewById(R.id.category_textView);
        }
    }
}
