package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MasterCardlistAdapter
    extends RecyclerView.Adapter<MasterCardlistAdapter.MCLViewHolder> {



    private ArrayList<Card> cardArrayList;
    private Context context;
    private ItemClickListener clickListener;

    public MasterCardlistAdapter(ArrayList<Card> cardArrayList, ItemClickListener clickListener) {
        this.cardArrayList = cardArrayList;
        this.clickListener = clickListener;
    }
    public MasterCardlistAdapter(Context context, ArrayList<Card> cardArrayList) {
        this.context = context;
        this.cardArrayList = cardArrayList;
    }


    @Override
    public int getItemCount() {return cardArrayList.size();}

    public MasterCardlistAdapter.MCLViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cards, parent, false);
        MCLViewHolder mclViewHolder = new MCLViewHolder(view);

        return mclViewHolder;
    }

    @Override
    public void onBindViewHolder(MasterCardlistAdapter.MCLViewHolder holder, int position) {
        holder.tv_cardnames.setText(cardArrayList.get(position).getCardName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                clickListener.onItemClick(cardArrayList.get(position));
            }
        });
    }


    protected class MCLViewHolder extends RecyclerView.ViewHolder {
        TextView tv_cardnames;

        public MCLViewHolder(View v) {
            super(v);
            tv_cardnames = itemView.findViewById(R.id.tv_cardnames);
        }
    }

    public void filterList(ArrayList<Card> filterdList) {
        cardArrayList = filterdList;
        notifyDataSetChanged();
    }

    public interface ItemClickListener {
        public void onItemClick(Card card);
    }
}
