package org.Task1.Models;

public class UsersExample {
    public static UserEntity createUserExample() {
        GeoEntity geo = new GeoEntity("40.7128", "-74.0060");
        geo.setLat("40.7128");
        geo.setLng("-74.0060");

        AddressEntity address = new AddressEntity("4 Privet Drive", "Apt 7", "Little Whinging", "12345", geo);

        CompanyEntity company = new CompanyEntity("Slytherin Co.", "Proud and Ambitious", "Wizarding Markets Leader");
        company.setName("Slytherin Co.");
        company.setCatchPhrase("Proud and Ambitious");
        company.setBs("Wizarding Markets Leader");

        UserEntity user = new UserEntity(11, "Draco Malfoy", "dracomalfoy", "draco.malfoy@example.com", address, "1-234-567-8901", "malfoy.manor", company);

        return user;
    }
}
