package com.example.rcv_androidapp;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.rcv_androidapp.databinding.ActivityMainBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private TextView textViewResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        //------------
        //setContentView(R.layout.fragment_first);

        //textViewResult = findViewById(R.id.textview_first);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://localhost:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        VotingApi votingApi = retrofit.create(VotingApi.class);

        Call<List<Poll>> call = votingApi.getPolls();

        call.enqueue(new Callback<List<Poll>>() {

            @Override
            public void onResponse(Call<List<Poll>> call, Response<List<Poll>> response) {

                if(!response.isSuccessful()) {
                    //textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<Poll> polls = response.body();

                for(Poll poll : polls) {

                    String content = "";
                    content += "poll_id: " + poll.getPoll_id() + "\n";
                    content += "creator_id: " + poll.getCreator_id() + "\n";
                    content += "question: " + poll.getQuestion() + "\n";
                    content += "require_name?: " + poll.isRequire_name() + "\n";
                    content += "password: " + poll.getPassword() + "\n";
                    content += "candidates: " + poll.getCandidates() + "\n";
                    content += "ballots: " + poll.getBallots() + "\n\n";

                    //textViewResult.append(content);
                    System.out.println(content);
                }
            }

            @Override
            public void onFailure(Call<List<Poll>> call, Throwable t) {
                //textViewResult.setText(t.getMessage());
                System.out.println(t.getMessage());
            }
        });
        //------------





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
