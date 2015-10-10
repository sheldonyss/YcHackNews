package yss.sheldon.ychacknews.libs;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

import yss.sheldon.ychacknews.model.CommentReplyItem;
import yss.sheldon.ychacknews.model.Item;

/**
 * Created by yss on 10/3/2015.
 */
public class CommentAdapter extends RecyclerView.Adapter<CommentItemViewHolder> {
    private List<CommentReplyItem> _comments;
    private int _itemLayout;
    private Context _context;

    public CommentAdapter(int itemLayout, Context context) {
        _comments = new LinkedList<>();
        _itemLayout = itemLayout;
        _context = context;
    }

    @Override
    public CommentItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(_itemLayout, parent, false);
        return new CommentItemViewHolder(itemView, _context);
    }

    @Override
    public void onBindViewHolder(CommentItemViewHolder holder, int position) {
        if (_comments != null && _comments.size() > position) {
            holder.SetItem(_comments.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return _comments.size();
    }

    private CommentReplyItem getItemByCommentId(int id) {
        for (int i = 0; i < _comments.size(); i++) {
            CommentReplyItem item = _comments.get(i);
            if (item.Comment != null && item.Comment.id == id)
                return item;
        }
        return null;
    }

    public boolean addOrReplaceItem(Item item) {
        boolean exist = false;
        CommentReplyItem existingItem = getItemByCommentId(item.parent);
        if (existingItem == null) {
            existingItem = new CommentReplyItem();
            existingItem.Comment = item;
            _comments.add(existingItem);
        } else {
            exist = true;
            existingItem.Reply = item;
        }
        notifyDataSetChanged();
        return exist;
    }

    public void clear() {
        _comments.clear();
        notifyDataSetChanged();
    }

    public void removeAllItems() {
        _comments.clear();
    }

}
