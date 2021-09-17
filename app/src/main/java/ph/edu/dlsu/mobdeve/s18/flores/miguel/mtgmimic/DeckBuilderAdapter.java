package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DeckBuilderAdapter extends RecyclerView.Adapter<DeckBuilderAdapter.DeckBuilderViewHolder> {

    private ArrayList<BuilderCard> cardArrayList;
    private Context context;

    public DeckBuilderAdapter(ArrayList<BuilderCard> cardArrayList, Context context) {
        this.cardArrayList = cardArrayList;
        this.context = context;
    }

    @Override
    public DeckBuilderAdapter.DeckBuilderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_deck_builder, parent, false);
        DeckBuilderAdapter.DeckBuilderViewHolder deckBuilderViewHolder = new DeckBuilderAdapter.DeckBuilderViewHolder(v);

        return deckBuilderViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull  DeckBuilderAdapter.DeckBuilderViewHolder holder, int position) {
        holder.tv_deck_builder_qty.setText(Integer.toString(cardArrayList.get(position).getQty()));
        holder.tv_deck_builder_name.setText(cardArrayList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return cardArrayList.size();
    }

    protected class DeckBuilderViewHolder extends RecyclerView.ViewHolder {
        // Text Views
        TextView tv_deck_builder_name;
        TextView tv_deck_builder_qty;

        public DeckBuilderViewHolder(View v) {
            super(v);

            tv_deck_builder_name = v.findViewById(R.id.tv_deck_builder_name);
            tv_deck_builder_qty = v.findViewById(R.id.tv_deck_builder_qty);
        }
    }

}
