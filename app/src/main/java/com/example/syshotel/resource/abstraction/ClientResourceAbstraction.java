package com.example.syshotel.resource.abstraction;

import com.example.syshotel.model.client.dto.ClientDto;
import com.example.syshotel.model.client.dto.UserDto;
import com.example.syshotel.resource.interfaces.ClientResourceInterface;
import com.example.syshotel.resource.interfaces.LoginResourceInterface;

public interface ClientResourceAbstraction {

    void register(ClientDto client, ClientResourceInterface callback);

    void authenticate(UserDto login, LoginResourceInterface callback);

}
