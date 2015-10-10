package yss.sheldon.ychacknews.service;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
import yss.sheldon.ychacknews.model.Item;

/**
 * Created by yss on 9/28/2015.
 */
public interface StoryService {
    @GET("/v0/topstories.json?print=pretty")
    List<Integer> GetTopStories();

    @GET("/v0/item/{id}.json")
    Item StoryItem(@Path("id") int itemId);
}
