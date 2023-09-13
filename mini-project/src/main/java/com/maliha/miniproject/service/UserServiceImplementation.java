package com.maliha.miniproject.service;

import com.maliha.miniproject.entity.UserEntity;
import com.maliha.miniproject.model.UserDto;
import com.maliha.miniproject.model.UserRole;
import com.maliha.miniproject.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImplementation implements UserService{
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
        userEntity.setRole(UserRole.CUSTOMER.name());
        UserEntity storedUserDetails =userRepository.save(modelMapper.map(userDto,UserEntity.class));
        UserDto returnedValue = modelMapper.map(storedUserDetails, UserDto.class);
        return returnedValue;
    }
    @Override
    public UserDto getUserById(Integer id)throws NullPointerException{
        if (userRepository.existsById(id)){
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

}
