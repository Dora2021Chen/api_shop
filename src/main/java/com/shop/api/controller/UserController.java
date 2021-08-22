package com.shop.api.controller;

import com.shop.api.common.Check;
import com.shop.api.common.response.Const;
import com.shop.api.common.response.Response;
import com.shop.api.common.response.ResponseRow;
import com.shop.api.common.response.ResponseRows;
import com.shop.api.model.UserData;
import com.shop.api.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/shop/user")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "findAll", produces = Const.RESPONSE_FORMAT)
    public ResponseRows<UserData> findAll() {
        ResponseRows<UserData> responseRows = userService.findAll();
        return responseRows;
    }

    @GetMapping(path = "login", produces = Const.RESPONSE_FORMAT)
    public ResponseRow<UserData> login(@RequestParam String username, String password) {
        ResponseRow<UserData> responseRow = userService.login(username, password);
        return responseRow;
    }

    @GetMapping(path = "findById", produces = Const.RESPONSE_FORMAT)
    public ResponseRow<UserData> findById(@RequestParam Long id) {
        ResponseRow<UserData> responseRow = userService.findById(id);
        return responseRow;
    }

    @PostMapping(path = "save", produces = Const.RESPONSE_FORMAT)
    public ResponseRow<UserData> save(@RequestBody UserData userData) {
        ResponseRow<UserData> responseRow;
        if (userData == null) return new ResponseRow<>(Const.STATUS_CODE_FAIL_PARAM_NULL, "userDta");

        List<String> fieldNameList = new ArrayList<>() {{
            add("firstName");
            add("lastName");
            add("username");
            add("password");
            add("phone");
            add("email");
        }};
        responseRow = Check.CheckStringNotNullable(userData, Const.maxLen, fieldNameList);
        if (responseRow.statusCode != Const.STATUS_CODE_SUCCEED) return responseRow;

        responseRow = userService.save(userData);
        return responseRow;
    }

    @DeleteMapping(path = "deleteById", produces = Const.RESPONSE_FORMAT)
    public Response deleteById(@RequestParam Long id) {
        Response response = userService.deleteById(id);
        return response;
    }
}
