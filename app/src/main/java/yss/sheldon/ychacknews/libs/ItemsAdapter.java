package yss.sheldon.ychacknews.libs;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

import yss.sheldon.ychacknews.model.Item;

/**
 * Created by yss on 10/3/2015.
 */
public class ItemsAdapter extends RecyclerView.Adapter<StoryItemViewHolder> {
    private List<Item> _items;
    private int _itemLayout;
    private Context _context;

    public ItemsAdapter(int itemLayout, Context context) {
        _items = new LinkedList<>();
        _itemLayout = itemLayout;
        _context = context;
    }

    @Override
    public StoryItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(_itemLayout, parent, false);
        return new StoryItemViewHolder(itemView, _context);
    }

    @Override
    public void onBindViewHolder(StoryItemViewHolder holder, int position) {
        if (_items != null && _items.size() > position) {
            holder.SetItem(_items.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return _items.size();
    }

    public void addItem(Item item) {
        _items.add(item);
        //notifyDataSetChanged();
    }

    public void clear() {
        _items.clear();
        notifyDataSetChanged();
    }

    public void removeAllItems() {
        _items.clear();
    }

}
