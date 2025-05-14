package com.example.syshotel.resource.interfaces;

import com.example.syshotel.model.client.dto.ClientDto;
import com.example.syshotel.model.client.dto.UserDto;

public interface LoginResourceInterface {
    void onSuccess(ClientDto user);
    void onError(String message);
}
