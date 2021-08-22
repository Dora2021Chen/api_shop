package com.shop.api.service;

import com.shop.api.common.response.Const;
import com.shop.api.common.response.Response;
import com.shop.api.common.response.ResponseRow;
import com.shop.api.common.response.ResponseRows;
import com.shop.api.model.UserData;
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

    public ResponseRows<UserData> findAll() {
        List<UserData> userDataList = userRepository.findAll();
        ResponseRows<UserData> responseRows = new ResponseRows<>(userDataList);
        return responseRows;
    }

    public ResponseRow<UserData> login(String username, String password) {
        List<UserData> userDataList = userRepository.findByUsername(username);
        if (userDataList.isEmpty()) {
            return new ResponseRow<>(Const.STATUS_CODE_FAIL_USER_NAME_NOT_EXISTS);
        }

        if (!userDataList.get(0).getPassword().equals(password)) {
            return new ResponseRow<>(Const.STATUS_CODE_FAIL_PASSWORD_WRONG);
        }

        ResponseRow<UserData> responseRow = new ResponseRow<>(userDataList.get(0));
        return responseRow;
    }

    public ResponseRow<UserData> findById(Long id) {
        Optional<UserData> userDta = userRepository.findById(id);
        ResponseRow<UserData> responseRow = new ResponseRow<>(userDta);
        return responseRow;
    }

    public ResponseRow<UserData> save(UserData userData) {
        ResponseRow<UserData> responseRow;
        List<UserData> userDataList = userRepository.findByUsername(userData.getUsername());
        if (!userDataList.isEmpty()) {
            if (userData.getId() == null || !userDataList.stream().findFirst().get().getId().equals(userData.getId())) {
                responseRow = new ResponseRow<>(Const.STATUS_CODE_FAIL_USER_NAME_EXISTS);
                return responseRow;
            }
        }

        userDataList = userRepository.findByPhone(userData.getPhone());
        if (!userDataList.isEmpty()) {
            if (userData.getId() == null || !userDataList.stream().findFirst().get().getId().equals(userData.getId())) {
                responseRow = new ResponseRow<>(Const.STATUS_CODE_FAIL_USER_PHONE_EXISTS);
                return responseRow;
            }
        }

        userDataList = userRepository.findByEmail(userData.getEmail());
        if (!userDataList.isEmpty()) {
            if (userData.getId() == null || !userDataList.stream().findFirst().get().getId().equals(userData.getId())) {
                responseRow = new ResponseRow<>(Const.STATUS_CODE_FAIL_USER_EMAIL_EXISTS);
                return responseRow;
            }
        }

        UserData userData1 = userRepository.save(userData);
        responseRow = new ResponseRow<>(userData1);
        return responseRow;
    }

    public Response deleteById(Long id) {
        Response response;
        Optional<UserData> userDta = userRepository.findById(id);
        if (userDta.isEmpty()) {
            response = new Response(Const.STATUS_CODE_FAIL_USER_ID_NOT_EXISTS);
            return response;
        }
        userRepository.deleteById(id);

        response = new Response();
        return response;
    }
}
