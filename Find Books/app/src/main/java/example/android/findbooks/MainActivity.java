package example.android.findbooks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.LoaderManager;

import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>>{

    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    private static final int BOOK_LOADER_ID = 1;
    ListView bookListView;
    boolean isConnected;
    private String mUrlRequestGoogleBooks = "";
    private TextView mEmptyStateTextView;
    private View circleProgressBar;
    private BookAdapter mAdapter;
    private SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //checking internet connection
        final ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        checkConnection(cm);

        //if network is connected than show the list
        bookListView = (ListView) findViewById(R.id.list);

        mAdapter = new BookAdapter(this , new ArrayList<Book>());
        bookListView.setAdapter(mAdapter);

        //If there is now internet connection.
        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        bookListView.setEmptyView(mEmptyStateTextView);

        circleProgressBar = findViewById(R.id.loading_cir);

        //Search Button
        Button mButton = (Button) findViewById(R.id.search_button);

        //Search Field
        mSearchView = (SearchView) findViewById(R.id.search_view_field);
        mSearchView.onActionViewExpanded();
        mSearchView.setIconified(true);
        mSearchView.setQueryHint("Enter a book title");

        if(isConnected){
            LoaderManager lm = getLoaderManager();
            lm.initLoader(BOOK_LOADER_ID, null, this);
        }
        else {
            Log.i(LOG_TAG, "INTERNET connection status: " + String.valueOf(isConnected) + ". Sorry dude, no internet - no data :(");

            circleProgressBar.setVisibility(GONE);
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }

        mButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                checkConnection(cm);

                if(isConnected){
                    updateQueryUrl(mSearchView.getQuery().toString());
                    restartLoader();
                    Log.i(LOG_TAG, "Search value: " + mSearchView.getQuery().toString());
                }
                else {
                    mAdapter.clear();
                    mEmptyStateTextView.setVisibility(View.VISIBLE);
                    mEmptyStateTextView.setText(R.string.no_internet_connection);
                }
            }
        });

        bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book currentBook = mAdapter.getItem(position);

                assert currentBook != null;
                Uri buyBookUri = Uri.parse(currentBook.getUrlBook());

                Intent intent = new Intent(Intent.ACTION_VIEW , buyBookUri);
                startActivity(intent);
            }
        });
    }

    private String updateQueryUrl(String searchValue) {

        if (searchValue.contains(" ")) {
            searchValue = searchValue.replace(" ", "+");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("https://www.googleapis.com/books/v1/volumes?q=").append(searchValue).append("&filter=paid-ebooks&maxResults=40");
        mUrlRequestGoogleBooks = sb.toString();
        return mUrlRequestGoogleBooks;
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int i , Bundle bundle) {
        Log.i("There is no instance", ": Created new one loader at the beginning!");
        updateQueryUrl(mSearchView.getQuery().toString());
        return new BookLoader(this, mUrlRequestGoogleBooks);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> data) {
        View circleProgressBar = findViewById(R.id.loading_cir);
        circleProgressBar.setVisibility(GONE);

        mEmptyStateTextView.setText(R.string.no_books);
        Log.i(LOG_TAG, ": Books has been moved to adapter's data set. This will trigger the ListView to update!");

        mAdapter.clear();

        if (data != null && !data.isEmpty()) {
            mAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        mAdapter.clear();
        Log.i(LOG_TAG, ": Loader reset, so we can clear out our existing data!");
    }

    public void restartLoader() {
        mEmptyStateTextView.setVisibility(GONE);
        circleProgressBar.setVisibility(View.VISIBLE);
        getLoaderManager().restartLoader(BOOK_LOADER_ID, null, MainActivity.this);
    }

    public void checkConnection(ConnectivityManager connectivityManager){
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        if (activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting()) {
            isConnected = true;

            Log.i(LOG_TAG, "INTERNET connection status: " + String.valueOf(isConnected) + ". It's time to play with LoaderManager :)");

        } else {
            isConnected = false;

        }
    }
}