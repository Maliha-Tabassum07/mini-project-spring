package com.maliha.miniproject.service;

import com.maliha.miniproject.constants.AppConstants;
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
import java.util.Optional;

@Service
@Transactional
public class UserServiceImplementation implements UserService, UserDetailsService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) throws Exception {
        modelMapper = new ModelMapper();
//        if(userRepository.findById(user.getUserId()).isPresent())
//            throw new Exception("Record already exists");
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        userEntity.setRole(userDto.getRole());
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setAddress(userDto.getAddress());
        UserEntity storedUserDetails = userRepository.save(userEntity);
//        UserDto returnedValue = modelMapper.map(storedUserDetails, UserDto.class);
        UserDto returnedValue = modelMapper.map(storedUserDetails,UserDto.class);
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
            return modelMapper.map(userRepository.findById(id).orElseThrow(()->new NullPointerException("Id not available")), UserDto.class);
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

}
