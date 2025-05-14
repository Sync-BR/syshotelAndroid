package com.example.syshotel.model.address.dto;

public class AddressDto {
    private  String clientStreet;
    private  String clientComplement;
    private  String clientNeighborhood;
    private  String clientLocality;
    private  String clientState;
    private  String clientPostalCode;



    public AddressDto(String clientStreet, String clientComplement, String clientNeighborhood, String clientLocality, String clientState, String clientPostalCode) {
        this.clientStreet = clientStreet;
        this.clientComplement = clientComplement;
        this.clientNeighborhood = clientNeighborhood;
        this.clientLocality = clientLocality;
        this.clientState = clientState;
        this.clientPostalCode = clientPostalCode;
    }

    public AddressDto() {
    }

    @Override
    public String toString() {
        return "AddressDto{" +
                "clientStreet='" + clientStreet + '\'' +
                ", clientComplement='" + clientComplement + '\'' +
                ", clientNeighborhood='" + clientNeighborhood + '\'' +
                ", clientLocality='" + clientLocality + '\'' +
                ", clientState='" + clientState + '\'' +
                ", clientPostalCode='" + clientPostalCode + '\'' +
                '}';
    }

    public String getClientStreet() {
        return clientStreet;
    }

    public String getClientComplement() {
        return clientComplement;
    }

    public String getClientNeighborhood() {
        return clientNeighborhood;
    }

    public String getClientLocality() {
        return clientLocality;
    }

    public String getClientState() {
        return clientState;
    }

    public String getClientPostalCode() {
        return clientPostalCode;
    }
}
