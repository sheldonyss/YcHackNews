package yss.sheldon.ychacknews.test;

import android.support.v7.widget.RecyclerView;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.robotium.solo.Condition;
import com.robotium.solo.Solo;

import yss.sheldon.ychacknews.MainActivity;
import yss.sheldon.ychacknews.R;

/**
 * Created by yss on 10/8/2015.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private Solo solo;

    public MainActivityTest(Class activityClass) {
        super(activityClass);
    }

    public MainActivityTest() {
        super(MainActivity.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @LargeTest
    public void testActivity() {
        // robotium assert
        solo.assertCurrentActivity("Welcome Screen", MainActivity.class);

        SuperRecyclerView superRecyclerView = (SuperRecyclerView) solo.getView(R.id.storyList);
        final RecyclerView recyclerView = superRecyclerView.getRecyclerView();

        solo.waitForCondition(new Condition() {
            @Override
            public boolean isSatisfied() {
                return recyclerView.getAdapter().getItemCount() > 0;
            }
        }, 10000);

        final RecyclerView.Adapter adapter = recyclerView.getAdapter();
        assertTrue(adapter.getItemCount() > 0);

        solo.clickInRecyclerView(0);
        final SuperRecyclerView detailList = (SuperRecyclerView) solo.getView(R.id.itemCommentList);
        final RecyclerView detailRecyclerView = detailList.getRecyclerView();
        solo.waitForCondition(new Condition() {
            @Override
            public boolean isSatisfied() {
                return detailRecyclerView.getAdapter().getItemCount() > 0;
            }
        }, 10000);
        RecyclerView.Adapter detailAdapter = detailRecyclerView.getAdapter();
        assertTrue(detailAdapter.getItemCount() > 0);
        solo.goBack();

        solo.scrollDown();
        solo.scrollDown();
        solo.waitForCondition(new Condition() {
            @Override
            public boolean isSatisfied() {
                return adapter.getItemCount() >= 20;
            }
        }, 10000);
        assertTrue(adapter.getItemCount() >= 20);
    }
}