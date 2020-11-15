package ir.torshizi.authentication.security.service;

import ir.torshizi.authentication.security.model.dto.UserDto;
import ir.torshizi.authentication.security.model.entity.UserEntity;
import ir.torshizi.authentication.security.model.mapper.UserMapper;
import ir.torshizi.authentication.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDto save(UserDto userDTO){
        UserEntity user = UserMapper.INSTANCE.toUserEntity(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity entity = userRepository.save(user);
        return UserMapper.INSTANCE.toUserDto(entity);
    }

    public UserDto findById(Long id){
        Optional<UserEntity> userEntity = userRepository.findById(id);
        return UserMapper.INSTANCE.toUserDto(userEntity.orElse(null));
    }

    public UserDto findByUsername(String username){
        Optional<UserEntity> userEntity = userRepository.findByUsername(username);
        return UserMapper.INSTANCE.toUserDto(userEntity.orElse(null));
    }

    public List<UserDto> getAll(){
        List<UserEntity> userEntityList = userRepository.findAllBy();
        return UserMapper.INSTANCE.toUserDtoList(userEntityList);
    }

    public boolean deleteById(Long id){
        userRepository.deleteById(id);
        return (findById(id)==null);
    }

}
