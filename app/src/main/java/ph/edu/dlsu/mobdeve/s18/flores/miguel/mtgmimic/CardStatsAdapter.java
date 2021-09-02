package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CardStatsAdapter extends RecyclerView.Adapter<CardStatsAdapter.CSViewHolder> {

    private ArrayList<Stats> statsList;

    public CardStatsAdapter(ArrayList<Stats> statsList) {
        this.statsList = statsList;
    }

    @Override
    public int getItemCount() {return statsList.size();}


    @Override
    public CardStatsAdapter.CSViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cardstats, parent, false);
        CardStatsAdapter.CSViewHolder csViewHolder = new CardStatsAdapter.CSViewHolder(v);

        return csViewHolder;
    }

    @Override
    public void onBindViewHolder(CardStatsAdapter.CSViewHolder holder, int position) {
        holder.tv_cstitle.setText(statsList.get(position).getTitle());
        holder.iv_csgraph.setImageResource(statsList.get(position).getImageID());

    }


    protected class CSViewHolder extends RecyclerView.ViewHolder {
        TextView tv_cstitle;
        ImageView iv_csgraph;

        public CSViewHolder(View v) {
            super(v);
            tv_cstitle = itemView.findViewById(R.id.tv_cstitle);
            iv_csgraph = itemView.findViewById(R.id.iv_csgraph);
        }
    }

}
