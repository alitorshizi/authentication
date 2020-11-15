package ir.torshizi.authentication.security.model.mapper;

import ir.torshizi.authentication.security.model.dto.UserDto;
import ir.torshizi.authentication.security.model.entity.UserEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "username", target = "username"),
            @Mapping(source = "firstName", target = "firstName"),
            @Mapping(source = "lastName", target = "lastName"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "mobile", target = "mobile"),
            @Mapping(source = "nationalCode", target = "nationalCode"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "enabled", target = "enabled"),
    })
    UserDto toUserDto(UserEntity userEntity);

    @InheritInverseConfiguration(name = "toUserDto")
    UserEntity toUserEntity(UserDto userDTO);

    List<UserDto> toUserDtoList(Collection<UserEntity> userEntityCollection);
    List<UserEntity> toUserEntityList(Collection<UserDto> userDtoCollection);

}
