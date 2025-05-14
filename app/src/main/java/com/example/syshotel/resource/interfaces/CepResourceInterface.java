package com.example.syshotel.resource.interfaces;

import com.example.syshotel.model.address.dto.AddressDto;

public interface CepResourceInterface {
    void onSuccess(AddressDto address);
    void onError(String error);
}
