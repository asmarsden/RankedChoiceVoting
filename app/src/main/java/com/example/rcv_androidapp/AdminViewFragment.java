package com.example.rcv_androidapp;

import android.content.ClipData;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.ClipboardManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.rcv_androidapp.databinding.FragmentAdminViewBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdminViewFragment extends Fragment {

    private FragmentAdminViewBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentAdminViewBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //load info in
        final String name = getArguments().getString("name");
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
        String adminCode = sharedPreferences.getString(name + "_adminCode", "");
        String urlCode = sharedPreferences.getString(name + "_urlCode", "");
        String question = sharedPreferences.getString(name + "_question", "");
        boolean status = sharedPreferences.getBoolean(name + "_status", false);
        String winner = sharedPreferences.getString(name + "_winner", "");
        binding.textView.setText(question);
        binding.btnCopyLink.setOnClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager)getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("link", "http://rankchoicevoting.herokuapp.com");
            clipboard.setPrimaryClip(clip);
            Toast toast = Toast.makeText(getContext(), "Coped to clipboard", Toast.LENGTH_SHORT);
            toast.show();
        });
        binding.editTextUrlCode.setText(urlCode);
        binding.btnCopyUrlCode.setOnClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager)getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("code", urlCode);
            clipboard.setPrimaryClip(clip);
            Toast toast = Toast.makeText(getContext(), "Coped to clipboard", Toast.LENGTH_SHORT);
            toast.show();
        });
        binding.btnEndPoll.setOnClickListener(v -> {
            endPoll(urlCode, adminCode, name);
        });
        binding.btnDeletePoll.setOnClickListener(v -> {
            //delete poll
            SharedPreferences sharedPreferencesSaver = getContext().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferencesSaver.edit();
            editor.putBoolean(name, false);
            editor.apply();
            NavHostFragment.findNavController(AdminViewFragment.this)
                    .navigate(R.id.action_AdminViewFragment_to_MenuFragment);
        });
        if(status) {
            binding.btnDeletePoll.setVisibility(View.GONE);
        } else {
            binding.btnEndPoll.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void endPoll(String urlCode, String adminCode, String name) {
        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://10.0.2.2:8080/") //local
                .baseUrl("http://rankchoicevoting.herokuapp.com/") //live
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        VotingApi votingApi = retrofit.create(VotingApi.class);
        Call<Poll> call = votingApi.endPoll(urlCode, adminCode);

        call.clone().enqueue(new Callback<Poll>() {
            @Override
            public void onResponse(@NonNull Call<Poll> call, @NonNull Response<Poll> response) {

                if (!response.isSuccessful()) {
                    System.out.println("Code: " + response.code());
                    return;
                }

                Poll pollResponse = response.body();

                //Get and store winner
                String winner = pollResponse.getWinner();
                //Make poll inactive
                SharedPreferences sharedPreferencesSaver = getContext().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferencesSaver.edit();
                editor.putBoolean(name + "_status", false);
                editor.putString(name + "_winner", winner);
                editor.apply();
            }

            @Override
            public void onFailure(@NonNull Call<Poll> call, @NonNull Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    private void loadPage() {

    }
}
