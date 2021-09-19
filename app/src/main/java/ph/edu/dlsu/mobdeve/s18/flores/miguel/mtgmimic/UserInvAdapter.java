package ph.edu.dlsu.mobdeve.s18.flores.miguel.mtgmimic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserInvAdapter
                extends RecyclerView.Adapter<UserInvAdapter.InvViewHolder> {

    private ArrayList<BuilderCard> cardArrayList;
    private Context context;
    private ItemClickListener listener;

    public UserInvAdapter(ArrayList<BuilderCard> cardArrayList, Context context) {
        this.cardArrayList = cardArrayList;
        this.context = context;
    }

    public UserInvAdapter(ArrayList<BuilderCard> cardArrayList, ItemClickListener listener) {
        this.cardArrayList = cardArrayList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public InvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_deck_builder, parent, false);
        UserInvAdapter.InvViewHolder userInvViewHolder = new UserInvAdapter.InvViewHolder(v);

        return userInvViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserInvAdapter.InvViewHolder holder, int position) {
        holder.tv_deck_builder_qty.setText(Integer.toString(cardArrayList.get(position).getQty()));
        holder.tv_deck_builder_name.setText(cardArrayList.get(position).getName());

        holder.itemView.setOnClickListener(v -> {
            listener.onItemClick(cardArrayList.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return cardArrayList.size();
    }

    protected class InvViewHolder extends RecyclerView.ViewHolder {
        // Text Views
        TextView tv_deck_builder_name;
        TextView tv_deck_builder_qty;

        public InvViewHolder(View v) {
            super(v);

            tv_deck_builder_name = v.findViewById(R.id.tv_deck_builder_name);
            tv_deck_builder_qty = v.findViewById(R.id.tv_deck_builder_qty);
        }
    }

    public interface ItemClickListener {
        public void onItemClick(BuilderCard card);
    }

    public void updateList(ArrayList<BuilderCard> newList) {
        cardArrayList.clear();
        cardArrayList.addAll(newList);
        notifyDataSetChanged();
    }

    public void filterList(ArrayList<BuilderCard> filterdList) {
        cardArrayList = filterdList;
        notifyDataSetChanged();
    }
}
