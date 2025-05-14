package com.example.syshotel.util.validation.interfaces;

import com.example.syshotel.exception.ClientException;
import com.example.syshotel.model.address.dto.AddressDto;
import com.example.syshotel.model.client.dto.ClientDto;
import com.example.syshotel.model.client.dto.UserDto;

public interface RegisterValidationInterface {

    //Verificar dados do usuarios
    String checkClientDate(ClientDto validateDateClient) throws ClientException;
    //Verificar campo do endereço
    String verifyAddressData(AddressDto validateDateAddress) throws ClientException;
    //Verificar campos de usuarios
    String checkUserDate(UserDto validateDateUser) throws ClientException;
}
