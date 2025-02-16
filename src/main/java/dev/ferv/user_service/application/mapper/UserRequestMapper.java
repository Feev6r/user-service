package dev.ferv.user_service.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import dev.ferv.user_service.application.dto.request.ClientRequest;
import dev.ferv.user_service.application.dto.request.EmployeeRequest;
import dev.ferv.user_service.application.dto.request.OwnerRequest;
import dev.ferv.user_service.application.dto.request.UserRequest;
import dev.ferv.user_service.domain.model.User;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserRequestMapper {

    User toUser(UserRequest userRequest);
    User toUser(OwnerRequest ownerRequest);
    User toUser(EmployeeRequest employeeRequest);
    User toUser(ClientRequest clientRequest);
}
