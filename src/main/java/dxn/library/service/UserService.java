package dxn.library.service;

import dxn.library.dto.request.UserCreationRequest;
import dxn.library.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse saveUser(UserCreationRequest userCreationRequest);
    List<UserResponse> getUsers();
    UserResponse getUserById(Long id);
    void deleteUserById(Long id);
}