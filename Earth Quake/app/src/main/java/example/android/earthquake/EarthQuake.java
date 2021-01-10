package example.android.earthquake;

public class EarthQuake {

    private double mMagnitude;
    private String mLocation;
    private String mDate;
    private long mTimeInMilliseconds;

    public EarthQuake(double magnitude , String location , long timeMilliseconds){
        mMagnitude = magnitude;
        mLocation = location;
        //mDate = date;
        mTimeInMilliseconds = timeMilliseconds;
    }

    public double getMagnitude(){
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
