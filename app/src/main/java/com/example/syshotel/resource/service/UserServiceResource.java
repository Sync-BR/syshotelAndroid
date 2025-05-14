package com.example.syshotel.resource.service;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.example.syshotel.model.client.dto.ClientDto;
import com.example.syshotel.model.client.dto.UserDto;
import com.example.syshotel.resource.UserResource;
import com.example.syshotel.resource.interfaces.ClientResourceInterface;
import com.example.syshotel.resource.interfaces.LoginResourceInterface;
import com.example.syshotel.resource.service.interfaces.UserServiceInterface;
import com.example.syshotel.ui.user.LoginActivity;
import com.example.syshotel.ui.user.RegisterActivity;

public class UserServiceResource implements UserServiceInterface {

    private final UserResource resource;
    private final Context context;

    public UserServiceResource(UserResource resource, Context context) {
        this.resource = resource;
        this.context = context;
    }


    @Override
    public void login(UserDto user) {
        resource.authenticate(user, new LoginResourceInterface() {
            @Override
            public void onSuccess(ClientDto user) {
                new Handler(Looper.getMainLooper()).post(() -> {
                    Intent screenHome = new Intent(context, RegisterActivity.class);
                    context.startActivity(screenHome);
                    ((Activity) context).finish();
                    Toast.makeText(context, "Autenticado com sucesso!", Toast.LENGTH_SHORT).show();
                });

            }

            @Override
            public void onError(String message) {
                new Handler(Looper.getMainLooper()).post(() ->
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                );
            }
        });
    }

    @Override
    public void registerNewUser(ClientDto dto) {
        resource.register(dto, new ClientResourceInterface() {
            @Override
            public void onSuccess(String message) {
                new Handler(Looper.getMainLooper()).post(() -> {
                            Intent screenLogin = new Intent(context, LoginActivity.class);
                            context.startActivity(screenLogin);
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                            ((Activity) context).finish();
                        }
                );
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
