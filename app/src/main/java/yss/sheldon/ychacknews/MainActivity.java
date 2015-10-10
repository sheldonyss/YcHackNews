package yss.sheldon.ychacknews;

import android.graphics.drawable.GradientDrawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.pnikosis.materialishprogress.ProgressWheel;

import java.util.List;

import de.greenrobot.event.EventBus;
import yss.sheldon.ychacknews.business.StoryManager;
import yss.sheldon.ychacknews.event.ErrorEvent;
import yss.sheldon.ychacknews.job.TopStoryJob;
import yss.sheldon.ychacknews.libs.DividerItemDecoration;
import yss.sheldon.ychacknews.libs.ItemsAdapter;
import yss.sheldon.ychacknews.model.Item;

public class MainActivity extends AppCompatActivity implements OnMoreListener{
    SuperRecyclerView _storyList;
    final Object _synObj = new Object();
    ItemsAdapter _itemsAdapter;
    int loadCount = 0;
    ProgressWheel _progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _itemsAdapter = new ItemsAdapter(R.layout.item_layout, this);
        _progressBar = (ProgressWheel) findViewById(R.id.progress_wheel);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        _storyList = (SuperRecyclerView) findViewById(R.id.storyList);
        _storyList.setLayoutManager(new LinearLayoutManager(this));
        _storyList.getRecyclerView().addItemDecoration(new DividerItemDecoration(this, R.drawable.divider));
        _storyList.setOnMoreListener(this);
        _storyList.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                _itemsAdapter.clear();
                _progressBar.spin();
                Utls.AddJob(new TopStoryJob());
            }
        });
        //_storyList.showProgress();
        _storyList.setAdapter(_itemsAdapter);
        _storyList.setNumberBeforeMoreIsCalled(1);
        _storyList.setLoadingMore(true);

        _progressBar.spin();
        //_storyList.showProgress();
        Utls.AddJob(new TopStoryJob());
    }

    public void onEventMainThread(Item item) {
        synchronized (_synObj) {
            if(item.isComment())
                return;
            loadCount++;
            _itemsAdapter.addItem(item);
            if (loadCount == StoryManager.Instance().getLastLoadCount()) {
                _storyList.hideMoreProgress();
                _storyList.hideProgress();
                _progressBar.stopSpinning();
                _itemsAdapter.notifyDataSetChanged();
            }
        }
    }

    public void onEventMainThread(ErrorEvent event) {
        _progressBar.stopSpinning();
    }

    public void onEventMainThread(List<Integer> storiesId) {
        //_itemsAdapter.removeAllItems();
        StoryManager.Instance().SetTopStoryId(storiesId);
        StoryManager.Instance().LoadTenItems();
        loadCount = 0;
    }

    @Override
    protected void onResume(){
        super.onResume();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {
        loadCount = 0;
        StoryManager.Instance().LoadTenItems();
        _storyList.showMoreProgress();
    }
}
