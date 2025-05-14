package com.example.syshotel.ui.validation;



import com.example.syshotel.model.address.dto.AddressDto;
import com.example.syshotel.model.client.dto.ClientDto;
import com.example.syshotel.model.client.dto.UserDto;
import com.example.syshotel.util.validation.RegisterValidation;

class RegisterValidationTest {
    private RegisterValidation validate = new RegisterValidation();
    @org.junit.jupiter.api.Test
    void isNotEmpty() {
    }

    @org.junit.jupiter.api.Test
    void checkClientDate() {
        AddressDto address = new AddressDto("Rua Exemplo", "123", "Apto 1", "Bairro", "Cidade", "Estado");
        UserDto user = new UserDto("usuario", "senha123");

        ClientDto client = new ClientDto(
                "Eduardo",
                "12345678900",
                "eduardo@email.com",
                "71999999999",
                true,
                null,
                null
        );
        validate.checkClientDate(client);
    }

    @org.junit.jupiter.api.Test
    void verifyAddressData() {
    }

    @org.junit.jupiter.api.Test
    void checkUserDate() {
    }
}