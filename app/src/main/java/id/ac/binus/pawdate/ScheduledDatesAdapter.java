package id.ac.binus.pawdate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ScheduledDatesAdapter extends RecyclerView.Adapter<ScheduledDatesAdapter.ViewHolder> {

    private final ArrayList<String> scheduledDates;

    public ScheduledDatesAdapter(ArrayList<String> scheduledDates) {
        this.scheduledDates = scheduledDates;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_scheduled_date, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String date = scheduledDates.get(position);
        holder.dateTextView.setText(date);
    }

    @Override
    public int getItemCount() {
        return scheduledDates.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView dateTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.date_text_view);
        }
    }
}