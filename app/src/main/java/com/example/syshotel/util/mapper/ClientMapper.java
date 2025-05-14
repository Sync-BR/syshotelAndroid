package com.example.syshotel.util.mapper;

import com.example.syshotel.model.client.dto.ClientDto;
import com.example.syshotel.model.client.dto.UserDto;
import com.example.syshotel.util.TextUtil;
import com.google.android.material.textfield.TextInputEditText;

public class ClientMapper {
    private final TextUtil utilText;

    public ClientMapper() {
        this.utilText = new TextUtil();
    }

    public ClientDto convertClientDto(String name, String cpf, String email, String phone, boolean statusAccount) {
        return new ClientDto(name, cpf.replaceAll("\\D", ""), email, phone.replaceAll("\\D", ""), statusAccount, null, null);
    }

    public UserDto convertUserDto(String username, String password){
        return new UserDto(username, password);
    }

    public ClientDto loadDateClient(TextInputEditText name, TextInputEditText cpf, TextInputEditText email, TextInputEditText phone) {
        return convertClientDto(
                utilText.safeText(name),
                utilText.safeText(cpf),
                utilText.safeText(email),
                utilText.safeText(phone),
                true
        );
    }
    public UserDto loadDateUser(TextInputEditText username, TextInputEditText password){
        return convertUserDto(utilText.safeText(username), utilText.safeText(password));
    }

}
