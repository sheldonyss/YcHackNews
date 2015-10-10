/**
 * Created by yss on 10/8/2015.
 */

import org.junit.Test;

import yss.sheldon.ychacknews.Utls;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class UtlsTest {
    @Test
    public void stringIsNull() {
        assertTrue(Utls.IsNullOrEmpty(null));
    }

    @Test
    public void stringIsEmpty() {
        assertTrue(Utls.IsNullOrEmpty(""));
    }

    @Test
    public void stringNotNullEmpty() {
        assertFalse(Utls.IsNullOrEmpty("test"));
    }

    @Test
    public void getRestAdapter(){
        assertNotNull(Utls.GetRestAdapter());
    }
}
