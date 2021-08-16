package com.shop.api.controller;

import com.shop.api.common.response.Const;
import com.shop.api.common.response.Response;
import com.shop.api.common.response.ResponseRow;
import com.shop.api.common.response.ResponseRows;
import com.shop.api.model.UserDta;
import com.shop.api.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shop/user")
@CrossOrigin
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "getAll", produces = Const.RESPONSE_FORMAT)
    public ResponseRows<UserDta> findAll() {
        ResponseRows<UserDta> responseRows = userService.findAll();
        return responseRows;
    }

    @GetMapping(path = "getById", produces = Const.RESPONSE_FORMAT)
    public ResponseRow<UserDta> findById(@RequestParam Long id) {
        ResponseRow<UserDta> responseRow = userService.findById(id);
        return responseRow;
    }

    @PostMapping(path = "write", produces = Const.RESPONSE_FORMAT)
    public ResponseRow<UserDta> save(@RequestBody UserDta userDta) {
        ResponseRow<UserDta> responseRow = userService.save(userDta);
        return responseRow;
    }

    @DeleteMapping(path = "delete", produces = Const.RESPONSE_FORMAT)
    public Response deleteById(@RequestParam Long id) {
        Response response = userService.deleteById(id);
        return response;
    }
}
