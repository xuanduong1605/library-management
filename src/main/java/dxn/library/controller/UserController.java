package dxn.library.controller;

import dxn.library.dto.request.UserCreationRequest;
import dxn.library.dto.response.ApiResponse;
import dxn.library.dto.response.UserResponse;
import dxn.library.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v0/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request){
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();

        apiResponse.setResult(userService.saveUser(request));

        return apiResponse;
    }

    @GetMapping
    List<UserResponse> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    ApiResponse<UserResponse> getUser(@PathVariable("userId") Long id){
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();

        apiResponse.setResult(userService.getUserById(id));

        return apiResponse;
    }

    @DeleteMapping("/{id}")
    String deleteUser(@PathVariable Long id){
        userService.deleteUserById(id);
        return "User has been deleted";
    }
}
