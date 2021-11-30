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

import com.example.rcv_androidapp.databinding.FragmentAdminViewBinding;

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
        Boolean isActive = sharedPreferences.getBoolean(name + "_isActive", false);
        binding.textView.setText(question);
        binding.btnCopyLink.setOnClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager)getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("link", "http://rankchoicevoting.herokuapp.com/add-ballot");
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
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}