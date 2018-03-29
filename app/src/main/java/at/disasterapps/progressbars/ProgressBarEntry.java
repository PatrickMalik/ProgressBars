package at.disasterapps.progressbars;

import java.util.concurrent.atomic.AtomicInteger;

import at.disasterapps.progressbars.exceptions.StartAndEndAreEqualException;

/**
 * Created by Austr on 08.03.2018.
 */

public class ProgressBarEntry {


    int id;

    //creating it everytime i need it right now, easier to persist this way
    private transient Progress progress;

    private String title;
    private String description;



    private int from;

    private int to;

    public ProgressBarEntry(String title, String description, int from, int to) {

        this.title = title;
        this.description = description;
        this.from = from;
        this.to = to;

        //TODO as long as theres only one bar to create, ill use relative, will change later
        try {
            this.progress = new RelativeProgress(this.from, this.to);
        } catch (StartAndEndAreEqualException e) {
            e.printStackTrace();
        }
    }

    public ProgressBarEntry(int id, String title, String description, int from, int to) {
        this.id = id; //very unsure if i shouldnt use this.id  = count.incrementAndGet();
        this.title = title;
        this.description = description;
        this.from = from;
        this.to = to;

//        //TODO as long as theres only one bar to create, ill use relative, will change later
//        try {
//            this.progress = new RelativeProgress(this.from, this.to);
//        } catch (StartAndEndAreEqualException e) {
//            e.printStackTrace();
//        }
    }


    public ProgressBarEntry() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Progress getProgress() {
        //TODO yeah i know
        try {
            return new RelativeProgress(this.from, this.to);
        } catch (StartAndEndAreEqualException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    //Probably not necessary
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(title);
//        dest.writeString(description);
//        dest.writeInt(from);
//        dest.writeInt(to);
//        dest.writeValue(progress);
//    }
//
//    public static final Parcelable.Creator<ProgressBarEntry> CREATOR = new Parcelable.Creator<ProgressBarEntry>(){
//        public ProgressBarEntry createFromParcel(Parcel in){
//            return new ProgressBarEntry(in);
//        }
//
//        public ProgressBarEntry[] newArray(int size){
//            return new ProgressBarEntry[size];
//        }
//    };
//
//    private ProgressBarEntry(Parcel in){
//        title = in.readString();
//        description = in.readString();
//        from = in.readInt();
//        to = in.readInt();
//        progress = (Progress) in.readValue(Progress.class.getClassLoader());
//    }
}
