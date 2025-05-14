package com.example.syshotel.util.validation;

import androidx.appcompat.app.AppCompatActivity;

import com.example.syshotel.exception.ClientException;
import com.example.syshotel.model.address.dto.AddressDto;
import com.example.syshotel.model.client.dto.ClientDto;
import com.example.syshotel.model.client.dto.UserDto;
import com.example.syshotel.util.validation.interfaces.RegisterValidationInterface;

public class RegisterValidation implements RegisterValidationInterface {
    public boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }



    @Override
    public String checkClientDate(ClientDto validateDateClient) throws ClientException {
        if (validateDateClient == null) return "Cliente inválido";
        if (!isNotEmpty(validateDateClient.getClientName())) return "Nome obrigatório";
        if (!isNotEmpty(validateDateClient.getClientCpf())) return "CPF obrigatório";
        if (!isNotEmpty(validateDateClient.getClientEmail())) return "E-mail obrigatório";
        if (!isNotEmpty(validateDateClient.getClientPhone())) return "Telefone obrigatório";
        return null;

    }

    @Override
    public String verifyAddressData(AddressDto validateDateAddress) throws ClientException {
        if (validateDateAddress == null) return "Endereço inválido";
        if (!isNotEmpty(validateDateAddress.getClientStreet())) return "Logradouro obrigatório";
        if (!isNotEmpty(validateDateAddress.getClientLocality())) return "Cidade obrigatória";
        if (!isNotEmpty(validateDateAddress.getClientState())) return "Estado obrigatório";
        if (!isNotEmpty(validateDateAddress.getClientNeighborhood())) return "Bairro obrigatório";
        if (!isNotEmpty(validateDateAddress.getClientPostalCode())) return "CEP obrigatório";
        return null;
    }

    @Override
    public String checkUserDate(UserDto validateDateUser) throws ClientException {
        if (validateDateUser == null) return "dados inválido";
        if (!isNotEmpty(validateDateUser.getUsername())) return "Usuário obrigatório";
        if (!isNotEmpty(validateDateUser.getPassword())) return "Senha obrigatória";
        return null;
    }

}
