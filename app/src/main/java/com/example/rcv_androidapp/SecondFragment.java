package com.example.rcv_androidapp;

import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.rcv_androidapp.databinding.FragmentSecondBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private VotingApi votingApi;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        hideWarnings();

        binding.editTextPassword.setVisibility(View.GONE);
        binding.checkBoxPasswordOption.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    binding.editTextPassword.setVisibility(View.VISIBLE);
                } else {
                    binding.editTextPassword.setVisibility(View.GONE);
                }
            }
        });

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPoll();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void createPoll() {
        if (!validateData()) {
            return;
        }
        Poll newPoll = new Poll();
        newPoll.setCreatorId("temporaryCreatorID"); //few ways we could deal with create_id generation (finish later regardless)
        //no need to set url_code here. server will do that for us.
        newPoll.setQuestion(binding.editTextPollQuestion.getText().toString());
        String endDate = binding.editTextEndDate.getText().toString();
        String endTime = binding.editTextEndTime.getText().toString();
        newPoll.setEndTime(endDate + "T" + endTime + ":00");
        String[] candidates = {binding.editTextCandidate1.getText().toString(), binding.editTextCandidate2.getText().toString()};
        newPoll.setCandidates(candidates);
        newPoll.setRequireName(binding.checkBoxNameOption.isChecked());
        if (binding.checkBoxPasswordOption.isChecked()) {
            newPoll.setPassword(binding.editTextPassword.getText().toString());
        } else {
            newPoll.setPassword(null);
        }
        //POST
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        VotingApi votingApi = retrofit.create(VotingApi.class);
        Call<Poll> call = votingApi.createPoll(newPoll);

        call.clone().enqueue(new Callback<Poll>() {
            @Override
            public void onResponse(Call<Poll> call, Response<Poll> response) {

                if (!response.isSuccessful()) {
                    System.out.println("Code: " + response.code());
                    return;
                }

                Poll pollResponse = response.body();

                String content = "";
                content += "creatorId: " + pollResponse.getCreatorId() + "\n";
                content += "urlCode: " + pollResponse.getUrlCode() + "\n";
                content += "question: " + pollResponse.getQuestion() + "\n";
                content += "endTime: " + pollResponse.getEndTime() + "\n";
                content += "requireName?: " + pollResponse.isRequireName() + "\n";
                content += "requirePassword?: " + pollResponse.isRequirePassword() + "\n";
                content += "password: " + pollResponse.getPassword() + "\n";
                content += "candidates: " + "\n";
                for (String candidate : pollResponse.getCandidates()) {
                    content += candidate + "\n";
                }
                content += "\n";
                System.out.println(content);

            }

            @Override
            public void onFailure(Call<Poll> call, Throwable t) {
                System.out.println(t.getMessage());
                System.out.println("Are you running the Spring app?");
            }
        });
    }

    private boolean validateData() {
        hideWarnings();
        boolean anyErrors = false;

        if (binding.editTextPollQuestion.getText().toString().equals("")) {
            binding.txtPollQuestionWarning.setVisibility(View.VISIBLE);
            anyErrors = true;
        }
        if (binding.editTextEndDate.getText().toString().equals("")) {
            binding.txtEndDateWarning.setVisibility(View.VISIBLE);
            anyErrors = true;
        } else {
            //check for formatting
        }
        if (binding.editTextEndTime.getText().toString().equals("")) {
            binding.txtEndTimeWarning.setVisibility(View.VISIBLE);
            anyErrors = true;
        } else {
            //check for formatting
        }
        if (binding.editTextCandidate1.getText().toString().equals("")) {
            binding.txtCandidate1Warning.setVisibility(View.VISIBLE);
            anyErrors = true;
        }
        if (binding.editTextCandidate2.getText().toString().equals("")) {
            binding.txtCandidate2Warning.setVisibility(View.VISIBLE);
            anyErrors = true;
        }
        if (binding.checkBoxPasswordOption.isChecked() && binding.editTextPassword.getText().toString().equals("")) {
            binding.txtPasswordWarning.setVisibility(View.VISIBLE);
            anyErrors = true;
        }
        if (anyErrors) return false;
        else return true;
    }

    private void hideWarnings() {
        binding.txtPollQuestionWarning.setVisibility(View.GONE);
        binding.txtEndDateWarning.setVisibility(View.GONE);
        binding.txtEndTimeWarning.setVisibility(View.GONE);
        binding.txtCandidate1Warning.setVisibility(View.GONE);
        binding.txtCandidate2Warning.setVisibility(View.GONE);
        binding.txtPasswordWarning.setVisibility(View.GONE);
    }
}