package com.hugoangeles.freebooks.ui.adapter;


import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hugoangeles.freebooks.R;
import com.hugoangeles.freebooks.model.Book;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hugo on 26/11/15.
 */
public class BooksResultsAdapter extends RecyclerView.Adapter<BooksResultsAdapter.ViewHolder> {

    private List<Book> mBooks;
    Context mContext;
    ItemClickListener mItemClickListener;

    public BooksResultsAdapter(Context context){
        mBooks = new ArrayList<Book>();
        mContext = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.book_item, parent, false);

        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Book book = mBooks.get(i);
        viewHolder.setTitle(book.getTitle());
        viewHolder.setImage(book.getImage());
        viewHolder.setDescription(book.getDescription());
    }

    @Override
    public int getItemCount() {
        return mBooks.size();
    }

    public void clear() {
        mBooks.clear();
        notifyDataSetChanged();
    }

    public void replace(List<Book> books) {
        mBooks = books;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(ItemClickListener listener) {
        mItemClickListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public int IMG_SIZE_DP = 100;

        public int IMG_SIZE_PX;

        public TextView mtitle;
        public ImageView mLogo;
        public TextView mDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            mtitle = (TextView) itemView.findViewById(R.id.item_book_title);
            mLogo = (ImageView) itemView.findViewById(R.id.item_book_image);
            mDescription = (TextView) itemView.findViewById(R.id.item_book_description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener != null){
                        mItemClickListener.onItemClicked(mBooks.get(getLayoutPosition()));
                    }
                }
            });

            setupDimensions();
        }

        private void setupDimensions() {
            DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
            IMG_SIZE_PX = IMG_SIZE_DP * (metrics.densityDpi / 160);
        }

        public void setTitle(String title) {
            mtitle.setText(title);
        }

        public void setImage(String url){
            Picasso.with(mContext)
                    .load(url)
                    .placeholder(R.drawable.book)
                    .resize(IMG_SIZE_PX, IMG_SIZE_PX)
                    .into(mLogo);
        }

        public void setDescription(String description) {
            mDescription.setText(description);
        }
    }

    public interface ItemClickListener {
        void onItemClicked(Book book);
    }
}
