package com.example.connectgym;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class GymAdapter extends RecyclerView.Adapter<GymAdapter.GymViewHolder> {

    private List<Gym> gymList;
    private OnGymClickListener listener;

    public interface OnGymClickListener {
        void onGymClick(Gym gym);
    }

    public GymAdapter(List<Gym> gymList, OnGymClickListener listener) {
        this.gymList = new ArrayList<>(gymList);
        this.listener = listener;
    }

    @Override
    public GymViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gym, parent, false);
        return new GymViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(GymViewHolder holder, int position) {
        Gym gym = gymList.get(position);
        holder.textViewGymName.setText(gym.getName());
        holder.textViewLocation.setText(gym.getLocation());
        holder.textViewPriceRange.setText(gym.getPriceRange());
        holder.gymImageView.setImageResource(gym.getImageResourceId());
    }

    @Override
    public int getItemCount() {
        return gymList.size();
    }

    public class GymViewHolder extends RecyclerView.ViewHolder {
        TextView textViewGymName, textViewLocation, textViewPriceRange;
        ImageView gymImageView;

        public GymViewHolder(View view, OnGymClickListener listener) {
            super(view);
            textViewGymName = view.findViewById(R.id.textViewGymName);
            textViewLocation = view.findViewById(R.id.textViewLocation);
            textViewPriceRange = view.findViewById(R.id.textViewPriceRange);
            gymImageView = view.findViewById(R.id.gymImage);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                        listener.onGymClick(gymList.get(getAdapterPosition()));
                    }
                }
            });
        }
    }

    // List update filtering
    public void filter(List<Gym> filteredList) {
        gymList.clear();
        gymList.addAll(filteredList);
        notifyDataSetChanged();
    }
}
