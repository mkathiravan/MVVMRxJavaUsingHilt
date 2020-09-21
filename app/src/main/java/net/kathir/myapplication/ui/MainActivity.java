package net.kathir.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import net.kathir.myapplication.R;
import net.kathir.myapplication.databinding.ActivityMainBinding;
import net.kathir.myapplication.ui.fragments.Favorites;
import net.kathir.myapplication.ui.fragments.Home;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private boolean isFavoriteListVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new Home()).commit();

        binding.changeFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isFavoriteListVisible){
                    isFavoriteListVisible = false;
                    binding.changeFragment.setText("Favorites");
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new Home())
                            .commit();
                }
                else {
                    isFavoriteListVisible = true;
                    binding.changeFragment.setText("Home");
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new Favorites())
                            .commit();
                }
            }
        });
    }

}
