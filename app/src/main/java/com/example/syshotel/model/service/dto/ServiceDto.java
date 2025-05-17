package com.example.syshotel.model.service.dto;

public class ServiceDto {
    private String serviceImage;
    private String serviceName;
    private String serviceDescription;
    private String serviceCategory;
    private Boolean isActive;
    private Double servicePrice;

    public ServiceDto(String serviceImage, String serviceName, String serviceDescription, String serviceCategory, Boolean isActive, Double servicePrice) {
        this.serviceImage = serviceImage;
        this.serviceName = serviceName;
        this.serviceDescription = serviceDescription;
        this.serviceCategory = serviceCategory;
        this.isActive = isActive;
        this.servicePrice = servicePrice;
    }

    public String getServiceImage() {
        return serviceImage;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public String getServiceCategory() {
        return serviceCategory;
    }

    public Boolean getActive() {
        return isActive;
    }

    public Double getServicePrice() {
        return servicePrice;
    }
}
