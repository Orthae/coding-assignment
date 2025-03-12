package orthae.com.github.userservice.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import orthae.com.github.userservice.infrastructure.repository.entity.UserEntity;

import java.util.Optional;
import java.util.UUID;

interface InternalUserPostgresRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByUsername(String username);
}