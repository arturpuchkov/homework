package dataGenerator;

import DTO.Store;

public class StoreGenerator {

    private static int id = 0;

    public static Store getStore(){
        id++;
        return Store.builder()
                .id(id)
                .petId(0)
                .quantity(0)
                .shipDate("2022-05-12T11:28:50.370Z")
                .status("placed")
                .complete(true)
                .build();



    }
}


