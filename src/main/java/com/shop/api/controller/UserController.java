package com.shop.api.controller;

import com.shop.api.model.IConst;
import com.shop.api.model.User;
import com.shop.api.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/shop/user")
@CrossOrigin
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "getAll", produces = IConst.RESPONSE_FORMAT)
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping(path = "getById", produces = IConst.RESPONSE_FORMAT)
    public Optional<User> findById(@RequestParam Long id) {
        return userService.findById(id);
    }

    @PostMapping(path = "write", produces = IConst.RESPONSE_FORMAT)
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

    @DeleteMapping(path = "delete", produces = IConst.RESPONSE_FORMAT)
    public void deleteById(@RequestParam Long id) {
        userService.deleteById(id);
    }
}
