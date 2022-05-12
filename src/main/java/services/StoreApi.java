package services;

import DTO.Store;
import com.atlassian.oai.validator.restassured.OpenApiValidationFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class StoreApi {

    private final String BASE_URI = "https://petstore.swagger.io/v2/";
    private final  String SWAGGER_JSON = "https://petstore.swagger.io/v2/swagger.json";

    private final OpenApiValidationFilter validationFilter = new OpenApiValidationFilter(SWAGGER_JSON);

    private RequestSpecification spec;

    public StoreApi(){
        spec = given()
                .baseUri(BASE_URI)
                .filter(validationFilter)
                .log().all();


    }

    public Response orderPlacePet(Store store){
        return given(spec)
                .contentType(ContentType.JSON)
                .body(store)
                .when()
                .post("/store/order");

    }

    public Response findById(String id){
        return given(spec)
                .when()
                .get("/store/order/" + id);
    }

    public Response deleteById(String id){
        return given()
                .baseUri(BASE_URI)
                .when()
                .delete("/store/order/" + id);
    }

    public  Response returnPetByStatus(){
        return given()
                .baseUri(BASE_URI)
                .when()
                .get("/store/inventory/");
    }



}
