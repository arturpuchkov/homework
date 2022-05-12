package services;

import DTO.User;
import com.atlassian.oai.validator.restassured.OpenApiValidationFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class UserApi {
    private final String BASE_URI = "https://petstore.swagger.io/v2/";
    private final  String SWAGGER_JSON = "https://petstore.swagger.io/v2/swagger.json";

    private final OpenApiValidationFilter validationFilter = new OpenApiValidationFilter(SWAGGER_JSON);

    private RequestSpecification spec;


    public UserApi(){
        spec = given()
                .baseUri(BASE_URI)
                .filter(validationFilter)
                .log().all();


    }


    public Response createOneUser(User user){
        return given(spec)
                .contentType(ContentType.JSON)
                .body(user)
        .when()
                .post("/user");


    }

    public Response createArrayUser(User[] users){
        return given(spec)
                .contentType(ContentType.JSON)
                .body(users)
        .when()
                .post("/user/createWithArray");
    }

    public Response createListUsers(ArrayList<User> users){
        return given(spec)
                .contentType(ContentType.JSON)
                .body(users)
                .when()
                .post("/user/createWithList");

    }

    public Response getUserByName(String name){
        return given(spec)
                .when()
                .get("/user/" + name);

    }

    public Response updateUserByName(String name, User user){
        return given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .put("/user/" + name);
    }

    public  Response deleteUserByName(String name){
        return given(spec)
                .when()
                .delete("/user/" + name);
    }

    public  Response logInUser(String name, String password){
        return given()
                .baseUri(BASE_URI)
                .when()
                .params("username", name, "password", password)
                .get("/user/login");
    }

    public Response logOutUser() {
        return given(spec)
                .when()
                .get("/user/logout");
    }

}
