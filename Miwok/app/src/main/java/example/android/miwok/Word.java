package example.android.miwok;

public class Word {

    private String mDefaultTranslation;
    private String mMiwokTranslation;
    
    public Word(String defaultTranslation , String miwokTrainslation){

        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTrainslation;
    }

    public String getmDefaultTranslation(){
        return mDefaultTranslation;
    }

    public String getmMiwokTranslation(){
        return mMiwokTranslation;
    }

}
