package id.ac.binus.pawdate;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MatchmakingFragment extends Fragment implements MatchmakingAdapter.OnPetActionListener {

    private RecyclerView recyclerView;
    private MatchmakingAdapter adapter;
    private ArrayList<Pet> petList;

    private ActivityResultLauncher<Intent> matchmakingDetailsLauncher;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        matchmakingDetailsLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == getActivity().RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            int petId = data.getIntExtra("petId", -1);
                            if (petId != -1) {
                                for (int i = 0; i < petList.size(); i++) {
                                    if (petList.get(i).getId() == petId) {
                                        petList.remove(i);
                                        adapter.notifyItemRemoved(i);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
        );
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_matchmaking, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        petList = new ArrayList<>();
        petList.add(new Pet(1, "Buddy", "Golden Retriever", 3, "A friendly and playful dog.", true, R.drawable.husky));
        petList.add(new Pet(2, "Whiskers", "Persian Cat", 2, "A calm and cuddly cat.", false, R.drawable.cat));
        petList.add(new Pet(3, "Coco", "Parrot", 1, "A talkative and colorful parrot.", true, R.drawable.parrot));

        adapter = new MatchmakingAdapter(getContext(), petList, this);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onMatchClick(Pet pet) {
        Intent intent = new Intent(getContext(), MatchmakingDetailsActivity.class);
        intent.putExtra("petId", pet.getId());
        intent.putExtra("petName", pet.getName());
        intent.putExtra("breed", pet.getBreed());
        intent.putExtra("age", pet.getAge());
        intent.putExtra("description", pet.getDescription());
        intent.putExtra("vaccinated", pet.isVaccinated());
        intent.putExtra("imageResId", pet.getImageResId());
        matchmakingDetailsLauncher.launch(intent);
    }

    @Override
    public void onDeleteClick(Pet pet) {
        Toast.makeText(getContext(), pet.getName() + " removed from matchmaking.", Toast.LENGTH_SHORT).show();
        petList.remove(pet);
        adapter.notifyDataSetChanged();
    }
}
