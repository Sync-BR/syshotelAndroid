package com.example.syshotel.resource.service.interfaces;

import com.example.syshotel.model.client.dto.ClientDto;
import com.example.syshotel.model.client.dto.UserDto;
import com.example.syshotel.resource.interfaces.LoginResourceInterface;

public interface UserServiceInterface {
    void login(UserDto user);
    void registerNewUser(ClientDto dto);
}
