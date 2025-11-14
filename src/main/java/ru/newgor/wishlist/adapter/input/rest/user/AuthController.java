package ru.newgor.wishlist.adapter.input.rest.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.newgor.wishlist.domain.UserModel;
import ru.newgor.wishlist.usecase.service.JwtService;
import ru.newgor.wishlist.usecase.service.UserService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public String register(@RequestBody UserModel request) {
        var user = userService.createUser(request);
        return jwtService.generateToken(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserModel request) {
        userService.loginUser(request);
        return jwtService.generateToken(request);
    }
}
