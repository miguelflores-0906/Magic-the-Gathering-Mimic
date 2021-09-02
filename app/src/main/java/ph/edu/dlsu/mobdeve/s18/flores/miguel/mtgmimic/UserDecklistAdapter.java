package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserDecklistAdapter
        extends RecyclerView.Adapter<UserDecklistAdapter.UDAViewHolder> {

    private ArrayList<Deck> deckArrayList;
    private ItemClickListener clickListener;

    public UserDecklistAdapter(ArrayList<Deck> deckArrayList, ItemClickListener clickListener) {
        this.deckArrayList = deckArrayList;
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount() {return deckArrayList.size();}

    public UserDecklistAdapter.UDAViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_userdecks, parent, false);
        UDAViewHolder udaViewHolder = new UDAViewHolder(view);

        return udaViewHolder;
    }

    @Override
    public void onBindViewHolder(UserDecklistAdapter.UDAViewHolder holder, int position) {
        holder.tv_decknames.setText(deckArrayList.get(position).getDeckname());

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                clickListener.onItemClick(deckArrayList.get(position));
            }
        });
    }


    protected class UDAViewHolder extends RecyclerView.ViewHolder {
        TextView tv_decknames;

        public UDAViewHolder (View v) {
            super(v);
            tv_decknames = v.findViewById(R.id.tv_decknames);
        }
    }

    public interface ItemClickListener {
        public void onItemClick(Deck deck);
    }
}
