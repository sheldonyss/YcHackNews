package yss.sheldon.ychacknews.business;

import java.util.ArrayList;
import java.util.List;


import yss.sheldon.ychacknews.Utls;
import yss.sheldon.ychacknews.job.ItemJob;

/**
 * Created by yss on 9/30/2015.
 */
public class StoryManager {
    private static StoryManager _instance;
    private List<Integer> _topStoriesIds;
    //private List<Item> _itemDetails;
    private int _lastStoryIndex;

    public int getLastLoadCount() {
        return _lastLoadCount;
    }

    private int _lastLoadCount;

    private StoryManager() {
        _topStoriesIds = new ArrayList<>();
        // _itemDetails = new LinkedList<>();
        _lastStoryIndex = 0;
    }

    public void SetTopStoryId(List<Integer> ids) {
        _topStoriesIds = ids;
        _lastStoryIndex = 0;
        //itemDetails.clear();
    }

    public void LoadTenItems() {
        _lastLoadCount = 0;
        for (int i = 0; i < 10; i++) {
            if (_lastStoryIndex < _topStoriesIds.size()) {
                Utls.AddJob(new ItemJob(_topStoriesIds.get(_lastStoryIndex)));
                _lastStoryIndex++;
                _lastLoadCount++;
            } else {
                break;
            }
        }
    }

    public static StoryManager Instance() {
        if (_instance == null) {
            _instance = new StoryManager();
            //EventBus.getDefault().register(_instance);
        }
        return _instance;
    }

//    public Item GetItemById(int id) {
//        for (int i = 0; i < _itemDetails.size(); i++) {
//            Item item = _itemDetails.get(i);
//            if (item.id == id)
//                return item;
//        }
//        return null;
//    }
//
//    public void SaveItem(Item item) {
//        _itemDetails.add(item);
//    }


}
