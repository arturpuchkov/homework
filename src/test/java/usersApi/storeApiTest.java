package usersApi;

import DTO.Store;
import DTO.StoreOut;
import DTO.UserOut;
import dataGenerator.StoreGenerator;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import services.StoreApi;


public class storeApiTest {
    private StoreApi storeApi = new StoreApi();

    @Test
    public void placeAndOrderPet(){
        Store store = StoreGenerator.getStore();

        Response response = storeApi.orderPlacePet(store);

        StoreOut actual = response.then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .as(StoreOut.class);

        StoreOut expected = StoreOut.builder()
                        .id(store.getId())
                                .petId(0)
                                        .quantity(0)
                                                .shipDate("2022-05-12T11:28:50.370+0000")
                                                        .status("placed")
                                                                .complete(true)
                                                                        .build();

        Assertions.assertEquals(expected, actual);

    }
}
