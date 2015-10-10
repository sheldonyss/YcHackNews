package yss.sheldon.ychacknews.libs;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import yss.sheldon.ychacknews.ItemDetailActivity;
import yss.sheldon.ychacknews.R;
import yss.sheldon.ychacknews.model.Item;

/**
 * Created by yss on 10/3/2015.
 */
public class StoryItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView Title;
    private TextView Desc;
    private ImageButton OpenBrowser;
    private Item _item;
    private Context _context;

    public StoryItemViewHolder(View itemView, Context context) {
        super(itemView);
        this._context = context;
        Title = (TextView) itemView.findViewById(R.id.ItemTitleText);
        Desc = (TextView) itemView.findViewById(R.id.ItemDesc);
        OpenBrowser = (ImageButton) itemView.findViewById(R.id.btnOpenBrowser);
        OpenBrowser.setOnClickListener(this);
        itemView.setOnClickListener(this);
    }

    public void SetItem(Item item) {
        this._item = item;
        Title.setText(item.title.trim());
        Desc.setText(item.getStoryDesc());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == OpenBrowser.getId()) {
            String url = _item.url;
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            _context.startActivity(i);
        } else {
            if(_item.kids==null){
                Toast.makeText(_context,"No comments yet",Toast.LENGTH_SHORT).show();
            }
            else{
                Intent i = new Intent(_context, ItemDetailActivity.class);
                i.putExtra("CommentIds", _item.kids);
                _context.startActivity(i);
            }
        }
    }


}
