package usersApi;

import DTO.Store;
import DTO.StoreOut;
import DTO.StoreOutThree;
import DTO.StoreOutTwo;
import dataGenerator.StoreGenerator;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import services.StoreApi;


public class storeApiTest {
    private StoreApi storeApi = new StoreApi();

    @Test
    public void placeAndOrderPetTest(){
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

    @Test
    public void findByIdTest(){
        String id = String.valueOf(1);

        Response response = storeApi.findById(id);

        StoreOut actual = response.then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .as(StoreOut.class);

        StoreOut expected = StoreOut.builder()
                .id(Integer.parseInt(id))
                .petId(0)
                .quantity(0)
                .shipDate("2022-05-12T11:28:50.370+0000")
                .status("placed")
                .complete(true)
                .build();

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void deleteByIdTest(){
        String id = String.valueOf(1);

        Response response = storeApi.deleteById(id);

        StoreOutTwo actual = response.then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .as(StoreOutTwo.class);

        StoreOutTwo expected = StoreOutTwo.builder()
                .code(200)
                .type("unknown")
                .message(String.valueOf(1))
                .build();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void returnPetTest(){
        Response response = storeApi.returnPetByStatus();

        StoreOutThree actual = response.then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .as(StoreOutThree.class);

        StoreOutThree expected = StoreOutThree.builder()
                .available(1)
                .available11(2)
                .connector(3)
                .free(4)
                .notAvailable(5)
                .pending(6)
                .sold(6)
                .test(4)
                .status16636(6)
                .available(4)
                .totvs(5)
                .build();

        Assertions.assertEquals(expected, actual);

    }
}
