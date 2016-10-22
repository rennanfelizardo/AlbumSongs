package albumsongs.rennanfelizardo.com.br.albumsongs;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by rennanfelizardo on 22/10/16.
 */

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {

    private Context context;
    private List<Album> albumList;

    public AlbumAdapter(Context context, List<Album> albumList) {
        this.context = context;
        this.albumList = albumList;
    }

    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_card, parent, false);
        return new AlbumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AlbumAdapter.AlbumViewHolder holder, int position) {
        Album album = albumList.get(position);
        holder.title.setText(album.getName());
        holder.count.setText(album.getNumOfSongs() + " songs");

        Glide.with(context).load(album.getThumbnail()).into(holder.thumbnail);

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopUpMenu(holder.overflow);
            }
        });

    }

    private void showPopUpMenu(ImageView overflow) {
        PopupMenu popup = new PopupMenu(context, overflow);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_add_favorite:
                        Toast.makeText(context, "Added to Favorites", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.action_play_next:
                        Toast.makeText(context, "Play next", Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                }
                return false;
            }
        });
        popup.show();
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }




    public class AlbumViewHolder extends RecyclerView.ViewHolder{

        private TextView title, count;
        private ImageView thumbnail, overflow;

        public AlbumViewHolder(View itemView) {
            super(itemView);
            this.title = (TextView) itemView.findViewById(R.id.title);
            this.count = (TextView) itemView.findViewById(R.id.count);
            this.thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            this.overflow = (ImageView) itemView.findViewById(R.id.overflow);
        }
    }


}
