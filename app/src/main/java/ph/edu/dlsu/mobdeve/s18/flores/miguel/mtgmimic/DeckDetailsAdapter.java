package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DeckDetailsAdapter extends RecyclerView.Adapter<DeckDetailsAdapter.DDViewHolder> {


    private ArrayList<Card> cardArrayList;
    private ItemClickListener clickListener;

    public DeckDetailsAdapter(ArrayList<Card> cardArrayList, ItemClickListener clickListener)
    {
        this.cardArrayList = cardArrayList;
        this.clickListener = clickListener;
    }

    @Override
    public DeckDetailsAdapter.DDViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cards, parent, false);
        DeckDetailsAdapter.DDViewHolder ddViewHolder = new  DeckDetailsAdapter.DDViewHolder(v);

        return ddViewHolder;
    }

    @Override
    public void onBindViewHolder( DeckDetailsAdapter.DDViewHolder holder, int position) {
        holder.tv_cardnames.setText(cardArrayList.get(position).getCardName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                clickListener.onItemClick(cardArrayList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    protected class DDViewHolder extends RecyclerView.ViewHolder {
        TextView tv_cardnames;

        public DDViewHolder(View v) {
            super(v);
            tv_cardnames = itemView.findViewById(R.id.tv_cardnames);
        }
    }

    public interface ItemClickListener {
        public void onItemClick(Card card);
    }
}

