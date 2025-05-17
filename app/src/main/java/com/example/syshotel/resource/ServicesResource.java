package com.example.syshotel.resource;

import androidx.annotation.NonNull;

import com.example.syshotel.config.Config;
import com.example.syshotel.model.service.dto.ServiceDto;
import com.example.syshotel.resource.abstraction.ServicesResourceAbstraction;
import com.example.syshotel.resource.interfaces.ServicesResourceInterface;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ServicesResource implements ServicesResourceAbstraction {
    private final Config configRoute;
    private final OkHttpClient clientHttp;
    private final Gson gson;

    public ServicesResource(OkHttpClient clientHttp, Gson gson) {
        this.clientHttp = clientHttp;
        this.gson = gson;
        this.configRoute = new Config();
    }

    @Override
    public void getResponseServices(ServicesResourceInterface callback) {
        Request request = new Request.Builder()
                .url(configRoute.URL+Config.ConfigurationApi.getResponseServices)
                .get()
                .build();
        clientHttp.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onError("Erro ao se comunicar com servidor");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String json = response.body().string();
                    Type type = new TypeToken<List<ServiceDto>>() {}.getType();
                    List<ServiceDto> serviceList = gson.fromJson(json, type);
                    callback.onSuccess(serviceList);
                } else {
                    callback.onError("Erro ao buscar os serviços: " + response.message());
                }
            }
        });


    }
}
