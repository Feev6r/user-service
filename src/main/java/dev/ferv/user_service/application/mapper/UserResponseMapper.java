package dev.ferv.user_service.application.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import dev.ferv.user_service.application.dto.response.UserResponse;
import dev.ferv.user_service.domain.model.User;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserResponseMapper {

    UserResponse toResponse(User user);
    List<UserResponse> toResponseList(List<User> user);

}
