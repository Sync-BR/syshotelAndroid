package com.example.syshotel.resource;


import androidx.annotation.NonNull;


import com.example.syshotel.config.Config;
import com.example.syshotel.model.client.dto.ClientDto;
import com.example.syshotel.model.client.dto.UserDto;
import com.example.syshotel.resource.abstraction.ClientResourceAbstraction;
import com.example.syshotel.resource.interfaces.ClientResourceInterface;
import com.example.syshotel.resource.interfaces.LoginResourceInterface;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UserResource implements ClientResourceAbstraction {
    private final Config routeConfig;
    private final Gson gson;
    private final OkHttpClient clientHttp;

    public UserResource(Gson gson, OkHttpClient clientHttp) {
        this.gson = gson;
        this.clientHttp = clientHttp;
        routeConfig = new Config();
    }


    @Override
    public void register(ClientDto client, ClientResourceInterface callback) {
        RequestBody body = RequestBody.create(gson.toJson(client), MediaType.get("application/json"));
        Request request = new Request.Builder()
                .url(routeConfig.URL + Config.ConfigurationApi.routeClient)
                .post(body)
                .build();
        clientHttp.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onError("Erro na conexão, tente novamente mais tarde!");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body().string());
                } else {
                    callback.onError(response.body().string());

                }
            }
        });
    }

    @Override
    public void authenticate(UserDto login, LoginResourceInterface callback) {
        RequestBody body = RequestBody.create(gson.toJson(login), MediaType.get("application/json"));
        Request request = new Request.Builder()
                .url(routeConfig.URL + Config.ConfigurationApi.routeLogin)
                .post(body)
                .build();
        clientHttp.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onError("Erro de conexão");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    callback.onSuccess(gson.fromJson(response.body().string(), ClientDto.class));
                } else {
                    callback.onError(response.body().string());
                }
            }
        });
    }
}
