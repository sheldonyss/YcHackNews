package yss.sheldon.ychacknews.job;

import com.path.android.jobqueue.JobManager;

import java.util.List;

import de.greenrobot.event.EventBus;
import retrofit.RestAdapter;
import yss.sheldon.ychacknews.service.StoryService;


/**
 * Created by yss on 9/28/2015.
 */
public class TopStoryJob extends ApiJob {

    @Override
    public void onAdded() {

    }

    @Override
    public void onRun() throws Throwable {
        StoryService service = adapter.create(StoryService.class);
        List<Integer> list = service.GetTopStories();
        if (list != null && list.size() > 0) {
            EventBus.getDefault().post(list);
        }
    }

    @Override
    protected void onCancel() {

    }
}
