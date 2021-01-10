package example.android.earthquake;

public class EarthQuake {

    private String mMagnitude;
    private String mLocation;
    private String mDate;
    private long mTimeInMilliseconds;

    public EarthQuake(String magnitude , String location , long timeMilliseconds){
        mMagnitude = magnitude;
        mLocation = location;
        //mDate = date;
        mTimeInMilliseconds = timeMilliseconds;
    }

    public String getMagnitude(){
        return mMagnitude;
    }

    public String getLocation(){
        return mLocation;
    }

    public String getDate(){
        return mDate;
    }

    public long getTimeMilliseconds(){
        return mTimeInMilliseconds;
    }
}
