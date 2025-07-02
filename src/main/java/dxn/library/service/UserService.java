package dxn.library.service;

import dxn.library.dto.request.UserCreationRequest;
import dxn.library.dto.response.UserResponse;
import dxn.library.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserResponse saveUser(UserCreationRequest userCreationRequest);
    List<UserResponse> getUsers();
    UserResponse getUserById(Long id);
    void deleteUserById(Long id);
}