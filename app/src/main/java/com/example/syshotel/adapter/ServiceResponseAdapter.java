package com.example.syshotel.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.syshotel.R;
import com.example.syshotel.model.service.dto.ServiceDto;
import com.example.syshotel.util.Base64Util;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class ServiceResponseAdapter extends RecyclerView.Adapter<ServiceResponseAdapter.ServiceViewHolder> {

    private List<ServiceDto> services;
    private OnServiceClickListener listener;
    private Base64Util base64;

    public interface OnServiceClickListener {
        void onServiceClick(ServiceDto service);
    }

    public ServiceResponseAdapter(List<ServiceDto> services, OnServiceClickListener listener) {
        this.services = services;
        this.listener = listener;
    }

    private Bitmap convertImgToBipmap(String img) {

        if (img != null && !img.isEmpty()) {
            byte[] imageBytes = base64.decodeImage(img);
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            return bitmap;
        }
        return null;
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_service, parent, false);
        return new ServiceViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {
        this.base64 = new Base64Util();
        ServiceDto service = services.get(position);
        holder.title.setText(service.getServiceName());
        holder.description.setText(service.getServiceDescription());
        holder.price.setText("R$ " + String.valueOf(service.getServicePrice()));
        holder.icon.setImageBitmap(convertImgToBipmap(service.getServiceImage()));
        holder.button.setText("Solicitar");
//        holder.button.setOnClickListener(v -> {
//            if (listener != null) {
//                listener.onServiceClick(service);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return services.size();
    }

    static class ServiceViewHolder extends RecyclerView.ViewHolder {
        TextView title, description, price;
        ImageView icon;
        MaterialButton button;

        public ServiceViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.service_title);
            description = itemView.findViewById(R.id.service_description);
            price = itemView.findViewById(R.id.service_price);
            icon = itemView.findViewById(R.id.service_icon);
            button = itemView.findViewById(R.id.service_button);
        }
    }
}
