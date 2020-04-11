package com.hvasoftware.parsehtmlusejsoup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdapterData extends RecyclerView.Adapter<AdapterData.MyViewHolder> {
    private Context mContext;
    private List<Quote> quoteList = new ArrayList<>();

    public AdapterData(Context mContext) {
        this.mContext = mContext;
    }

    public void setQuoteList(List<Quote> quoteList) {
        this.quoteList = quoteList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quote, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Quote quote = quoteList.get(position);
        holder.tvCategory.setText(quote.getCategory());
        holder.tvContent.setText(quote.getContent());
        holder.tvAuthor.setText(quote.getAuthor());
    }

    @Override
    public int getItemCount() {
        return quoteList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCategory;
        private TextView tvContent;
        private TextView tvAuthor;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCategory = itemView.findViewById(R.id.tvCategory);
            tvContent = itemView.findViewById(R.id.tvContent);
            tvAuthor = itemView.findViewById(R.id.tvAuthor);
        }
    }
}
