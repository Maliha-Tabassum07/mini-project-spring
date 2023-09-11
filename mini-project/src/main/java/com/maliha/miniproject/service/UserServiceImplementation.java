package com.maliha.miniproject.service;

import com.maliha.miniproject.entity.UserEntity;
import com.maliha.miniproject.model.User;
import com.maliha.miniproject.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class UserServiceImplementation implements UserService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private ModelMapper modelMapper;

    @Override
    public User createUser(User user) throws Exception {
        modelMapper = new ModelMapper();
        if(userRepository.findByEmail(user.getEmail()).isPresent())
            throw new Exception("Record already exists");
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userEntity.setRole("ROLE_CUSTOMER");
//        String publicUserId = JWTUtils.generateUserID(10);
//        userEntity.setUser_id(publicUserId);
        UserEntity storedUserDetails =userRepository.save(userEntity);
        User returnedValue = modelMapper.map(storedUserDetails,User.class);
        return returnedValue;
    }
    @Override
    public User getUserById(Integer id)throws NullPointerException{
        if (userRepository.existsById(id)){
            return modelMapper.map(userRepository.findById(id),User.class);
        }
        else throw new NullPointerException();
    }
}
