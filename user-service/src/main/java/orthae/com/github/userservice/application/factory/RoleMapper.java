package orthae.com.github.userservice.application.factory;

import orthae.com.github.userservice.domain.Role;
import orthae.com.github.userservice.application.model.RoleModel;

public class RoleMapper {
    public static Role toDomain(RoleModel role) {
        return switch (role) {
            case ADMIN -> Role.ADMIN;
            case USER -> Role.USER;
        };
    }

    public static RoleModel toModel(Role role) {
        return switch (role) {
            case ADMIN -> RoleModel.ADMIN;
            case USER -> RoleModel.USER;
        };
    }
}