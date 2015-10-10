package yss.sheldon.ychacknews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.pnikosis.materialishprogress.ProgressWheel;

import de.greenrobot.event.EventBus;
import yss.sheldon.ychacknews.job.ItemJob;
import yss.sheldon.ychacknews.libs.CommentAdapter;
import yss.sheldon.ychacknews.libs.DividerItemDecoration;
import yss.sheldon.ychacknews.model.Item;

public class ItemDetailActivity extends AppCompatActivity {
    SuperRecyclerView _commentList;
    CommentAdapter _commentAdapter;
    ProgressWheel _progressBar;
    final Object _syncObj = new Object();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        _commentList = (SuperRecyclerView) findViewById(R.id.itemCommentList);
        _progressBar = (ProgressWheel) findViewById(R.id.progress_wheel);
        _progressBar.spin();
        _commentAdapter = new CommentAdapter(R.layout.comment_item_layout, this);
        _commentList.setLayoutManager(new LinearLayoutManager(this));
        _commentList.getRecyclerView().addItemDecoration(new DividerItemDecoration(this, R.drawable.divider));
        _commentList.setAdapter(_commentAdapter);
        EventBus.getDefault().register(this);
        int[] commentId = getIntent().getIntArrayExtra("CommentIds");
        int commentLimit = 10;
        int count = 0;
        for (int aCommentId : commentId) {
            Utls.AddJob(new ItemJob(aCommentId));
            count++;
            if (count == commentLimit) {
                break;
            }
        }
    }

    public void onEventMainThread(Item comment) {
        synchronized (_syncObj) {
            boolean commentExist = _commentAdapter.addOrReplaceItem(comment);
            if (!commentExist) {
                if (comment.kids != null && comment.kids.length > 0) {
                    Utls.AddJob(new ItemJob(comment.kids[comment.kids.length - 1]));
                }
            }
        }
        _progressBar.stopSpinning();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
    }

}
