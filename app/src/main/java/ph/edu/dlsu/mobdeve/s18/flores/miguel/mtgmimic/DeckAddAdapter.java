package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import io.magicthegathering.javasdk.resource.Card;

public class DeckAddAdapter extends RecyclerView.Adapter<DeckAddAdapter.DeckAddHolder> {

    private Context context;
    private ArrayList<Card> cardArrayList;
    private ItemClickListener listener;

    public DeckAddAdapter(ArrayList<Card> cardArrayList, ItemClickListener clickListener) {
        this.cardArrayList = cardArrayList;
        this.listener = clickListener;
    }


    @Override
    public DeckAddAdapter.DeckAddHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cards, parent, false);
        DeckAddAdapter.DeckAddHolder deckAddHolder = new DeckAddAdapter.DeckAddHolder(v);

        return deckAddHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DeckAddAdapter.DeckAddHolder holder, int position) {
        holder.tv_cardnames.setText(cardArrayList.get(position).getName());

        holder.itemView.setOnClickListener(v -> {
            listener.onItemClick(cardArrayList.get(position));
        });
    }

    public interface RecyclerViewClickListener {
        void onClick(View v, int position);
    }

    @Override
    public int getItemCount() {
        return cardArrayList.size();
    }

    public void filterList(ArrayList<Card> filterdList) {
        cardArrayList = filterdList;
        notifyDataSetChanged();
    }

    protected class DeckAddHolder extends RecyclerView.ViewHolder {
        // Text Views
        TextView tv_cardnames;

        public DeckAddHolder(View v) {
            super(v);

            tv_cardnames = v.findViewById(R.id.tv_cardnames);
        }
    }


    public interface ItemClickListener {
        public void onItemClick(Card card);
    }
}
