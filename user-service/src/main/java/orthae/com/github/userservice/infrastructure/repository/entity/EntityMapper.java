package orthae.com.github.userservice.infrastructure.repository.entity;

import orthae.com.github.userservice.domain.Role;
import orthae.com.github.userservice.domain.User;

public class EntityMapper {

    public static UserEntity toEntity(User user) {

        return UserEntity.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .role(RoleMapper.toEntity(user.getRole()))
                .build();
    }

    public static User toDomain(UserEntity entity) {

        return User.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .role(RoleMapper.toDomain(entity.role))
                .build();
    }

    private static class RoleMapper {
        public static Role toDomain(RoleEntity role) {
            return switch (role) {
                case ADMIN -> Role.ADMIN;
                case USER -> Role.USER;
            };
        }

        public static RoleEntity toEntity(Role role) {
            return switch (role) {
                case ADMIN -> RoleEntity.ADMIN;
                case USER -> RoleEntity.USER;
            };
        }
    }
}
