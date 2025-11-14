package ru.newgor.wishlist.usecase.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.newgor.wishlist.adapter.output.persistence.user.UserPersistence;
import ru.newgor.wishlist.domain.UserModel;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserPersistence userPersistence;

    public UserModel getUserByLogin(String login) {
        return userPersistence.getUserByLogin(login);
    }
    public UserModel createUser(UserModel userModel) {
        return userPersistence.saveUser(userModel);
    }

    public void addFollower(String userLogin, String followerLogin) {
        userPersistence.addFollower(userLogin, followerLogin);
    }

    public List<String> getAllFollowers(String userLogin) {
        return userPersistence.getAllFollowers(userLogin);
    }

    public void loginUser(UserModel request) {
        userPersistence.loginUser(request);
    }
}
