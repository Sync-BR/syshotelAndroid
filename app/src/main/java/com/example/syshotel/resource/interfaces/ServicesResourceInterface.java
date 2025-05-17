package com.example.syshotel.resource.interfaces;

import com.example.syshotel.model.address.dto.AddressDto;
import com.example.syshotel.model.service.dto.ServiceDto;

import java.util.List;

public interface ServicesResourceInterface {
    void onSuccess(List<ServiceDto> service);
    void onError(String error);
}
