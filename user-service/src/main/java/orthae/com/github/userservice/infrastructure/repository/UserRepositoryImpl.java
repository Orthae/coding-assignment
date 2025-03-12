package orthae.com.github.userservice.infrastructure.repository;

import org.springframework.stereotype.Repository;
import orthae.com.github.userservice.domain.User;
import orthae.com.github.userservice.domain.UserRepository;
import orthae.com.github.userservice.infrastructure.repository.entity.EntityMapper;


import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final InternalUserPostgresRepository repository;

    UserRepositoryImpl(InternalUserPostgresRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username)
                .map(EntityMapper::toDomain);
    }

    @Override
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    @Override
    public void save(User user) {
        repository.save(EntityMapper.toEntity(user));
    }
}


