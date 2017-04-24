package com.shixels.wemeet.adapter;

import android.support.v7.widget.RecyclerView;

import com.shixels.wemeet.model.Item;

import java.util.ArrayList;

public abstract class RecyclerViewAdapter<VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH>
{
    private ArrayList<Item> someData;

    public void swap(final ArrayList<Item> someData)
    {
        this.someData = someData;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount()
    {
        return this.someData != null
                ? this.someData.size()
                : 0;
    }

    public Item getItem(final int position)
    {
        if (this.someData != null )
        {
            this.someData.get(position);
        }

        return this.someData.get(position);
    }

    public ArrayList<Item> getArrayList()
    {
        return this.someData;
    }

    @Override
    public final void onBindViewHolder(final VH holder, final int position)
    {
        final Item item = this.getItem(position);
        this.onBindViewHolder(holder, item);
    }

    public abstract void onBindViewHolder(final VH holder, final Item item);
}