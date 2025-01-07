package id.ac.binus.pawdate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Match> matchList;
    private OnMatchActionListener listener;

    public interface OnMatchActionListener {
        void onViewDetailsClick(Match match);
        void onDeleteMatchClick(Match match);
    }

    public MatchesAdapter(Context context, ArrayList<Match> matchList, OnMatchActionListener listener) {
        this.context = context;
        this.matchList = matchList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_match, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Match match = matchList.get(position);

        holder.tvPetName.setText(match.getPetName());
        holder.tvBreed.setText(match.getPetBreed());
        holder.tvAge.setText("Age: " + match.getPetAge() + " years");
        holder.tvVaccinated.setText(match.isVaccinated() ? "Vaccinated: Yes" : "Vaccinated: No");
        holder.ivPetImage.setImageResource(match.getImageResId());

        holder.btnViewDetails.setOnClickListener(v -> {
            if (listener != null) {
                listener.onViewDetailsClick(match);
            }
        });

        holder.btnDeleteMatch.setOnClickListener(v -> {
            if (listener != null) {
                listener.onDeleteMatchClick(match);
            }

            matchList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, matchList.size());
            Toast.makeText(context, "Match deleted successfully!", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return matchList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvPetName, tvBreed, tvAge, tvVaccinated;
        ImageView ivPetImage;
        Button btnViewDetails, btnDeleteMatch;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPetName = itemView.findViewById(R.id.tvPetName);
            tvBreed = itemView.findViewById(R.id.tvBreed);
            tvAge = itemView.findViewById(R.id.tvAge);
            tvVaccinated = itemView.findViewById(R.id.tvVaccinated);
            ivPetImage = itemView.findViewById(R.id.ivPetImage);
            btnViewDetails = itemView.findViewById(R.id.btnViewDetails);
            btnDeleteMatch = itemView.findViewById(R.id.btnDeleteMatch);
        }
    }
}
