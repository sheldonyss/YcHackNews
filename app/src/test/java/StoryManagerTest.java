import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.List;

import yss.sheldon.ychacknews.business.StoryManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by yss on 10/8/2015.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StoryManagerTest {
    @Test
    public void test1() {
        assertNotNull(StoryManager.Instance());
    }

    @Test
    public void test2() {
        List<Integer> topStories = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            topStories.add(i);
        }
        StoryManager.Instance().SetTopStoryId(topStories);
        assertEquals(StoryManager.Instance().getLastLoadCount(), 0);
    }

    @Test
    public void test3() {
        StoryManager.Instance().LoadTenItems();
        assertEquals(StoryManager.Instance().getLastLoadCount(), 10);
    }

    @Test
    public void test4(){
        StoryManager.Instance().LoadTenItems();//no more story to load after this call
        StoryManager.Instance().LoadTenItems();
        assertEquals(StoryManager.Instance().getLastLoadCount(), 0);
    }
}
