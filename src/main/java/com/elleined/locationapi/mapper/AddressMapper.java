package com.elleined.locationapi.mapper;

import com.elleined.locationapi.dto.AddressDTO;
import com.elleined.locationapi.model.address.Address;
import com.elleined.locationapi.model.address.DeliveryAddress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    @Mappings({
            @Mapping(target = "provinceName", source = "address.province.locationName"),
            @Mapping(target = "cityName", source = "address.city.locationName"),
            @Mapping(target = "baranggayName", source = "address.baranggay.locationName"),
            @Mapping(target = "provinceId", source = "address.province.id"),
            @Mapping(target = "cityId", source = "address.city.id"),
            @Mapping(target = "baranggayId", source = "address.baranggay.id")
    })
    AddressDTO toDTO(Address address);

    default AddressDTO deliveryAddressToDTO(DeliveryAddress deliveryAddress) {
        return toDTO(deliveryAddress);
    }
}
