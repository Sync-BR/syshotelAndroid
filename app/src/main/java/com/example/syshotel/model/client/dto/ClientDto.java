package com.example.syshotel.model.client.dto;

import com.example.syshotel.model.address.dto.AddressDto;

import java.io.Serializable;

public class ClientDto implements Serializable {
    private  String clientName;
    private  String clientCpf;
    private  String clientEmail;
    private  String clientPhone;
    private  Boolean isActive;
    private  AddressDto address;
    private  UserDto clientUser;

    public ClientDto(String clientName, String clientCpf, String clientEmail, String clientPhone, Boolean isActive, AddressDto address, UserDto clientUser) {
        this.clientName = clientName;
        this.clientCpf = clientCpf;
        this.clientEmail = clientEmail;
        this.clientPhone = clientPhone;
        this.isActive = isActive;
        this.address = address;
        this.clientUser = clientUser;
    }

    @Override
    public String toString() {
        return "ClientDto{" +
                "clientName='" + clientName + '\'' +
                ", clientCpf='" + clientCpf + '\'' +
                ", clientEmail='" + clientEmail + '\'' +
                ", clientPhone='" + clientPhone + '\'' +
                ", isActive=" + isActive +
                ", address=" + address +
                ", clientUser=" + clientUser +
                '}';
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientCpf() {
        return clientCpf;
    }

    public void setClientCpf(String clientCpf) {
        this.clientCpf = clientCpf;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public UserDto getClientUser() {
        return clientUser;
    }

    public void setClientUser(UserDto clientUser) {
        this.clientUser = clientUser;
    }
}
