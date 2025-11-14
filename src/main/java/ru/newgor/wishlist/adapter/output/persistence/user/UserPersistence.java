package ru.newgor.wishlist.adapter.output.persistence.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.newgor.wishlist.adapter.output.persistence.user.entity.FollowerRelationEntity;
import ru.newgor.wishlist.adapter.output.persistence.user.entity.UserEntity;
import ru.newgor.wishlist.adapter.output.persistence.user.mapper.UserMapper;
import ru.newgor.wishlist.adapter.output.persistence.user.repository.FollowerRelationRepository;
import ru.newgor.wishlist.adapter.output.persistence.user.repository.UserRepository;
import ru.newgor.wishlist.domain.UserModel;
import ru.newgor.wishlist.domain.exception.UserAlreadyExistsException;
import ru.newgor.wishlist.domain.exception.WrongPasswordException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserPersistence {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final FollowerRelationRepository followerRelationRepository;
    private final PasswordEncoder passwordEncoder;

    public void addFollower(String userLogin, String followerLogin) {
        followerRelationRepository.save(new FollowerRelationEntity(UUID.randomUUID(), userLogin, followerLogin));
    }

    public List<String> getAllFollowers(String userLogin) {
        return followerRelationRepository.findAllFollowers(userLogin);
    }

    public UserModel saveUser(UserModel userModel) {
        var login = userModel.getLogin();
        var currentUser = userRepository.findByLogin(login).orElse(null);
        if(currentUser != null) {
            throw new UserAlreadyExistsException(login);
        }

        var userEntity = new UserEntity();
        userEntity.setLogin(userModel.getLogin());
        userEntity.setPassword(passwordEncoder.encode(userModel.getPassword()));
        return userMapper.toUserModel(userRepository.saveAndFlush(userEntity));
    }

    public UserModel getUserByLogin(String login) {
        var entity = userRepository.findByLogin(login).orElseThrow(); // todo
        return userMapper.toUserModel(entity);
    }

    public void loginUser(UserModel request) {
        var login = request.getLogin();
        UserEntity user = userRepository.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException(login));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new WrongPasswordException(login);
        }
    }
}
