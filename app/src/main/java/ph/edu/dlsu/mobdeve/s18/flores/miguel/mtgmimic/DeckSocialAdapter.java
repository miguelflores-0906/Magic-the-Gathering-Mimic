package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DeckSocialAdapter extends RecyclerView.Adapter<DeckSocialAdapter.DSViewHolder> {

    private ArrayList<Deck> deckArrayList;

    private ItemClickListener clickListener;

    public DeckSocialAdapter(ArrayList<Deck> deckList) {
        this.deckArrayList = deckList;
    }

    @Override
    public int getItemCount() {return deckArrayList.size();}

    public DeckSocialAdapter.DSViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_decksocial, parent, false);
        DeckSocialAdapter.DSViewHolder dsViewHolder = new DeckSocialAdapter.DSViewHolder(view);

        return dsViewHolder;
    }

    @Override
    public void onBindViewHolder(DeckSocialAdapter.DSViewHolder holder, int position) {
        holder.tv_cardnames.setText(deckArrayList.get(position).getDeckname());

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                clickListener.onItemClick(deckArrayList.get(position));
            }
        });
    }


    protected class DSViewHolder extends RecyclerView.ViewHolder {
        TextView tv_cardnames;

        public DSViewHolder(View v) {
            super(v);
            tv_cardnames = itemView.findViewById(R.id.tv_socialdeck);
        }
    }

    public void filterList(ArrayList<Deck> filterdList) {
        deckArrayList = filterdList;
        notifyDataSetChanged();
    }

    public interface ItemClickListener {
        public void onItemClick(Deck deck);
    }
}
