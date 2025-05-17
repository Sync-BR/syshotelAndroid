package com.example.syshotel.ui.user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.syshotel.R;
import com.example.syshotel.resource.UserResource;
import com.example.syshotel.resource.service.UserServiceResource;
import com.example.syshotel.ui.home.HomeActivity;
import com.example.syshotel.util.TextUtil;
import com.example.syshotel.util.mapper.ClientMapper;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;

public class LoginActivity extends AppCompatActivity {
    private SharedPreferences preferences;
    private SwitchMaterial saveSession;

    private TextInputEditText username, password;
    private MaterialButton login, register;
    private UserServiceResource resource;
    private ClientMapper mapper;
    private TextUtil utilText;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        initializeUI();
        setupListeners();
        initializePreferences();
    }

    private void initializeUI() {
        this.username = findViewById(R.id.login_username);
        this.password = findViewById(R.id.login_password);
        this.login = findViewById(R.id.login_button);
        this.register = findViewById(R.id.login_button_register);
        this.saveSession = findViewById(R.id.login_rememberPassword);
        this.resource = new UserServiceResource(new UserResource(new Gson(), new OkHttpClient()), this);
        this.mapper = new ClientMapper();
        this.utilText = new TextUtil();
    }

    private void setupListeners() {
        login.setOnClickListener(v -> {
                    if (validateInput()) {
                        resource.login(mapper.convertUserDto(utilText.safeText(username), utilText.safeText(password)));
                        saveSessionConfig(utilText.safeText(username), utilText.safeText(password));
                    }
                }
        );
        register.setOnClickListener(v -> {
            Intent screenRegister = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(screenRegister);
            finish();
        });
    }
    private void cleanAllError(){
        username.setError(null);
        password.setError(null);
    }
    private boolean validateInput() {
        cleanAllError();
        if (utilText.safeText(username).isEmpty()) {
            username.setError("Prenchar com o nome de usuário.");
            return false;
        }
        if (utilText.safeText(password).isEmpty()) {
            password.setError("Prenchar com uma senha.");
            return false;
        }

        return true;
    }

    private void initializePreferences(){
        preferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        if (preferences.getBoolean("lembrar", false)) {
            username.setText(preferences.getString("username", ""));
            password.setText(preferences.getString("senha", ""));
            saveSession.setChecked(true);
        }
    }
    private void saveSessionConfig(String username, String password) {
        if (saveSession.isChecked()) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("username", username);
            editor.putString("senha", password);
            editor.putBoolean("lembrar", true);
            editor.apply();

        } else {
            preferences.edit().clear().apply();
        }
    }
}
