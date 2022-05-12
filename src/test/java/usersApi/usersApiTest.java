package usersApi;

import DTO.User;
import DTO.UserOut;
import dataGenerator.UserGenerator;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import services.UserApi;

import java.util.ArrayList;

import static org.hamcrest.Matcher.*;

public class usersApiTest {
    private UserApi userApi = new UserApi();



    @Test
    public void createValidUserTest(){
        User user = UserGenerator.getUser();

        Response response = userApi.createOneUser(user);


        UserOut actual = response.then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .as(UserOut.class);

        UserOut expected  = UserOut.builder()
                .code(200)
                .message(user.getId().toString())
                .type("unknown")
                .build();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void createArrayUsersTest(){
        User[] users = {UserGenerator.getUser(), UserGenerator.getUser()};

        Response response = userApi.createArrayUser(users);

        UserOut actual = response.then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .as(UserOut.class);

        UserOut expected = UserOut.builder()
                .code(200)
                .type("unknown")
                .message("ok")
                .build();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void createListUsersTest(){
        ArrayList<User> users = new ArrayList<User>();
        users.add(UserGenerator.getUser());
        users.add(UserGenerator.getUser());
        users.add(UserGenerator.getUser());

        Response response = userApi.createListUsers(users);

        UserOut actual = response.then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .as(UserOut.class);

        UserOut expected = UserOut.builder()
                .code(200)
                .type("unknown")
                .message("ok")
                .build();
        Assertions.assertEquals(expected, actual);
    }


    @ParameterizedTest
    @ValueSource(strings = {"admin", "moderator", "user"})
    public void getUserTest(String name){
        Response response = userApi.getUserByName(name);

        UserOut actual = response.then()
                .log().all()
                .statusCode(404)
                .extract()
                .body()
                .as(UserOut.class);

        UserOut expected = UserOut.builder()
                .code(1)
                .message("User not found")
                .type("error")
                .build();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void updateUserTest(){
        User user = UserGenerator.getExistUser();
        String name = "admin";

        Response response = userApi.updateUserByName(user.getUsername(), user);

        UserOut actual = response.then()
                .log().all()
                .statusCode(200)
                .extract()
                .body()
                .as(UserOut.class);

        UserOut expected = UserOut.builder()
                .code(200)
                .message(user.getId().toString())
                .type("unknown")
                .build();

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void deleteUserTest(){
        String name = "admin";

        Response response = userApi.deleteUserByName(name);

        UserOut actual = response.then()
                .log().all()
                .statusCode(404)
                .extract()
                .body()
                .as(UserOut.class);

        UserOut expected = UserOut.builder()
                .code(404)
                .build();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void loginUser(){
        String name = "admin";
        String password = "password";

        Response response = userApi.logInUser(name, password);

        UserOut actual = response.then()
                .log().all()
                .statusCode(200)
                .extract()
                .body()
                .as(UserOut.class);

        UserOut expected = UserOut.builder()
                .code(200)
                .message("")
                .type("unknown")
                .build();

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void logOutUser(){

        Response response = userApi.logOutUser();

        UserOut actual = response.then()
                .log().all()
                .statusCode(200)
                .extract()
                .body()
                .as(UserOut.class);

        UserOut expected = UserOut.builder()
                .code(200)
                .message("ok")
                .type("unknown")
                .build();

        Assertions.assertEquals(expected, actual);
    }
}
