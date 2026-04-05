package com.cguzman.springboot_project_ecommerce.services;

import com.cguzman.springboot_project_ecommerce.entities.Dto.UserDto;
import com.cguzman.springboot_project_ecommerce.entities.User;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface UserService extends BaseService<UserDto, User> {
    void activate(Long id, boolean active);

    UserDto findByEmail(String email);
    List<UserDto> findByNameContaining(String name);
}
