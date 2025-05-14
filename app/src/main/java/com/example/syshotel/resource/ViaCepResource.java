package com.example.syshotel.resource;



import androidx.annotation.NonNull;

import com.example.syshotel.config.Config;
import com.example.syshotel.model.address.dto.ViaCepDto;
import com.example.syshotel.resource.interfaces.CepResourceInterface;
import com.example.syshotel.util.mapper.AddressMapper;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ViaCepResource {
    private final Config configRoute;
    private final OkHttpClient clientHttp;
    private final Gson gson;
    private final AddressMapper mapper;

    public ViaCepResource(OkHttpClient clientHttp, Gson gson) {
        this.clientHttp = clientHttp;
        this.gson = gson;
        this.mapper = new AddressMapper();
        this.configRoute = new Config();
    }

    public void searchCep(String cep, CepResourceInterface callback){
        Request request = new Request.Builder()
                .url(configRoute.ViaCep+cep+"/json/")
                .get()
                .build();
        clientHttp.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onError("Erro ao se comunicar com servidor");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    callback.onSuccess(mapper.convertCepToAddressDto(gson.fromJson(response.body().string(), ViaCepDto.class)));
                }
            }
        });
    }
}
