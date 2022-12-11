package com.selfproject.emailapp.service.mapper;

import com.selfproject.emailapp.domain.EmailDetails;
import com.selfproject.emailapp.service.dto.EmailDetailsDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmailDetailsMapper {

    EmailDetailsDto toDto(EmailDetails emailDetails);

    EmailDetails toEntity(EmailDetailsDto emailDetailsDto);
}
