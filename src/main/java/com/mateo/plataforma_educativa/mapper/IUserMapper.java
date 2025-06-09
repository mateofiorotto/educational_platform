package com.mateo.plataforma_educativa.mapper;

import com.mateo.plataforma_educativa.dto.UserSecResponseDTO;
import com.mateo.plataforma_educativa.dto.UserSecRequestDTO;
import com.mateo.plataforma_educativa.dto.UserSecUpdateDTO;
import com.mateo.plataforma_educativa.model.UserSec;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IUserMapper {

    IUserMapper mapper = Mappers.getMapper(IUserMapper.class);

    UserSecResponseDTO userSecToUserSecGetDTO(UserSec userSec);
    UserSec userSecGetDTOToUserSec(UserSecResponseDTO userSecGetDTO);

    UserSecRequestDTO userSecToUserSecSaveDTO(UserSec userSec);
    UserSec userSecSaveDTOToUserSec(UserSecRequestDTO userSecSaveDTO);

    UserSecUpdateDTO userSecToUserSecUpdateDTO(UserSec userSec);
}
