package orthae.com.github.userservice.application.factory;

import orthae.com.github.userservice.application.model.UserModel;
import orthae.com.github.userservice.domain.User;

public class UserMapper {
    public static UserModel toModel(User user) {
        return UserModel.builder()
                .id(user.getId())
                .username(user.getUsername())
                .role(RoleMapper.toModel(user.getRole()))
                .build();
    }
}
