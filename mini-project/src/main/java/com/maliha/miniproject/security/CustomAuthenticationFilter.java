package com.maliha.miniproject.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maliha.miniproject.MiniProjectContext;
import com.maliha.miniproject.constants.AppConstants;
import com.maliha.miniproject.model.UserDto;
import com.maliha.miniproject.model.UserLoginModel;
import com.maliha.miniproject.service.UserService;
import com.maliha.miniproject.utils.JWTUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
@RequiredArgsConstructor
@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UserLoginModel creds = new ObjectMapper().readValue(request.getInputStream(), UserLoginModel.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(creds.getEmail(),creds.getPassword())
            );
        } catch (IOException e) {
            log.info("Exception occured at attemptAuthentication method: {}",e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String user = ((User)authResult.getPrincipal()).getUsername();

        String accessToken = JWTUtils.generateToken(user);
        UserService userService = (UserService) MiniProjectContext.getBean("userServiceImpl");
        UserDto userDto = userService.getUser(user);
        response.addHeader("userId",userDto.getEmail());
        response.addHeader(AppConstants.HEADER_STRING, AppConstants.TOKEN_PREFIX+accessToken);
    }

}

