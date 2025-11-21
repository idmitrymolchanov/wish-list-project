package ru.newgor.wishlist.infrastructure;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.newgor.wishlist.domain.UserModel;
import ru.newgor.wishlist.usecase.service.JwtService;
import ru.newgor.wishlist.usecase.service.UserService;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
//    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

//        String authHeader = request.getHeader("Authorization");
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            chain.doFilter(request, response);
//            return;
//        }
//
//        String token = authHeader.substring(7);
//        String login = jwtService.extractLogin(token);
////todo
//        if (login != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserModel user = userService.getUserByLogin(login);
//            if (jwtService.isTokenValid(token, user)) {
//                UsernamePasswordAuthenticationToken auth =
//                        new UsernamePasswordAuthenticationToken(user, null, List.of());
//                SecurityContextHolder.getContext().setAuthentication(auth);
//            }
//        }

        chain.doFilter(request, response);
    }
}
