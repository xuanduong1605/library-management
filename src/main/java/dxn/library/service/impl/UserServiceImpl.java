package dxn.library.service.impl;

import dxn.library.dto.request.UserCreationRequest;
import dxn.library.dto.response.UserResponse;
import dxn.library.exception.ApiException;
import dxn.library.exception.ResponseCode;
import dxn.library.model.User;
import dxn.library.repository.UserRepository;
import dxn.library.service.UserService;
import dxn.library.util.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponse saveUser(UserCreationRequest request){
        User user = userMapper.toUser(request);

        if (userRepository.existsByPhoneNumber(user.getPhoneNumber()) || userRepository.existsByEmail(user.getEmail())) {
            throw new ApiException(ResponseCode.USER_EXISTED);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public List<UserResponse> getUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable).stream()
                .map(userMapper::toUserResponse)
                .toList();
    }

    public UserResponse getUserById(Long userId){
        return userMapper.toUserResponse(userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found")));
    }

    @Override
    public void deleteUserById(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ApiException(ResponseCode.UNKNOWN_USER);
        }
        userRepository.deleteById(userId);
    }
}
