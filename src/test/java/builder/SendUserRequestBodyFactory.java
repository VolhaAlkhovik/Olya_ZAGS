package builder;

import models.SendUser.SendUserRequestBody;
import net.datafaker.Faker;

public class SendUserRequestBodyFactory {

    private static final Faker faker = new Faker();

    public static SendUserRequestBody weddingRequest(){
        return SendUserRequestBody.builder()
                .mode("wedding")
                .personalLastName(faker.name().lastName())
                .personalFirstName(faker.name().firstName())
                .personalMiddleName(faker.name().firstName())
                .personalPhoneNumber("2365984")
                .personalNumberOfPassport("GH234345")
                .personalAddress(faker.address().city())
                .dateOfMarriage("2026-06-06")
                .anotherPersonFirstName(faker.name().firstName())
                .anotherPersonLastName(faker.name().lastName())
                .anotherPersonMiddleName(faker.name().firstName())
                .anotherPersonPassport("KJ343453")
                .birthOfAnotherPerson("2006-08-06")
                .citizenFirstName(faker.name().firstName())
                .citizenMiddleName(faker.name().firstName())
                .citizenBirthDate("2008-05-06")
                .citizenLastName(faker.name().lastName())
                .citizenGender(faker.demographic().sex())
                .newLastName(faker.name().lastName())
                .citizenNumberOfPassport("JF48394")
                .citizenAddress(faker.address().city())
                .build();
    }
}
