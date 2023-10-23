package com.maliha.miniproject.service;

import com.maliha.miniproject.constants.AppConstants;
import com.maliha.miniproject.entity.BookEntity;
import com.maliha.miniproject.entity.UserEntity;
import com.maliha.miniproject.model.UserDto;
import com.maliha.miniproject.repository.UserRepository;
import com.maliha.miniproject.utils.JWTUtils;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImplementation implements UserService, UserDetailsService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;



    @Override
    public UserDto createUser(UserDto userDto) throws RuntimeException {
        if(userRepository.existsByEmail(userDto.getEmail())){
            throw new RuntimeException();

        }
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        userEntity.setRole("CUSTOMER");
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setAddress(userDto.getAddress());
        UserEntity storedUserDetails = userRepository.save(userEntity);
        UserDto returnedValue = new ModelMapper().map(storedUserDetails,UserDto.class);
        String accessToken = JWTUtils.generateToken(userEntity.getEmail());
        returnedValue.setAccessToken(AppConstants.TOKEN_PREFIX + accessToken);
        return returnedValue;
    }
    @Override
    public UserDto getUserById(Integer id)throws NullPointerException{
        Optional<UserEntity> userEntity = userRepository.findById(id);
        UserDto userDto= new UserDto();
        if (userRepository.existsById(id)){
            BeanUtils.copyProperties(userEntity.get(),userDto);
            return new ModelMapper().map(userRepository.findById(id).orElseThrow(()->new NullPointerException("Id not available")), UserDto.class);
        }
        else throw new NullPointerException("");
    }
    @Override
    public UserDto getUser(String email) {
        UserEntity userEntity = userRepository.findByEmail(email).get();
        if(userEntity == null) throw new UsernameNotFoundException("No record found");
        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(userEntity,returnValue);
        return returnValue;
    }
    public UserDetails loadUserByUsername(String email) {
        UserEntity userEntity = userRepository.findByEmail(email).get();
        if(userEntity==null) throw new UsernameNotFoundException("User Email does not exists!");
        return new org.springframework.security.core.userdetails.User(userEntity.getEmail(),userEntity.getPassword(),
                true,true,true, true,new ArrayList<>());
    }
    public List<UserEntity> getAllUser() {
        return userRepository.findAll().stream()
                .sorted(Comparator.comparing(UserEntity::getFirstName))
                .collect(Collectors.toList());
    }

}
