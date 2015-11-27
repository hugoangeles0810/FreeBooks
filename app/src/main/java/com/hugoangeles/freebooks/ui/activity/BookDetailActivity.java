package com.hugoangeles.freebooks.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hugoangeles.freebooks.R;
import com.hugoangeles.freebooks.model.Book;
import com.hugoangeles.freebooks.presenter.BookPresenter;
import com.hugoangeles.freebooks.view.BookMvpView;
import com.squareup.picasso.Picasso;

public class BookDetailActivity extends ActionBarActivity implements BookMvpView
{

    private View mLoader;
    private TextView mTitle;
    private ImageView mLogo;
    private TextView mAuthor;
    private TextView mDescription;
    private BookPresenter bookPresenter;
    private Button mBtnDownload;
    private long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        mLoader = findViewById(R.id.loader);
        mTitle = (TextView) findViewById(R.id.book_title);
        mLogo = (ImageView) findViewById(R.id.book_image);
        mAuthor = (TextView) findViewById(R.id.book_author);
        mDescription = (TextView) findViewById(R.id.book_description);
        mBtnDownload = (Button) findViewById(R.id.book_btn_download);

        bookPresenter = new BookPresenter();

        if (getIntent() != null) {
            id = getIntent().getLongExtra("id", 0);
        }

        mBtnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = (String) v.getTag();
                if (url != null)
                    bookPresenter.downloadBook(url);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        bookPresenter.attachedView(this);
        bookPresenter.loadBook(id);
    }

    @Override
    protected void onPause() {
        bookPresenter.detachView();
        super.onPause();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        id = savedInstanceState.getLong("id");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putLong("id", id);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_book_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void renderBook(Book book) {
        if (book != null) {
            getSupportActionBar().setTitle(book.getTitle());
            mTitle.setText(book.getTitle());
            Picasso.with(this)
                    .load(book.getImage())
                    .placeholder(R.drawable.book)
                    .into(mLogo);;
            mAuthor.setText(book.getAuthor());
            mDescription.setText(book.getDescription());
            mBtnDownload.setTag(book.getDownload());
        }
    }

    @Override
    public void showLoader() {
        mLoader.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoader() {
        mLoader.setVisibility(View.GONE);
    }

    @Override
    public void downloadBook(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    @Override
    public Context getContext() {
        return this;
    }
}
