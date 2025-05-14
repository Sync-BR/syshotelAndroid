package com.example.syshotel.resource.service;

import android.content.Context;
import android.util.Log;


import com.example.syshotel.model.address.dto.AddressDto;
import com.example.syshotel.resource.ViaCepResource;
import com.example.syshotel.resource.interfaces.CepResourceInterface;
import com.example.syshotel.resource.service.interfaces.ViaCepServiceInterface;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;

public class ViaCepServiceResource implements ViaCepServiceInterface {

    private final ViaCepResource resource;

    public ViaCepServiceResource() {
        this.resource = new ViaCepResource(new OkHttpClient(), new Gson());
    }

    @Override
    public void searchCep(String cep,  CepResourceInterface callback) {
        resource.searchCep(cep, new CepResourceInterface() {
            @Override
            public void onSuccess(AddressDto address) {
                callback.onSuccess(address);
            }

            @Override
            public void onError(String error) {
               callback.onError(error);
            }
        });
    }
}
