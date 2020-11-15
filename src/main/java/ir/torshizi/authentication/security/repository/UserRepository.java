package ir.torshizi.authentication.security.repository;

import ir.torshizi.authentication.security.model.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    List<UserEntity> findAllBy();
    Optional<UserEntity> findByMobile(String mobile);
}
