package android.example.com.mediaplayer;

import android.content.Context;
import android.util.JsonReader;

import java.io.IOException;
import java.util.ArrayList;

public class Sample {

    private int mSampleID;
    private String mComposer;
    private String mTitle;
    private String mUri;
    private String mAlbumArtID;


    private Sample(int sampleID, String composer, String title, String uri, String albumArtID) {
        mSampleID = sampleID;
        mComposer = composer;
        mTitle = title;
        mUri = uri;
        mAlbumArtID = albumArtID;
    }

    static ArrayList<Integer> getAllSampleIDs(Context context){
        JsonReader reader;
        ArrayList<Integer> sampleIDs = new ArrayList<>();
        try {
            reader = readJSONFile(context);
            reader.beginArray();
            while (reader.hasNext()) {
                sampleIDs.add(readEntry(reader).getSampleID());
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sampleIDs;
    }

}
