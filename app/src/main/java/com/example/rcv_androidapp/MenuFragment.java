package com.example.rcv_androidapp;

import android.app.ActionBar;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
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

        binding.btnNewPoll.setOnClickListener(view1 -> NavHostFragment.findNavController(MenuFragment.this)
                .navigate(R.id.action_MenuFragment_to_CreatePollFragment));

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
                boolean status = sharedPreferences.getBoolean(name + "_status", false);
                String winner = sharedPreferences.getString(name + "_winner", "");

                //create card
                LinearLayout linearLayout = new LinearLayout(getContext());
                LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, dpAsPixels(90));
                linearLayoutParams.gravity = Gravity.CENTER_VERTICAL;
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                linearLayout.setLayoutParams(linearLayoutParams);

                //SpannableStringBuilder sb = new SpannableStringBuilder(question); //This is needed to make text bold or italicized
                //StyleSpan styleSpan = new StyleSpan(android.graphics.Typeface.BOLD);
                //sb.setSpan(styleSpan, 0, 4, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

                TextView pollTitle = new TextView(getContext());
                pollTitle.setTextSize(30);
                pollTitle.setText(question);
                pollTitle.setTextColor(0xFF000000);
                LinearLayout.LayoutParams pollTitleParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                pollTitleParams.setMarginStart(dpAsPixels(15));
                pollTitleParams.gravity = Gravity.CENTER_VERTICAL;
                pollTitle.setLayoutParams(pollTitleParams);
                linearLayout.addView(pollTitle);

                TextView activeStatus = new TextView(getContext());
                activeStatus.setTextSize(20);
                if (status) {
                    activeStatus.setText("Active");
                } else {
                    activeStatus.setText("Winner: " + winner);
                }
                LinearLayout.LayoutParams activeStatusParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                activeStatusParams.setMarginStart(dpAsPixels(15));
                activeStatusParams.gravity = Gravity.CENTER_VERTICAL;
                activeStatus.setLayoutParams(activeStatusParams);
                linearLayout.addView(activeStatus);

                View line = new View(getContext());
                LinearLayout.LayoutParams lineParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, dpAsPixels(1));
                lineParams.gravity = Gravity.BOTTOM;
                line.setLayoutParams(lineParams);
                line.setBackgroundColor(0xFFD3D3D3);
                line.setLayoutParams(lineParams);
                linearLayout.addView(line);

                final Bundle bundle = new Bundle();
                bundle.putString("name", name);
                linearLayout.setOnClickListener(view1 -> NavHostFragment.findNavController(MenuFragment.this)
                        .navigate(R.id.action_MenuFragment_to_AdminViewFragment, bundle));

                binding.pollList.addView(linearLayout);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private int dpAsPixels(int n) {
        float scale = getResources().getDisplayMetrics().density;
        return (int)(n*scale + 0.5f);
    }
}
