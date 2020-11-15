package ir.torshizi.authentication.security.model.mapper;

import ir.torshizi.authentication.security.model.dto.RoleDto;
import ir.torshizi.authentication.security.model.entity.RoleEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Mapper
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper( RoleMapper.class );
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "role", target = "role"),
    })
    RoleDto toRoleDto(RoleEntity roleEntity);

    @InheritInverseConfiguration(name = "toRoleDto")
    RoleEntity toRoleEntity(RoleDto roleDTO);

    List<RoleDto> toRoleDtoList(Collection<RoleEntity> roleEntityCollection);
    List<RoleEntity> toRoleEntityList(Collection<RoleDto> roleDtoCollection);
}
