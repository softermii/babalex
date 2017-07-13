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
import java.util.LinkedList;
import java.util.List;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private static final int count = 5;

    private int shift = 0;

    private int selected = 0;

    private List<Category> items = new ArrayList<>();

    private List<Category> toDisplay = new LinkedList<>();

    public void setItems(List<Category> items) {
        this.items = items;
        for (int i = 0; i < getItemCount(); i++) {
            toDisplay.add(items.get(i));
        }
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
        Category category = toDisplay.get(position);
        holder.categoryTextView.setText(category.getName());
        if (position == selected - shift) {
            holder.categoryTextView.setTextColor(Color.RED);
        } else {
            holder.categoryTextView.setTextColor(Color.BLACK);
        }
    }

    @Override
    public int getItemCount() {
        return items.size() < count ? items.size() : count;
    }

    void setSelected(int posInItems) {
        int prevSelected = selected;
        selected = posInItems;
        if (items.size() > 5) {
            if (selected > prevSelected && selected > 2 && selected < items.size() - 2) {
                scrollForward();
            } else if (selected < prevSelected && selected > 1 && selected < items.size() - 3) {
                scrollBackward();
            }
        }
        notifyItemChanged(selected - shift - 1);
        notifyItemChanged(selected - shift);
        notifyItemChanged(selected - shift + 1);
    }

    private void scrollBackward() {
        shift--;
        toDisplay.remove(4);
        notifyItemRemoved(4);
        toDisplay.add(0, items.get(selected - 2));
        notifyItemInserted(0);
    }

    private void scrollForward() {
        shift++;
        toDisplay.remove(0);
        notifyItemRemoved(0);
        toDisplay.add(items.get(selected + 2));
        notifyItemInserted(4);
    }


    public static class CategoryViewHolder extends RecyclerView.ViewHolder {

        TextView categoryTextView;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            categoryTextView = (TextView) itemView.findViewById(R.id.category_textView);
        }
    }
}
