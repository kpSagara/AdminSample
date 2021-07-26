package com.example.lpuadmin;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter  extends RecyclerView.Adapter<NewsAdapter.ImageViewHolder> {

    private Context mContext;
    private List<NewsUpload> mUploads;
    private NewsAdapter.OnItemClickListener3 mListener;

    public NewsAdapter(Context context, List<NewsUpload> uploads) {
        mContext = context;
        mUploads = uploads;
    }

    @Override
    public NewsAdapter.ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.news_item, parent, false);
        return new NewsAdapter.ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ImageViewHolder holder, int position) {
        NewsUpload uploadCurrent = mUploads.get(position);
        holder.textViewName.setText(uploadCurrent.getName());
        holder.newsDate.setText(uploadCurrent.getDate());
        holder.newsDesc.setText(uploadCurrent.getNewsDesc());
        Picasso.get().load(uploadCurrent.getImageUrl())
                .placeholder(R.mipmap.lpuad)
                .fit()
                .centerCrop()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

        public TextView textViewName;
        public ImageView imageView;
        public TextView newsDate;
        public TextView newsDesc;


        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.text_view_name3);
            imageView = itemView.findViewById(R.id.image_view_upload);
            newsDate = itemView.findViewById(R.id.news_date);
            newsDesc = itemView.findViewById(R.id.event_desc3);

            itemView.setOnClickListener(this);

            itemView.setOnCreateContextMenuListener(this);

        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    mListener.onItemClick(position);
                }
            }
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Select Action");
            MenuItem Update = menu.add(Menu.NONE, 1, 1, "Update Item");
            MenuItem Delete = menu.add(Menu.NONE, 2, 2, "Delete Item");

            Update.setOnMenuItemClickListener(this);
            Delete.setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            if (mListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    switch (item.getItemId()){
                        case 1:
                            mListener.onUpdateClick(position);
                            return true;
                        case 2:
                            mListener.onDeleteClick(position);
                            return true;
                    }
                }
            }
            return false;
        }
    }

    public interface OnItemClickListener3 {
        void onItemClick(int position);

        void onUpdateClick(int position);

        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(NewsAdapter.OnItemClickListener3 listener) {
        mListener = listener;


    }
}