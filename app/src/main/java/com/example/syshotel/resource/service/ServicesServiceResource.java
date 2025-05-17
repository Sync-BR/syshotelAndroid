package com.example.syshotel.resource.service;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.example.syshotel.model.service.dto.ServiceDto;
import com.example.syshotel.resource.ServicesResource;
import com.example.syshotel.resource.interfaces.ServicesResourceInterface;
import com.google.gson.Gson;

import java.util.List;

import okhttp3.OkHttpClient;

public class ServicesServiceResource {
    private final ServicesResource resource;
    private final Context context;

    public ServicesServiceResource(Context context) {
        this.resource = new ServicesResource(new OkHttpClient(), new Gson());
        this.context = context;
    }

    public void getAllResponseService(ServicesResourceInterface callback){
        resource.getResponseServices(new ServicesResourceInterface() {
            @Override
            public void onSuccess(List<ServiceDto> service) {
                resource.getResponseServices(callback);
            }

            @Override
            public void onError(String error) {
                new Handler(Looper.getMainLooper()).post(() ->
                        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                );
            }
        });
    }
}
