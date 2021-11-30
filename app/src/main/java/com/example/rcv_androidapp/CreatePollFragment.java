package com.example.rcv_androidapp;

import android.content.Context;
import android.content.SharedPreferences;
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
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreatePollFragment extends Fragment {

    private FragmentCreatePollBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
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
        binding.checkBoxPasswordOption.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                binding.editTextPassword.setVisibility(View.VISIBLE);
            } else {
                binding.editTextPassword.setVisibility(View.GONE);
            }
        });

        binding.btnAddCandidate.setOnClickListener(v -> createCandidate());

        binding.btnSubmit.setOnClickListener(view1 -> createPoll());
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
        newPoll.setAdminCode(generateRandomAdminCode()); //few ways we could deal with createId generation (finish later regardless)
        //no need to set urlCode here. server will do that for us.
        newPoll.setQuestion(binding.editTextPollQuestion.getText().toString());
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
                .baseUrl("http://10.0.2.2:8080/") //local
//                .baseUrl("http://rankchoicevoting.herokuapp.com/") //live
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        VotingApi votingApi = retrofit.create(VotingApi.class);
        Call<Poll> call = votingApi.createPoll(newPoll);

        call.clone().enqueue(new Callback<Poll>() {
            @Override
            public void onResponse(@NonNull Call<Poll> call, @NonNull Response<Poll> response) {

                if (!response.isSuccessful()) {
                    System.out.println("Code: " + response.code());
                    return;
                }

                Poll pollResponse = response.body();

                //save poll to local data
                SharedPreferences sharedPreferencesLoader = getContext().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
                Integer totalPolls = sharedPreferencesLoader.getInt("totalPolls", 0);
                totalPolls++;

                SharedPreferences sharedPreferencesSaver = getContext().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferencesSaver.edit();

                editor.putInt("totalPolls", totalPolls); //make sure this overwrites(?)
                String name = "poll" + totalPolls.toString();
                System.out.println(name);
                editor.putBoolean(name, true); //whether or not it exists anymore
                editor.putString(name + "_adminCode", pollResponse.getAdminCode());
                editor.putString(name + "_urlCode", pollResponse.getUrlCode());
                editor.putString(name + "_question", pollResponse.getQuestion());
                editor.putBoolean(name + "_isActive", pollResponse.isActive());

                editor.apply();

//                String content = "";
//                content += "adminCode: " + pollResponse.getAdminCode() + "\n";
//                content += "urlCode: " + pollResponse.getUrlCode() + "\n";
//                content += "question: " + pollResponse.getQuestion() + "\n";
//                content += "isActive?: " + pollResponse.isActive() + "\n";
//                content += "requireName?: " + pollResponse.isRequireName() + "\n";
//                content += "password: " + pollResponse.getPassword() + "\n";
//                content += "candidates: " + "\n";
//                for (String candidate : pollResponse.getCandidates()) {
//                    content += candidate + "\n";
//                }
//                content += "\n";
//                System.out.println(content);
            }

            @Override
            public void onFailure(@NonNull Call<Poll> call, @NonNull Throwable t) {
                System.out.println(t.getMessage());
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
        return !anyErrors;
    }

    private void hideWarnings() {
        binding.txtPollQuestionWarning.setVisibility(View.GONE);
        binding.txtCandidatesWarning.setVisibility(View.GONE);
        for (int i = 0; i < binding.tableLayout.getChildCount(); i++) {
            TableRow tableRow = (TableRow)binding.tableLayout.getChildAt(i);
            TextView textView = (TextView)tableRow.getChildAt(2);
            textView.setVisibility(View.GONE);
        }
        binding.txtPasswordWarning.setVisibility(View.GONE);
    }

    private TableRow createCandidate() {
        TableLayout tableLayout = binding.tableLayout;

        TableRow newTableRow = new TableRow(getContext()); //just putting "getContext()" might not work.
        newTableRow.setGravity(Gravity.CENTER_VERTICAL);

        TextInputEditText newEditText = new TextInputEditText(getContext());
        newEditText.setEms(10);
        newEditText.setHint("Candidate");
        newTableRow.addView(newEditText);

        ImageButton newImageBtn = new ImageButton(getContext());
        newImageBtn.setImageResource(R.drawable.redx);
        newImageBtn.setBackgroundColor(0x00FFFFFF);
        newImageBtn.setOnClickListener(v -> tableLayout.removeView(newTableRow));
        newTableRow.addView(newImageBtn);

        TextView newTxtView = new TextView(getContext());
        newTxtView.setText("Candidate Name\nRequired");
        newTxtView.setTextColor(Color.RED);
        float scale = getResources().getDisplayMetrics().density;
        int dpAsPixels = (int) (8*scale + 0.5f);
        newTxtView.setPadding(dpAsPixels,0,0,0);
        newTableRow.addView(newTxtView);
        newTxtView.setVisibility(View.GONE);

        tableLayout.addView(newTableRow);



        return newTableRow;
    }

    private String generateRandomAdminCode() { // difference is poll = length 8, vote length 10
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 8;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        //System.out.println(generatedString);
        return generatedString;
    }
}
