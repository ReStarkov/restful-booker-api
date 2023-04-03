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

        firstname = "QA";
        lastname = "GURU";
        checkIn = "2023-01-01";
        checkOut = "2023-04-17";
        totalPrice = 17;
        depositPaid = true;
        additionalneed = "Breakfast";

        bookingDates.setCheckin(checkIn);
        bookingDates.setCheckout(checkOut);

        requestCreate.setFirstname(firstname);
        requestCreate.setLastname(lastname);
        requestCreate.setTotalprice(totalPrice);
        requestCreate.setDepositpaid(depositPaid);
        requestCreate.setBookingdates(bookingDates);
        requestCreate.setAdditionalneeds(additionalneed);

        return requestCreate;
    }

    @Test
    @DisplayName("Создание бронирования с обязательными и опциональными полями")
    public void createBookingAllFieldsTest() {

        requestCreate = createData();
        CreateResponseModel response = given()
                .spec(requestSpec)
                .body(requestCreate)
                .when()
                .post("/booking")
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .extract().as(CreateResponseModel.class);

        step("Проверка значения поля BookingId полученного в ответе. Поле не null, а также больше чем '0'", () ->
                assertThat(response.getBookingid()).isNotNull().isGreaterThan(0));

        step("Проверка объекта 'booking' который возвращается в ответе. Поля содержат данные переданные в " +
                " запросе на создание бронирования", () ->
                assertThat(response.getBooking())
                        .hasFieldOrPropertyWithValue("firstname", firstname)
                        .hasFieldOrPropertyWithValue("lastname", lastname)
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
        requestCreate.removeAdditionalNeeds();
        CreateResponseModel response = given()
                .spec(requestSpec)
                .body(requestCreate)
                .when()
                .post("/booking")
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .extract().as(CreateResponseModel.class);

        step("Проверка значения поля BookingId полученного в ответе. Поле не null, а также больше чем '0'", () ->
                assertThat(response.getBookingid()).isNotNull().isGreaterThan(0));

        step("В ответе отсутствует опциональное поле 'additionalneed'", () ->
                assertThat(response.getBooking().getAdditionalneeds()).isNull());
    }


    static Stream<String> optionalFieldsProvider() {
        return Stream.of("lastname", "firstname", "totalprice", "depositpaid", "bookingdates");
    }

    @ParameterizedTest
    @MethodSource("optionalFieldsProvider")
    @DisplayName("Создание бронирования без обязательного поля. В ответе ожидается ответ код 500 согласно " +
            " реализации сервиса")
    public void testCreateBookingWithoutOptionalFields(String fieldName) {
        requestCreate = createData();

        switch (fieldName) {
            case "lastname":
                requestCreate.removeLastName();
                break;
            case "firstname":
                requestCreate.removeFirstName();
                break;
            case "totalprice":
                requestCreate.removeTotalPrice();
                break;
            case "depositpaid":
                requestCreate.removeDepositPaid();
                break;
            case "bookingdates":
                requestCreate.removeBookingdates();
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
