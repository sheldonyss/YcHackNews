package yss.sheldon.ychacknews;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.JobManager;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by yss on 9/28/2015.
 */
public class Utls {
    private static JobManager _jobManager;

    public static void Init(Context context) {
        _jobManager = new JobManager(context);
    }

    public static RestAdapter GetRestAdapter() {
        //Type propNameSearchResultType = new TypeToken<HashMap<PropertyCategory, String>>() {
        //}.getType();
        Gson gson = new GsonBuilder()
                //.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
//                .registerTypeAdapter(Date.class, new DateDeserialier())
//                .registerTypeAdapter(PropertyType.class, new PropertyTypeDeserializer())
//                .registerTypeAdapter(PropertySubType.class, new PropertySubtypeDeserializer())
//                .registerTypeAdapter(propNameSearchResultType, new PropSearchResultDeserializer())
//                .registerTypeAdapter(HDBSchemeType.class, new HDBSchemeDeserializer())
//                .registerTypeAdapter(AmenityType.class, new AmenityTypeDeserializer())
//                .registerTypeAdapter(LiveChatStatus.class, new LiveChatStatusDeserializer())
                .create();
        //ApiRequestInterceptor requestInterceptor = new ApiRequestInterceptor();
        RestAdapter restAdapter = new RestAdapter.Builder()
                //.setRequestInterceptor(requestInterceptor)
                .setConverter(new GsonConverter(gson))
                .setEndpoint("https://hacker-news.firebaseio.com")
                        //.setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        return restAdapter;
    }

    public static boolean IsNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }

    public static boolean NotNullEmpty(String s) {
        return !IsNullOrEmpty(s);
    }

    public static void AddJob(Job job) {
        if (_jobManager != null)
            _jobManager.addJob(job);
    }

    public static void setText(TextView textView, String text) {
        if (Utls.NotNullEmpty(text)) {
            textView.setText(text);
            textView.setVisibility(View.VISIBLE);
        } else {
            textView.setVisibility(View.GONE);
        }
    }
}
