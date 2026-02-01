package builder;

import models.SendUser.SendUserRequestBody;
import net.datafaker.Faker;

public class SendUserRequestBodyFactory {

  private static final Faker FAKER = new Faker();

  public static SendUserRequestBody weddingRequest() {
    return SendUserRequestBody.builder()
        .mode("wedding")
        .personalLastName(FAKER.name().lastName())
        .personalFirstName(FAKER.name().firstName())
        .personalMiddleName(FAKER.name().firstName())
        .personalPhoneNumber("2365984")
        .personalNumberOfPassport("GH234345")
        .personalAddress(FAKER.address().city())
        .dateOfMarriage("2026-06-06")
        .anotherPersonFirstName(FAKER.name().firstName())
        .anotherPersonLastName(FAKER.name().lastName())
        .anotherPersonMiddleName(FAKER.name().firstName())
        .anotherPersonPassport("KJ343453")
        .birthOfAnotherPerson("2006-08-06")
        .citizenFirstName(FAKER.name().firstName())
        .citizenMiddleName(FAKER.name().firstName())
        .citizenBirthDate("2008-05-06")
        .citizenLastName(FAKER.name().lastName())
        .citizenGender(FAKER.demographic().sex())
        .newLastName(FAKER.name().lastName())
        .citizenNumberOfPassport("JF48394")
        .citizenAddress(FAKER.address().city())
        .build();
  }
}
