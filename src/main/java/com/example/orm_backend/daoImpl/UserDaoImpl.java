package com.example.orm_backend.daoImpl;

import com.example.orm_backend.dao.UserDao;
import com.example.orm_backend.entity.User;
import com.example.orm_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void register(String name, String password, String email, String address) {

        User add_user = new User();

        add_user.setUser_name(name);
        add_user.setUser_password(password);
        add_user.setIdentity("customer");
        add_user.setEmail(email);
        add_user.setAddress(address);
        add_user.setState("1");

        userRepository.saveAndFlush(add_user);
    }

    @Override
    public List<User> checkRepeat(String user_name) {
        return userRepository.findAll();
    }

    @Override
    public List<User> checkAuth(String name, String password, String identity) {
        return userRepository.checkAuth(name, password, identity);
    }

    @Override
    public List<User> getUsersAll() {
        return userRepository.findAll();
    }

    @Override
    public void changeState(int user_id) {
        User user_changed = userRepository.getOne(user_id);
        if (Objects.equals(user_changed.getState(), "1"))
            user_changed.setState("0");
        else
            user_changed.setState("1");
        userRepository.saveAndFlush(user_changed);
    }
}
