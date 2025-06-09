package com.mateo.plataforma_educativa.mapper;

import com.mateo.plataforma_educativa.dto.RoleResponseDTO;
import com.mateo.plataforma_educativa.dto.RoleRequestDTO;
import com.mateo.plataforma_educativa.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IRoleMapper {

    IRoleMapper mapper = Mappers.getMapper(IRoleMapper.class);

    RoleResponseDTO roleToRoleGetDTO(Role role);
    Role roleGetDTOToRole(RoleResponseDTO roleGetDTO);

    RoleRequestDTO roleToRoleSaveDTO(Role role);
    Role roleSaveDTOToRole(RoleRequestDTO roleSaveDTO);
}
