package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.entity.User;
import com.edu.ulab.app.entity.generator.UserIdGenerator;
import com.edu.ulab.app.service.UserService;
import com.edu.ulab.app.storage.Storage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
        private final Storage storage;

    public UserServiceImpl(@Autowired Storage storage) {
        this.storage = storage;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        // сгенерировать идентификатор
        // создать пользователя
        // вернуть сохраненного пользователя со всеми необходимыми полями id
        long id = UserIdGenerator.getId();
        User user = new User();
        user.setFullName(userDto.getFullName());
        user.setTitle(userDto.getTitle());
        user.setAge(userDto.getAge());
        user.setId(id);
        return convert(storage.addUser(user));
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        return convert(storage.updateUser(convert(userDto)));
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = storage.getUserById(id);
        return user == null ? null : convert(user);
    }

    @Override
    public void deleteUserById(Long id) {
        storage.deleteUser(id);
    }
    public UserDto convert(User user){
        UserDto userDto = new UserDto();
        userDto.setFullName(user.getFullName());
        userDto.setAge(user.getAge());
        userDto.setTitle(user.getTitle());
        userDto.setId(user.getId());
        return userDto;
    }
    public User convert(UserDto userDto){
        User user = new User();
        user.setFullName(userDto.getFullName());
        user.setAge(userDto.getAge());
        user.setTitle(userDto.getTitle());
        user.setId(userDto.getId());
        return user;
    }

}
