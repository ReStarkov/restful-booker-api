package tests;

import models.datesmodel.BookingDatesModel;
import models.createbooking.CreateBodyRequestModel;
import models.createbooking.CreateResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.BaseSpec.requestSpec;
import static specs.BaseSpec.responseSpec;


public class CreateBookingTest {
    @Test
    @DisplayName("Создание бронирования с обязательными и опциональными полями")
    public void createBookingTest() {

        String firstname = "QA";
        String lastname = "GURU";
        String additionalNeeds = "Breakfast";
        String checkIn = "2023-01-01";
        String checkOut = "2023-04-17";
        int totalPrice = 17;
        boolean depositPaid = true;

        BookingDatesModel bookingDates = new BookingDatesModel();
        bookingDates.setCheckin(checkIn);
        bookingDates.setCheckout(checkOut);


        CreateBodyRequestModel request = new CreateBodyRequestModel();
        request.setFirstname(firstname);
        request.setLastname(lastname);
        request.setTotalprice(totalPrice);
        request.setDepositpaid(depositPaid);
        request.setAdditionalneeds(additionalNeeds);
        request.setBookingdates(bookingDates);


        CreateResponseModel response = given()
                .spec(requestSpec)
                .body(request)
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
                        .hasFieldOrPropertyWithValue("additionalneeds", additionalNeeds));

        step("Проверка дат бронирования. Дата начала соответствуют значению переданному в запросе на создание " +
                " бронирования", () ->
                assertThat(response.getBooking().getBookingdates().getCheckin().equals(checkIn)));
        step("Проверка дат бронирования. Дата окончания соответствуют значению переданному в запросе на создание " +
                " бронирования", () ->
                assertThat(response.getBooking().getBookingdates().getCheckout().equals(checkOut)));
    }
}
