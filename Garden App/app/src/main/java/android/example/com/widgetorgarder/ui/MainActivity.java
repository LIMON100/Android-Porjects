package android.example.com.widgetorgarder.ui;

import android.content.Intent;
import android.database.Cursor;
import android.example.com.widgetorgarder.R;
import android.example.com.widgetorgarder.provider.PlantContract;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.example.com.widgetorgarder.provider.PlantContract.BASE_CONTENT_URI;
import static android.example.com.widgetorgarder.provider.PlantContract.PATH_PLANTS;


public class MainActivity
        extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int GARDEN_LOADER_ID = 100;
    private PlantListAdapter mAdapter;

    private RecyclerView mGardenRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // The main activity displays the garden as a grid layout recycler view
        mGardenRecyclerView = (RecyclerView) findViewById(R.id.plants_list_recycler_view);
        mGardenRecyclerView.setLayoutManager(
                new GridLayoutManager(this, 4)
        );
        mAdapter = new PlantListAdapter(this, null);
        mGardenRecyclerView.setAdapter(mAdapter);

        getSupportLoaderManager().initLoader(GARDEN_LOADER_ID, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri PLANT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_PLANTS).build();
        return new CursorLoader(this, PLANT_URI, null,
                null, null, PlantContract.PlantEntry.COLUMN_CREATION_TIME);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        cursor.moveToFirst();
        mAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader loader) {

    }

    public void onPlantClick(View view) {
        ImageView imgView = (ImageView) view.findViewById(R.id.plant_list_item_image);
        long plantId = (long) imgView.getTag();
        Intent intent = new Intent(getBaseContext(), PlantDetailActivity.class);
        intent.putExtra(PlantDetailActivity.EXTRA_PLANT_ID, plantId);
        startActivity(intent);
    }

    public void onAddFabClick(View view) {
        Intent intent = new Intent(this, AddPlantActivity.class);
        startActivity(intent);
    }
}