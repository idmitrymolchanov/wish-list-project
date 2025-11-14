package ru.newgor.wishlist.adapter.output.persistence.user.mapper;

import org.mapstruct.Mapper;
import ru.newgor.wishlist.adapter.output.persistence.user.entity.UserEntity;
import ru.newgor.wishlist.domain.UserModel;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toUserEntity(UserModel user);

    UserModel toUserModel(UserEntity user);
}

