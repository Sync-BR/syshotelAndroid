package com.example.syshotel.ui.home;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.syshotel.R;
import com.example.syshotel.adapter.ServiceResponseAdapter;
import com.example.syshotel.model.service.dto.ServiceDto;
import com.example.syshotel.resource.interfaces.ServicesResourceInterface;
import com.example.syshotel.resource.service.ServicesServiceResource;

import java.util.ArrayList;
import java.util.List;

public class ServicesResponseActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ServiceResponseAdapter adapter;
    private List<ServiceDto> servicesList;
    private ServicesServiceResource resource;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_request_service);
        initializeUI();
        configAdapter();
        loadServices();
    }

    private void initializeUI(){
        this.recyclerView = findViewById(R.id.services_recycler);
        this.resource = new ServicesServiceResource(this);
        this.servicesList = new ArrayList<>();

    }
    private void configAdapter(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ServiceResponseAdapter(servicesList, service -> {
            Toast.makeText(this, "Clicou em: " + service.getServiceName(), Toast.LENGTH_SHORT).show();
        });

        recyclerView.setAdapter(adapter);
    }

    private void loadServices() {
        resource.getAllResponseService(new ServicesResourceInterface() {
            @Override
            public void onSuccess(List<ServiceDto> services) {
                runOnUiThread(() -> {
                    if (services != null && !services.isEmpty()) {
                        servicesList.clear();
                        servicesList.addAll(services);
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(ServicesResponseActivity.this, "Nenhum serviço encontrado.", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onError(String error) {
                runOnUiThread(() ->
                        Toast.makeText(ServicesResponseActivity.this, error, Toast.LENGTH_SHORT).show());
            }
        });
    }
}
