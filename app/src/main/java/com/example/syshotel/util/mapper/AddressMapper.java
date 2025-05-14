package com.example.syshotel.util.mapper;

import com.example.syshotel.model.address.dto.AddressDto;
import com.example.syshotel.model.address.dto.ViaCepDto;
import com.example.syshotel.util.TextUtil;
import com.google.android.material.textfield.TextInputEditText;

public class AddressMapper {
    private final TextUtil utilText;

    public AddressMapper() {
        this.utilText = new TextUtil();
    }

    public AddressDto convertAddressDto(String street, String complement, String neighborhood, String locality, String state, String code) {
        return new AddressDto(street, complement, neighborhood, locality, state, code.replaceAll("\\D", ""));
    }

    public AddressDto convertCepToAddressDto(ViaCepDto viaCep) {
        return new AddressDto(viaCep.getLogradouro(),
                viaCep.getComplemento(),
                viaCep.getBairro(),
                viaCep.getLocalidade(),
                viaCep.getEstado(),
                viaCep.getCep()
        );
    }

    public AddressDto loadAddress(TextInputEditText street, TextInputEditText complement, TextInputEditText neighborhood, TextInputEditText city, TextInputEditText state, TextInputEditText cep) {

        return convertAddressDto(
                utilText.safeText(street),
                utilText.safeText(complement),
                utilText.safeText(neighborhood),
                utilText.safeText(city),
                utilText.safeText(state),
                utilText.safeText(cep));
    }

}
