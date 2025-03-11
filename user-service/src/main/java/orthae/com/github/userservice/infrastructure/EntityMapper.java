package orthae.com.github.userservice.infrastructure;

import orthae.com.github.userservice.domain.Role;
import orthae.com.github.userservice.domain.User;

public class EntityMapper {

    public static UserEntity toEntity(User user) {
        return new UserEntity(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                switch (user.getRole()) {
                    case ADMIN -> RoleEntity.ADMIN;
                    case USER -> RoleEntity.USER;
                }
        );
    }

    public static User toDomain(UserEntity entity) {
        return new User(
                entity.getId(),
                entity.getUsername(),
                entity.getPassword(),
                switch (entity.role) {
                    case ADMIN -> Role.ADMIN;
                    case USER -> Role.USER;
                }
        );
    }
}
