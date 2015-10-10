package yss.sheldon.ychacknews.libs;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import yss.sheldon.ychacknews.R;
import yss.sheldon.ychacknews.Utls;
import yss.sheldon.ychacknews.model.CommentReplyItem;

/**
 * Created by yss on 10/3/2015.
 */
public class CommentItemViewHolder extends RecyclerView.ViewHolder {
    private TextView _commentDesc;
    private TextView _commentText;
    private TextView _replyDesc;
    private TextView _replyText;
    private CommentReplyItem _item;
    private Context _context;

    public CommentItemViewHolder(View itemView, Context context) {
        super(itemView);
        this._context = context;
        _commentDesc = (TextView) itemView.findViewById(R.id.commentDesc);
        _commentText = (TextView) itemView.findViewById(R.id.commentText);
        _replyDesc = (TextView) itemView.findViewById(R.id.replyDesc);
        _replyText = (TextView) itemView.findViewById(R.id.replyText);

    }

    public void SetItem(CommentReplyItem item) {
        _item = item;
        if (item.Comment != null) {
            _commentText.setVisibility(View.VISIBLE);
            Utls.setText(_commentDesc, item.Comment.getCommentDesc());
            if(item.Comment.text!=null)
            _commentText.setText(Html.fromHtml(item.Comment.text));
            else
                _commentText.setVisibility(View.GONE);
        }
        if (item.Reply != null) {
            _replyText.setVisibility(View.VISIBLE);
            _replyDesc.setVisibility(View.VISIBLE);
            Utls.setText(_replyDesc, item.Reply.getCommentDesc());
            if (item.Reply.text != null)
                _replyText.setText(Html.fromHtml(item.Reply.text));
            else
                _replyText.setVisibility(View.GONE);
        } else {
            _replyText.setVisibility(View.GONE);
            _replyDesc.setVisibility(View.GONE);
        }
    }


}
