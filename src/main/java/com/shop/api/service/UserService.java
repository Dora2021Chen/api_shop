package com.shop.api.service;

import com.shop.api.common.response.Const;
import com.shop.api.common.response.Response;
import com.shop.api.common.response.ResponseRow;
import com.shop.api.common.response.ResponseRows;
import com.shop.api.model.UserDta;
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

    public ResponseRows<UserDta> findAll() {
        List<UserDta> userDtaList = userRepository.findAll();
        ResponseRows<UserDta> responseRows = new ResponseRows<>(userDtaList);
        return responseRows;
    }

    public ResponseRow<UserDta> findById(Long id) {
        Optional<UserDta> userDta = userRepository.findById(id);
        ResponseRow<UserDta> responseRow = new ResponseRow<>(userDta);
        return responseRow;
    }

    public ResponseRow<UserDta> save(UserDta userDta) {
        UserDta userDta1 = userRepository.save(userDta);
        ResponseRow<UserDta> responseRow = new ResponseRow<>(userDta1);
        return responseRow;
    }

    public Response deleteById(Long id) {
        Response response;
        Optional<UserDta> userDta = userRepository.findById(id);
        if (userDta.isEmpty()) {
            response = new Response(Const.STATUS_CODE_FAIL_USER_NOT_EXISTS);
            return response;
        }
        userRepository.deleteById(id);

        response = new Response();
        return response;
    }
}
