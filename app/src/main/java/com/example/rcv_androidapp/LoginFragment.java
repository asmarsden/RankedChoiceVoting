package com.example.rcv_androidapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.rcv_androidapp.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //I remove "android:onClick="gotoCreateAccount"" from the XML
        //not sure if this works yet still need to test
        binding.txtCreateAccount.setOnClickListener(view1 -> {
//                NavHostFragment.findNavController(LoginFragment.this)
//                        .navigate(R.id.action_LoginFragment_to_CreateAccountFragment);
//                        .navigate(R.id.action_MenuFragment_to_CreatePollFragment);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
