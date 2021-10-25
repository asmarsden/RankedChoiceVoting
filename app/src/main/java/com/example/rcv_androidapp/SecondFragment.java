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

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

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
        if (validateData()) {
            Poll newPoll = new Poll();
            newPoll.setQuestion(binding.editTextPollQuestion.getText().toString());
            String endDate = binding.editTextEndDate.getText().toString();
            String endTime = binding.editTextEndTime.getText().toString();
            newPoll.setEnd_time(endDate + "T" + endTime + ":00");
            String[] candidates = {binding.editTextCandidate1.getText().toString(), binding.editTextCandidate2.getText().toString()};
            newPoll.setCandidates(candidates);
            newPoll.setRequire_name(binding.checkBoxNameOption.isChecked());
            if (binding.checkBoxPasswordOption.isChecked()) {
                newPoll.setPassword(binding.editTextPassword.getText().toString());
            } else {
                newPoll.setPassword(null);
            }
            //POST

        }
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