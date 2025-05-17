package com.example.syshotel.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.syshotel.R;
import com.example.syshotel.model.client.dto.ClientDto;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

public class HomeActivity extends AppCompatActivity {
    private TextView userName;
    private MaterialButton btnViewAccount;
    private MaterialCardView screenServices;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initializeUI();
        setupListeners();
        loadDate();
    }

    private void initializeUI() {
        this.userName = findViewById(R.id.admin_user_name);
        this.screenServices = findViewById(R.id.card_screen_services);

    }

    private void setupListeners() {
        screenServices.setOnClickListener(v -> {
            Intent screenResponseService = new Intent(HomeActivity.this, ServicesResponseActivity.class);
            startActivity(screenResponseService);
        });
    }

    private void loadDate() {
        ClientDto userLoad = getDateIntent();
        if (userLoad != null) {
            runOnUiThread(() -> {
                this.userName.setText("Olá, " + userLoad.getClientName());
            });
        }
    }

    private ClientDto getDateIntent() {
        Intent getDate = getIntent();
        return (ClientDto) getDate.getSerializableExtra("userData");

    }
}
