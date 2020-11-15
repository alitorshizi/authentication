package ir.torshizi.authentication.security.service;

import ir.torshizi.authentication.security.model.dto.RoleDto;
import ir.torshizi.authentication.security.model.entity.RoleEntity;
import ir.torshizi.authentication.security.model.mapper.RoleMapper;
import ir.torshizi.authentication.security.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public RoleDto save(RoleDto roleDTO){
        RoleEntity entity = roleRepository.save(RoleMapper.INSTANCE.toRoleEntity(roleDTO));
        return RoleMapper.INSTANCE.toRoleDto(entity);
    }

    public RoleDto findByRole(String role){
        Optional<RoleEntity> roleEntity = roleRepository.findByRole(role);
        return RoleMapper.INSTANCE.toRoleDto(roleEntity.orElse(null));
    }

    public RoleDto findById(Long id){
        Optional<RoleEntity> roleEntity = roleRepository.findById(id);
        return RoleMapper.INSTANCE.toRoleDto(roleEntity.orElse(null));
    }

    public List<RoleDto> getAll(){
        List<RoleEntity> roleEntityList = (List<RoleEntity>) roleRepository.findAll();
        return RoleMapper.INSTANCE.toRoleDtoList(roleEntityList);
    }

    public boolean deleteById(Long id){
        roleRepository.deleteById(id);
        return (findById(id)==null);
    }

}
