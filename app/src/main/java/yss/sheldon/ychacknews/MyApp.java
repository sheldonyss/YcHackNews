package yss.sheldon.ychacknews;

import android.app.Application;

/**
 * Created by yss on 9/30/2015.
 */
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Utls.Init(this);
    }
}
