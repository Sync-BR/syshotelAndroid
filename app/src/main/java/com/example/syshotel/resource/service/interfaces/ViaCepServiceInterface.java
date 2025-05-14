package com.example.syshotel.resource.service.interfaces;

import com.example.syshotel.model.address.dto.AddressDto;
import com.example.syshotel.resource.interfaces.CepResourceInterface;

public interface ViaCepServiceInterface {
    void searchCep(String cep, CepResourceInterface callback);

}
