package id.ac.binus.pawdate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MatchmakingAdapter extends RecyclerView.Adapter<MatchmakingAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Pet> petList;
    private OnPetActionListener listener;

    public interface OnPetActionListener {
        void onMatchClick(Pet pet);
        void onDeleteClick(Pet pet);
    }

    public MatchmakingAdapter(Context context, ArrayList<Pet> petList, OnPetActionListener listener) {
        this.context = context;
        this.petList = petList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pet, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pet pet = petList.get(position);

        holder.tvPetName.setText(pet.getName());
        holder.tvBreed.setText(pet.getBreed());
        holder.ivPetImage.setImageResource(pet.getImageResId());

        holder.btnMatch.setOnClickListener(v -> listener.onMatchClick(pet));

        holder.btnDelete.setOnClickListener(v -> {
            petList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, petList.size());
            listener.onDeleteClick(pet);
        });

        holder.btnMatch.setOnClickListener(v -> {
            if (listener != null) {
                listener.onMatchClick(pet); // Pass the correct Pet object
            }
        });

    }

    @Override
    public int getItemCount() {
        return petList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvPetName, tvBreed;
        ImageView ivPetImage;
        Button btnMatch, btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPetName = itemView.findViewById(R.id.tvPetName);
            tvBreed = itemView.findViewById(R.id.tvBreed);
            ivPetImage = itemView.findViewById(R.id.ivPetImage);
            btnMatch = itemView.findViewById(R.id.btnMatch);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
