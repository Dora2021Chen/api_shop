package com.shop.api.controller;

import com.shop.api.common.Check;
import com.shop.api.common.response.Const;
import com.shop.api.common.response.Response;
import com.shop.api.common.response.ResponseRow;
import com.shop.api.common.response.ResponseRows;
import com.shop.api.model.UserDta;
import com.shop.api.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
        ResponseRow<UserDta> responseRow;
        if (userDta == null) return new ResponseRow<>(Const.STATUS_CODE_FAIL_PARAM_NULL, "userDta");

        List<String> fieldNameList = new ArrayList<>() {{
            add("name");
            add("userName");
            add("password");
            add("telephone");
            add("email");
        }};
        responseRow = Check.CheckStringNotNullable(userDta, Const.maxLen, fieldNameList);
        if (responseRow.statusCode != Const.STATUS_CODE_SUCCEED) return responseRow;

        responseRow = userService.save(userDta);
        return responseRow;
    }

    @DeleteMapping(path = "delete", produces = Const.RESPONSE_FORMAT)
    public Response deleteById(@RequestParam Long id) {
        Response response = userService.deleteById(id);
        return response;
    }
}
