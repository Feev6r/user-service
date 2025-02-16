package dev.ferv.user_service.infrastructure.output.jpa.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import dev.ferv.user_service.domain.model.User;
import dev.ferv.user_service.infrastructure.output.jpa.entity.UserEntity;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserEntityMapper {


    @Mapping(target = "birthdate", qualifiedByName = "toLocalDate")
    UserEntity toEntity(User user);
    
    @Mapping(target = "birthdate", qualifiedByName = "toBirthdateString")
    User toUser(UserEntity userEntity);

    List<User> toUserList(List<UserEntity> userEntityList);
    

    @Named("toBirthdateString")
    static String toBirthdateString(LocalDate birthdate){
        if(birthdate == null) return null;      
        return birthdate.toString();
    }

    @Named("toLocalDate")
    static LocalDate toLocalDate(String birthdate){
        if(birthdate == null) return null;   
        
        LocalDate parsedBirhtDate = LocalDate.parse(birthdate, DateTimeFormatter.ISO_LOCAL_DATE);
        return parsedBirhtDate;
    }
}