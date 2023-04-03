package tests;

import models.createbooking.CreateBodyRequestModel;
import models.createbooking.CreateResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.BaseSpec.requestSpec;
import static specs.BaseSpec.responseSpec;


public class CreateBookingTest extends TestBase {

    public CreateBodyRequestModel createData() {

        firstName = "QA";
        lastName = "GURU";
        checkIn = "2023-01-01";
        checkOut = "2023-04-17";
        totalPrice = 17;
        depositPaid = true;
        additionalNeed = "Breakfast";

        bookingDates.setCheckin(checkIn);
        bookingDates.setCheckout(checkOut);

        requestCreate.setFirstname(firstName);
        requestCreate.setLastname(lastName);
        requestCreate.setTotalprice(totalPrice);
        requestCreate.setDepositpaid(depositPaid);
        requestCreate.setBookingdates(bookingDates);
        requestCreate.setAdditionalneeds(additionalNeed);

        return requestCreate;
    }

    @Test
    @DisplayName("Создание бронирования с обязательными и опциональными полями")
    public void createBookingAllFieldsTest() {

        requestCreate = createData();
        CreateResponseModel response = step("Отправка запроса на создание бронирования",
                () -> given()
                        .spec(requestSpec)
                        .body(requestCreate)
                        .when()
                        .post("/booking")
                        .then()
                        .spec(responseSpec)
                        .statusCode(200)
                        .extract().as(CreateResponseModel.class));

        step("Проверка значения поля BookingId полученного в ответе. Поле не null, а также больше чем '0'", () ->
                assertThat(response.getBookingid()).isGreaterThan(0));

        step("Проверка объекта 'booking' который возвращается в ответе. Поля содержат данные переданные в " +
                " запросе на создание бронирования", () ->
                assertThat(response.getBooking())
                        .hasFieldOrPropertyWithValue("firstname", firstName)
                        .hasFieldOrPropertyWithValue("lastname", lastName)
                        .hasFieldOrProperty("bookingdates")
                        .hasFieldOrPropertyWithValue("totalprice", totalPrice)
                        .hasFieldOrPropertyWithValue("depositpaid", depositPaid)
                        .hasFieldOrPropertyWithValue("additionalneeds", "Breakfast"));

        step("Проверка дат бронирования. Дата начала соответствуют значению переданному в запросе на создание " +
                " бронирования", () ->
                assertThat(response.getBooking().getBookingdates().getCheckin().equals(checkIn)));
        step("Проверка дат бронирования. Дата окончания соответствуют значению переданному в запросе на создание " +
                " бронирования", () ->
                assertThat(response.getBooking().getBookingdates().getCheckout().equals(checkOut)));
    }

    @Test
    @DisplayName("Создание бронирования с обязательными полями и без опциональеного поля 'additionalneed'")
    public void createBookingRequiredTest() {

        requestCreate = createData();
        requestCreate.setNullAdditionalNeeds();
        CreateResponseModel response = step("Отправка запроса на создание бронирования",
                () -> given()
                        .spec(requestSpec)
                        .body(requestCreate)
                        .when()
                        .post("/booking")
                        .then()
                        .spec(responseSpec)
                        .statusCode(200)
                        .extract().as(CreateResponseModel.class));

        step("Проверка значения поля BookingId полученного в ответе. Поле не null, а также больше чем '0'", () ->
                assertThat(response.getBookingid()).isGreaterThan(0));

        step("В ответе отсутствует опциональное поле 'additionalneed'", () ->
                assertThat(response.getBooking().getAdditionalneeds()).isNull());
    }


    static Stream<String> optionalFieldsProvider() {
        return Stream.of("lastName", "firstName", "totalPrice", "depositPaid", "bookingDates");
    }

    @ParameterizedTest
    @MethodSource("optionalFieldsProvider")
    @DisplayName("Создание бронирования со значением 'null' в обязательных полях. В ответе " +
            " ожидается ответ код 500 согласно реализации сервиса")
    public void testCreateBookingWithoutOptionalFields(String fieldName) {

        requestCreate = createData();

        switch (fieldName) {
            case "lastName":
                requestCreate.setNullLastName();
                break;
            case "firstName":
                requestCreate.setNullFirstName();
                break;
            case "totalPrice":
                requestCreate.setNullTotalPrice();
                break;
            case "depositPaid":
                requestCreate.setNullDepositPaid();
                break;
            case "bookingDates":
                requestCreate.setNullBookingdates();
                break;
            default:
                throw new IllegalArgumentException("Invalid field name: " + fieldName);
        }

        given()
                .spec(requestSpec)
                .body(requestCreate)
                .when()
                .post("/booking")
                .then()
                .spec(responseSpec)
                .statusCode(500);
    }
}
