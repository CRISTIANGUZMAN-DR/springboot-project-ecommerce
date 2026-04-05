package com.cguzman.springboot_project_ecommerce.services;

import com.cguzman.springboot_project_ecommerce.entities.Dto.UserDto;
import com.cguzman.springboot_project_ecommerce.entities.User;
import com.cguzman.springboot_project_ecommerce.exceptions.RegistryNotFoundException;
import com.cguzman.springboot_project_ecommerce.repositories.UserRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public List<UserDto> findAll() {
        List<User> users = (List<User>) userRepository.findAll();
        List<UserDto> listUserDto = new ArrayList<>();
        users.forEach(user -> {
            UserDto userDto = saveUserDto(user);
            listUserDto.add(userDto);
        });

        return listUserDto;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDto findById(Long id) {
        return  userRepository.findById(id)
                .map(this::saveUserDto)
                .orElseThrow(() -> new RegistryNotFoundException("No se encontró ningún usuario relacionado con el id"));
    }

    @Transactional(readOnly = true)
    @Override
    public @Nullable UserDto findByEmail(String email) {
        return userRepository.findByEmail(email).map(this::saveUserDto).orElseThrow(()-> new RegistryNotFoundException("No se encontró ningun usuario con ese mail"));
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDto> findByNameContaining(String name) {
        List<User> users = userRepository.findByNameContaining(name);
        List<UserDto> userDtos = new ArrayList<>();
        users.forEach(user -> {
            userDtos.add(saveUserDto(user));
        });
        if(userDtos.isEmpty()){
            throw new RegistryNotFoundException("No se encontró ningun usuario con esas characteristics de nombre");
        }
        return userDtos;
    }

    @Transactional
    @Override
    public UserDto save(User user) {
        return saveUserDto(userRepository.save(user));
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            userRepository.deleteById(id);
        }else {
            throw new RegistryNotFoundException("No se encontró ningún usuario relacionado con el id");
        }
    }

    @Transactional
    @Override
    public UserDto update(Long id, User user) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            User userOld = optionalUser.get();
            userOld.setName(user.getName());
            userOld.setEmail(user.getEmail());
            userOld.setPassword(user.getPassword());
            userOld.setActive(user.getActive());
            return saveUserDto(userRepository.save(userOld));
        }
        throw new RegistryNotFoundException("No se encontró ningún usuario relacionado con el id");
    }

    public UserDto saveUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setActive(user.getActive());
        return userDto;
    }

    @Transactional
    @Override
    public void activate(Long id, boolean active) {
        User user = userRepository.findById(id).orElseThrow(()-> new RegistryNotFoundException("Usuario no encontrado"));
        user.setActive(active);
        userRepository.save(user);
    }


}
