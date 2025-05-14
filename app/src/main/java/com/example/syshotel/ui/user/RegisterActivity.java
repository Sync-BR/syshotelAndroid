package com.example.syshotel.ui.user;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.syshotel.R;
import com.example.syshotel.model.address.dto.AddressDto;
import com.example.syshotel.model.client.dto.ClientDto;
import com.example.syshotel.resource.UserResource;
import com.example.syshotel.resource.interfaces.CepResourceInterface;
import com.example.syshotel.resource.service.UserServiceResource;
import com.example.syshotel.resource.service.ViaCepServiceResource;
import com.example.syshotel.util.TextUtil;
import com.example.syshotel.util.mapper.AddressMapper;
import com.example.syshotel.util.mapper.ClientMapper;
import com.example.syshotel.util.mask.MaskUtil;
import com.example.syshotel.util.validation.RegisterValidation;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;

public class RegisterActivity extends AppCompatActivity {
    private UserServiceResource userService;
    private ViaCepServiceResource cepService;
    private RegisterValidation validation;
    private ClientMapper clientMapper;
    private AddressMapper addressMapper;
    private TextInputLayout cepSearch;
    private TextUtil utilText;
    private TextInputEditText name, cpf, email, phone, street, cep, complement, neighborhood, city, state, user, password, repeatPassword;
    private SwitchMaterial accept_terms;
    private Button save, cancel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        initializeUI();
        setupListeners();
        initializeMask();
    }

    private void initializeUI() {
        this.name = findViewById(R.id.register_name);
        this.cpf = findViewById(R.id.register_cpf);
        this.email = findViewById(R.id.register_email);
        this.phone = findViewById(R.id.register_phone);
        this.street = findViewById(R.id.register_houser_address);
        this.cep = findViewById(R.id.register_house_cep);
        this.complement = findViewById(R.id.register_house_complement);
        this.neighborhood = findViewById(R.id.register_house_neighborhood);
        this.city = findViewById(R.id.register_house_city);
        this.state = findViewById(R.id.register_houser_state);
        this.user = findViewById(R.id.register_user_username);
        this.password = findViewById(R.id.register_user_password);
        this.repeatPassword = findViewById(R.id.register_user_repeat_password);
        this.accept_terms = findViewById(R.id.terms);
        this.save = findViewById(R.id.btn_user_save);
        this.cancel = findViewById(R.id.btn_user_cancel);
        this.cepSearch = findViewById(R.id.search_cep_button);

        //Inicialização de objetos
        this.userService = new UserServiceResource(new UserResource(new Gson(), new OkHttpClient()), this);
        this.cepService = new ViaCepServiceResource();
        this.validation = new RegisterValidation();
        this.addressMapper = new AddressMapper();
        this.clientMapper = new ClientMapper();
        this.utilText = new TextUtil();
    }

    private void initializeMask() {
        this.cpf.addTextChangedListener(MaskUtil.maskCpf(cpf));
        this.cep.addTextChangedListener(MaskUtil.maskCep(cep));
        this.phone.addTextChangedListener(MaskUtil.maskPhone(phone));
    }

    private void setupListeners() {
        cepSearch.setEndIconOnClickListener(v -> {
            cepService.searchCep(utilText.safeText(cep), new CepResourceInterface() {
                @Override
                public void onSuccess(AddressDto address) {
                    if(address == null){
                        runOnUiThread(()-> Toast.makeText(RegisterActivity.this, "Cep não encontrado!", Toast.LENGTH_LONG).show());
                    }
                    fillAddress(address);
                }

                @Override
                public void onError(String error) {
                    runOnUiThread(()-> Toast.makeText(RegisterActivity.this, error, Toast.LENGTH_LONG).show());

                }
            });
        });

        save.setOnClickListener(v -> {
            if (accept_terms.isChecked()) {
                if (checkInput()) {
                    runOnUiThread(() -> {
                        userService.registerNewUser(loadDateClient());
                    });
                }
            } else {
                Toast.makeText(this, "Aceite os termos para continuar", Toast.LENGTH_SHORT).show();
            }
        });

        cancel.setOnClickListener(v -> {
            Intent screenLogin = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(screenLogin);
            finish();
        });


    }

    private void clearAllErrors() {
        name.setError(null);
        cpf.setError(null);
        email.setError(null);
        phone.setError(null);
        cep.setError(null);
        street.setError(null);
        complement.setError(null);
        city.setError(null);
        state.setError(null);
        neighborhood.setError(null);
        user.setError(null);
        password.setError(null);
        repeatPassword.setError(null);
    }

    private boolean checkInput() {
        boolean isValid = true;
        String clientError = validation.checkClientDate(loadDateClient());
        String addressError = validation.verifyAddressData(loadDateClient().getAddress());
        String userError = validation.checkUserDate(loadDateClient().getClientUser());

        clearAllErrors();

        if (clientError != null) {
            if (clientError.contains("Nome")) name.setError(clientError);
            if (clientError.contains("CPF")) cpf.setError(clientError);
            if (clientError.contains("E-mail")) email.setError(clientError);
            if (clientError.contains("Telefone")) phone.setError(clientError);
            isValid = false;
        }

        if (addressError != null) {
            if (addressError.contains("CEP")) cep.setError(addressError);
            if (addressError.contains("Logradouro")) street.setError(addressError);
            if (addressError.contains("Cidade")) city.setError(addressError);
            if (addressError.contains("Complemento")) complement.setError(addressError);
            if (addressError.contains("Estado")) state.setError(addressError);
            if (addressError.contains("Bairro")) neighborhood.setError(addressError);
            isValid = false;
        }

        if (userError != null) {
            if (userError.contains("Usuário")) user.setError(userError);
            if (userError.contains("Senha")) password.setError(userError);
            isValid = false;
        }

        String pass = utilText.safeText(password);
        String repeat = utilText.safeText(repeatPassword);

        if (repeat.isEmpty()) {
            repeatPassword.setError("Preenchar o campo");
            isValid = false;
        } else if (!pass.equals(repeat)) {
            repeatPassword.setError("A senha repetida precisa ser igual à senha digitada");
            isValid = false;
        }


        return isValid;
    }

    private ClientDto loadDateClient() {
        ClientDto clientLoad = clientMapper.loadDateClient(name, cpf, email, phone);
        clientLoad.setAddress(addressMapper.loadAddress(street, complement, neighborhood, city, state, cep));
        clientLoad.setClientUser(clientMapper.loadDateUser(user, password));

        return clientLoad;
    }

    private void fillAddress(AddressDto address) {
        runOnUiThread(() -> {
            this.street.setText(address.getClientStreet());
            this.cep.setText(address.getClientPostalCode());
            this.complement.setText(address.getClientComplement());
            this.neighborhood.setText(address.getClientNeighborhood());
            this.city.setText(address.getClientLocality());
            this.state.setText(address.getClientState());
        });
    }
}