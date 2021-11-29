package com.example.rcv_androidapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.rcv_androidapp.databinding.FragmentMenuBinding;

public class MenuFragment extends Fragment {

    private FragmentMenuBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentMenuBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnNewPoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MenuFragment.this)
                        .navigate(R.id.action_MenuFragment_to_CreatePollFragment);
            }
        });

        //load in all of the locally saved polls
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
        int totalPolls = sharedPreferences.getInt("totalPolls", 0);
        for (Integer i = totalPolls; i > 0; i--) {
            String name = "poll" + i.toString();
            boolean shouldShow = sharedPreferences.getBoolean(name, false);
            if (shouldShow) {
                String adminCode = sharedPreferences.getString(name + "_adminCode", "");
                String urlCode = sharedPreferences.getString(name + "_urlCode", "");
                String question = sharedPreferences.getString(name + "_question", "");
                Boolean isActive = sharedPreferences.getBoolean(name + "_isActive", false);

                //create card
                LinearLayout linearLayout = new LinearLayout(getContext());
                linearLayout.setMinimumHeight(dpAsPixels(90));
                linearLayout.setOrientation(LinearLayout.VERTICAL);

                //SpannableStringBuilder sb = new SpannableStringBuilder(question); //This is needed to make text bold or italicized
                //StyleSpan styleSpan = new StyleSpan(android.graphics.Typeface.BOLD);
                //sb.setSpan(styleSpan, 0, 4, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

                TextView pollTitle = new TextView(getContext());
                pollTitle.setTextSize(20);
                pollTitle.setText(question);
                linearLayout.addView(pollTitle);

                TextView activeStatus = new TextView(getContext());
                activeStatus.setTextSize(10);
                activeStatus.setText(isActive.toString());
                linearLayout.addView(activeStatus);

                View line = new View(getContext());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dpAsPixels(1));
                params.gravity = Gravity.BOTTOM;
                line.setLayoutParams(params);
                line.setBackgroundColor(0xFFD3D3D3);
                linearLayout.addView(line);

                LinearLayout pollList = (LinearLayout)binding.scrollView.getChildAt(0);
                pollList.addView(linearLayout);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public int dpAsPixels(int n) {
        float scale = getResources().getDisplayMetrics().density;
        return (int)(n*scale + 0.5f);
    }
}
