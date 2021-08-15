package com.shop.api.service;

import com.shop.api.model.User;
import com.shop.api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public <S extends User> S save(S s) {
        return userRepository.save(s);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
