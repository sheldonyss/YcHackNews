import org.junit.Test;

import java.util.Date;

import yss.sheldon.ychacknews.model.Item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * Created by yss on 10/8/2015.
 */
public class ItemTest {
    @Test
    public void isComment() {
        Item item = new Item();
        item.type = "cOMment";
        assertTrue(item.isComment());
    }

    @Test
    public void testStoryDesc() {
        Item item = mock(Item.class);
        //Item item = new Item();
        item.by = "Sheldon";
        item.type = "story";
        item.title = "Android is awesome";

        when(item.getTimeSpan()).thenReturn("1 hour ago");
        when(item.getStoryDesc()).thenCallRealMethod();
        String s = item.getStoryDesc();
        assertEquals("by Sheldon 1 hour ago", s);
    }

    @Test
    public void testCommentDesc(){
        Item item = mock(Item.class);
        //Item item = new Item();
        item.by = "abc";
        item.type = "comment";
        item.title = "Android is awesome";

        when(item.getTimeSpan()).thenReturn("10 seconds ago");
        when(item.getCommentDesc()).thenCallRealMethod();
        String s = item.getCommentDesc();
        assertEquals("by abc 10 seconds ago", s);
    }
}
