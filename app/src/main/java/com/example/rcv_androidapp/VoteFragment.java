package com.example.rcv_androidapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.rcv_androidapp.databinding.FragmentVoteBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VoteFragment extends Fragment {

    private FragmentVoteBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentVoteBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //load in candidates from the provided codeUrl (develop later)
        binding.btnGetCandidates.setOnClickListener(view1 -> {
            String urlCode = binding.editTextUrlCode.getText().toString();
            Poll thisPoll = new Poll();
            thisPoll = getPoll(urlCode);

        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private Poll getPoll(String urlCode) {
        Poll poll = new Poll();

//        call.clone().enqueue(new Callback<Poll>() {
//            @Override
//            public void onResponse(Call<Poll> call, Response<Poll> response) {
//
//                if (!response.isSuccessful()) {
//                    System.out.println("Code: " + response.code());
//                    return;
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<Poll> call, Throwable t) {
//                System.out.println(t.getMessage());
//            }
//        });

        return poll;
    }
}
