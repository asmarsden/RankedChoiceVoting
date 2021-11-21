package com.example.rcv_androidapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.rcv_androidapp.databinding.FragmentCreatePollBinding;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreatePollFragment extends Fragment {

    private FragmentCreatePollBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentCreatePollBinding.inflate(inflater, container, false);

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        createCandidate();
        createCandidate();
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

        binding.btnAddCandidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createCandidate();
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
        newPoll.setCreatorId("temporaryCreatorID"); //few ways we could deal with createId generation (finish later regardless)
        //no need to set urlCode here. server will do that for us.
        newPoll.setQuestion(binding.editTextPollQuestion.getText().toString());
//        String endDate = binding.editTextEndDate.getText().toString();
//        String endTime = binding.editTextEndTime.getText().toString();
//        newPoll.setEndTime(endDate + "T" + endTime + ":00");
        String[] candidates = new String[binding.tableLayout.getChildCount()];
        for(int i = 0; i < binding.tableLayout.getChildCount(); i++) {
            TableRow tableRow = (TableRow)binding.tableLayout.getChildAt(i);
            EditText editText = (EditText)tableRow.getChildAt(0);
            candidates[i] = editText.getText().toString();
        }
        newPoll.setCandidates(candidates);
        newPoll.setRequireName(binding.checkBoxNameOption.isChecked());
        if (binding.checkBoxPasswordOption.isChecked()) {
            newPoll.setPassword(binding.editTextPassword.getText().toString());
        } else {
            newPoll.setPassword(null);
        }
        //POST
        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://10.0.2.2:8080/") //local
                .baseUrl("http://rankchoicevoting.herokuapp.com/") //live
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
                content += "isActive?: " + pollResponse.isActive() + "\n";
//                content += "endTime: " + pollResponse.getEndTime() + "\n";
                content += "requireName?: " + pollResponse.isRequireName() + "\n";
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
//                System.out.println("Are you running the Spring app?");
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
//        if (binding.editTextEndDate.getText().toString().equals("")) {
//            binding.txtEndDateWarning.setVisibility(View.VISIBLE);
//            anyErrors = true;
//        } else {
//            //check for formatting
//        }
//        if (binding.editTextEndTime.getText().toString().equals("")) {
//            binding.txtEndTimeWarning.setVisibility(View.VISIBLE);
//            anyErrors = true;
//        } else {
//            //check for formatting
//        }
        if (binding.tableLayout.getChildCount() < 2) {
            binding.txtCandidatesWarning.setVisibility(View.VISIBLE);
            anyErrors = true;
        }
        for (int i = 0; i < binding.tableLayout.getChildCount(); i++) {
            TableRow tableRow = (TableRow)binding.tableLayout.getChildAt(i);
            EditText editText = (EditText)tableRow.getChildAt(0);
            if (editText.getText().toString().equals("")) {
                TextView textView = (TextView)tableRow.getChildAt(2);
                textView.setVisibility(View.VISIBLE);
                anyErrors = true;
            }
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
//        binding.txtEndDateWarning.setVisibility(View.GONE);
//        binding.txtEndTimeWarning.setVisibility(View.GONE);
        binding.txtCandidatesWarning.setVisibility(View.GONE);
        for (int i = 0; i < binding.tableLayout.getChildCount(); i++) {
            TableRow tableRow = (TableRow)binding.tableLayout.getChildAt(i);
            TextView textView = (TextView)tableRow.getChildAt(2);
            textView.setVisibility(View.GONE);
        }
        binding.txtPasswordWarning.setVisibility(View.GONE);
    }

    private TableRow createCandidate() {
        TableLayout layout = binding.tableLayout;

        TableRow newTableRow = new TableRow(getContext()); //just putting "getContext()" might not work.
        newTableRow.setGravity(Gravity.CENTER_VERTICAL);

        TextInputEditText newEditText = new TextInputEditText(getContext());
        newEditText.setEms(10);
        newEditText.setHint("Candidate");
        newTableRow.addView(newEditText);

        ImageButton newImageBtn = new ImageButton(getContext());
        newImageBtn.setImageResource(R.drawable.redx);
        newImageBtn.setBackgroundColor(0x00FFFFFF);
        newImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.removeView(newTableRow);
            }
        });
        newTableRow.addView(newImageBtn);

        TextView newTxtView = new TextView(getContext());
        newTxtView.setText("Candidate Name\nRequired");
        newTxtView.setTextColor(Color.RED);
        float scale = getResources().getDisplayMetrics().density;
        int dpAsPixels = (int) (8*scale + 0.5f);
        newTxtView.setPadding(dpAsPixels,0,0,0);
        newTableRow.addView(newTxtView);
        newTxtView.setVisibility(View.GONE);

        layout.addView(newTableRow);
        return newTableRow;
    }
}
