package example.android.miwok;

public class Word {

    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int mImageResourceId = NO_IMAGE_PROVIDED;
    private int mAudioResourceId;

    private static final int NO_IMAGE_PROVIDED = -1;
    
    public Word(String defaultTranslation , String miwokTrainslation , int audioResourceId){

        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTrainslation;
        mAudioResourceId = audioResourceId;
    }

    public Word(String defaultTranslation , String miwokTrainslation , int imageResourceId , int audioResourceId){

        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTrainslation;
        mImageResourceId = imageResourceId;
        mAudioResourceId = audioResourceId;
    }

    public String getDefaultTranslation(){
        return mDefaultTranslation;
    }

    public String getMiwokTranslation(){
        return mMiwokTranslation;
    }

    public int getImageResourceId(){
        return mImageResourceId;
    }

    public boolean hasImage(){
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    public int getAudioResourceId(){
        return mAudioResourceId;
    }

}
