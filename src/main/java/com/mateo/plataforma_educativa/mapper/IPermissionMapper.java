package com.mateo.plataforma_educativa.mapper;

import com.mateo.plataforma_educativa.dto.PermissionResponseDTO;
import com.mateo.plataforma_educativa.dto.PermissionRequestDTO;
import com.mateo.plataforma_educativa.model.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IPermissionMapper {

    IPermissionMapper mapper = Mappers.getMapper(IPermissionMapper.class);

    PermissionResponseDTO permissionToPermissionGetDTO(Permission permission);
    Permission permissionGetDTOToPermission(PermissionResponseDTO permissionGetDTO);

    PermissionRequestDTO permissionToPermissionSaveDTO(Permission permission);
    Permission permissionSaveDTOToPermission(PermissionRequestDTO permissionSaveDTO);
}
