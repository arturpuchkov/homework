package dataGenerator;

import DTO.User;

public class UserGenerator {
    private static int id = 0;

    public static User getUser(){
        id++;
        return User.builder()
                .email("EMAIL")
                .userStatus(1)
                .firstName("FIRST NAME")
                .id(id)
                .lastName("Last NAME")
                .password("PASSWORD")
                .phone("+7(999)999-99-99")
                .username("USERNAME")
                .build();

    }

    public static User getExistUser(){
        return User.builder()
                .email("EMAIL")
                .userStatus(1)
                .firstName("FIRST NAME")
                .id(id)
                .lastName("Last NAME")
                .password("PASSWORD")
                .phone("+7(999)999-99-99")
                .username("USERNAME")
                .build();
    }
}
