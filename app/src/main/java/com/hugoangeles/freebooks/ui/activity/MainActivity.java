package com.hugoangeles.freebooks.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.hugoangeles.freebooks.R;
import com.hugoangeles.freebooks.config.Constantes;
import com.hugoangeles.freebooks.model.Book;
import com.hugoangeles.freebooks.presenter.BooksSearchSearchPresenter;
import com.hugoangeles.freebooks.ui.adapter.BooksResultsAdapter;
import com.hugoangeles.freebooks.ui.adapter.BooksResultsAdapter.ItemClickListener;
import com.hugoangeles.freebooks.view.BooksSearchMvpView;

import java.util.List;


public class MainActivity extends ActionBarActivity implements BooksSearchMvpView, SwipeRefreshLayout.OnRefreshListener, ItemClickListener {

    public static final String TAG = MainActivity.class.getSimpleName();

    private BooksSearchSearchPresenter booksSearchPresenter;
    private SearchView mSearchView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mBooksRecyclerView;
    private BooksResultsAdapter mBooksResultsAdapter;
    private String mQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mBooksRecyclerView = (RecyclerView) findViewById(R.id.books_container);
        mBooksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBooksResultsAdapter = new BooksResultsAdapter(this);
        mBooksResultsAdapter.setOnItemClickListener(this);
        mBooksRecyclerView.setAdapter(mBooksResultsAdapter);
        booksSearchPresenter = new BooksSearchSearchPresenter();

        mQuery = "";
        if (savedInstanceState !=null) {
            if (savedInstanceState.containsKey("query")) {
                mQuery = savedInstanceState.getString("query");
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        booksSearchPresenter.attachedView(this);
        booksSearchPresenter.onSearchBooks(mQuery);
    }

    @Override
    protected void onPause() {
        booksSearchPresenter.detachView();
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("query", mQuery);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey("query")) mQuery = savedInstanceState.getString("query");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        setupSearchView(mSearchView);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupSearchView(final SearchView searchView) {
        searchView.setQueryHint(getString(R.string.search_placeholder));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                mQuery = query;
                booksSearchPresenter.onSearchBooks(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newQuery) {
                return false;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener(){
            @Override
            public boolean onClose() {
                mQuery = "";
                booksSearchPresenter.onSearchBooks(mQuery);
                return false;
            }
        });
    }

    @Override
    public void showLoading() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void renderBooks(List<Book> books) {
        mBooksResultsAdapter.replace(books);
    }

    @Override
    public void launchBookDetail(Long id) {
        Intent intent = new Intent(this, BookDetailActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    @Override
    public void showNoBooksFound() {
        mBooksResultsAdapter.clear();
        Toast.makeText(this, "Not found books :(", Toast.LENGTH_LONG).show();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onRefresh() {
        booksSearchPresenter.onSearchBooks(mQuery);
    }

    @Override
    public void onItemClicked(Book book) {
        booksSearchPresenter.launchBookDetail(book.getId());
    }
}
