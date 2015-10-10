package yss.sheldon.ychacknews.model;

import android.text.format.DateUtils;

import java.util.Date;

/**
 * Created by yss on 9/28/2015.
 */
public class Item {
    public String by;
    public int descendants;
    public int id;
    public int[] kids;
    public int score;
    public long time;
    public String title;
    public String type;
    public String url;
    public String text;
    public int parent;
    public boolean deleted;

    public Item(){
        deleted=false;
    }

    public boolean isComment(){
        return type.equalsIgnoreCase("comment");
    }
    public String getStoryDesc() {
        String s = "";
        if (type.equalsIgnoreCase("story")) {
            s = String.format("by %s %s", by, getTimeSpan());
        }
        return s;
    }

    public String getCommentDesc(){
        String s = "";
        if (type.equalsIgnoreCase("comment")) {
            s = String.format("by %s %s", by, getTimeSpan());
        }
        return s;
    }



    public String getTimeSpan() {
        Date start = new Date((long) time * 1000);
        return DateUtils.getRelativeTimeSpanString(start.getTime(), System.currentTimeMillis(), 0).toString();
    }
}
