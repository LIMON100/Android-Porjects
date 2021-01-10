package example.android.earthquake;

public class EarthQuake {

    private double mMagnitude;
    private String mLocation;
    private String mDate;
    private long mTimeInMilliseconds;
    private String mUrl;

    public EarthQuake(double magnitude, String location, long timeInMilliseconds, String url) {
        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilliseconds = timeInMilliseconds;
        mUrl = url;
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

    public String getUrl() {
        return mUrl;
    }
}
