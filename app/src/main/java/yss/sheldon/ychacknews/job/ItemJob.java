package yss.sheldon.ychacknews.job;

import de.greenrobot.event.EventBus;
import yss.sheldon.ychacknews.model.Item;
import yss.sheldon.ychacknews.service.StoryService;

/**
 * Created by yss on 9/30/2015.
 */
public class ItemJob extends ApiJob {
    private int itemId;

    public ItemJob(int itemId) {
        this.itemId = itemId;
    }

    @Override
    public void onAdded() {

    }

    @Override
    public void onRun() throws Throwable {
        StoryService service = adapter.create(StoryService.class);
        Item item = service.StoryItem(itemId);
        if (!item.deleted)
            EventBus.getDefault().post(item);
    }

    @Override
    protected void onCancel() {

    }
}
