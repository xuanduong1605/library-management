package dxn.library.util.mapper;

import dxn.library.dto.request.UserCreationRequest;
import dxn.library.dto.response.AuthenticationResponse;
import dxn.library.dto.response.UserResponse;
import dxn.library.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);
}
