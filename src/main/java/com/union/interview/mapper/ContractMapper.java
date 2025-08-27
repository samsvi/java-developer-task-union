package com.union.interview.mapper;

import com.union.interview.dto.response.ContractResponseDto;
import com.union.interview.model.Contract;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContractMapper {

    @Mapping(source = "provider.name", target = "providerName")
    @Mapping(source = "provider.ico", target = "providerIco")
    @Mapping(source = "service.name", target = "serviceName")
    @Mapping(source = "service.code", target = "serviceCode")
    @Mapping(source = "totalPrice", target = "price")
    ContractResponseDto toDto(Contract contract);
}
