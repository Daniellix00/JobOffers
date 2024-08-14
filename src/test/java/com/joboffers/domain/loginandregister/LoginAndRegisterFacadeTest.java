package com.joboffers.domain.loginandregister;

import com.joboffers.domain.loginandregister.dto.RegisterUserDto;
import com.joboffers.domain.loginandregister.dto.RegistrationResultDto;
import com.joboffers.domain.loginandregister.dto.UserDto;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

public class LoginAndRegisterFacadeTest {
    LoginAndRegisterFacade loginFacade = new LoginAndRegisterFacade(new InMemoryLoginRepository());
    @Test
    public void should_register_user(){
        //Given
        RegisterUserDto registerUserDto = new RegisterUserDto("Username", "Pass");
        //When
        RegistrationResultDto register = loginFacade.register(registerUserDto);
        //Then
        assertAll(
                () -> assertThat(register.created()).isTrue(),
                () -> assertThat(register.username()).isEqualTo("Username")
        );}
    @Test
    public void should_find_user_by_username(){
        //Given
        RegisterUserDto registerUserDto = new RegisterUserDto("Username", "Pass");
        RegistrationResultDto register = loginFacade.register(registerUserDto);
        //When
        UserDto userByName = loginFacade.findByUsername(register.username());
        //Then
        assertThat(userByName).isEqualTo(new UserDto(register.id(),"Pass","Username"));
    }
    @Test
    public void should_throw_exception_when_user_not_found(){
        //Given
        String username = "UserUser";
        //When
        Throwable throwable = catchThrowable(() -> loginFacade.findByUsername(username));
        //Then
        AssertionsForClassTypes.assertThat(throwable)
                .isInstanceOf(UserNotFoundException.class)
                .hasMessage("User not found");
    }
}