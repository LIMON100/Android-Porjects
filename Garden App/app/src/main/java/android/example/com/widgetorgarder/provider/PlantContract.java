package android.example.com.widgetorgarder.provider;


import android.net.Uri;
import android.provider.BaseColumns;

public class PlantContract {

    public static final String AUTHORITY = "android.example.com.widgetorgarder";

    // The base content URI = "content://" + <authority>
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static final String PATH_PLANTS = "plants";

    public static final long INVALID_PLANT_ID = -1;

    public static final class PlantEntry implements BaseColumns {

        // TaskEntry content URI = base content URI + path
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_PLANTS).build();

        public static final String TABLE_NAME = "plants";
        public static final String COLUMN_PLANT_TYPE = "plantType";
        public static final String COLUMN_CREATION_TIME = "createdAt";
        public static final String COLUMN_LAST_WATERED_TIME = "lastWateredAt";
    }
}
