package example.android.miwok;

public class Word {

    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    private static final int NO_IMAGE_PROVIDED = -1;
    
    public Word(String defaultTranslation , String miwokTrainslation){

        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTrainslation;
    }

    public Word(String defaultTranslation , String miwokTrainslation , int imageResourceId){

        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTrainslation;
        mImageResourceId = imageResourceId;
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

}
