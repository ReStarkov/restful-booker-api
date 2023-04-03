package tests;

import models.updatebooking.UpdateBodyRequestModel;
import models.updatebooking.UpdateResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.BaseSpec.requestSpec;
import static specs.BaseSpec.responseSpec;

public class UpdateBookingTest extends TestBase {

    public UpdateBodyRequestModel requestUpdate() {

        firstname = "NewMan";
        lastname = "NewFamily";
        checkIn = "2025-05-05";
        checkOut = "2026-07-11";
        totalPrice = 99;
        depositPaid = false;

        bookingDates.setCheckin(checkIn);
        bookingDates.setCheckout(checkOut);
        requestUpdate.setFirstname(firstname);
        requestUpdate.setLastname(lastname);
        requestUpdate.setTotalprice(totalPrice);
        requestUpdate.setDepositpaid(depositPaid);
        requestUpdate.setBookingdates(bookingDates);

        return requestUpdate;
    }

    @Test
    @DisplayName("Обновление бронирования с обязательными и опциональными полями")
    public void updateBookingAllFieldsTest() {

        int id = createBooking();

        requestUpdate = requestUpdate();
        requestUpdate.setAdditionalneeds("Sport");
        UpdateResponseModel response = step("Отправка запроса на обновление бронирования",
                () -> given()
                        .spec(requestSpec)
                        .body(requestUpdate)
                        .when()
                        .put("booking/" + id)
                        .then()
                        .spec(responseSpec)
                        .statusCode(200)
                        .extract().as(UpdateResponseModel.class));

        step("Проверка значения поля firstname полученного в ответе. Значение соответствует переданному в запросе" +
                " на обновление бронирования", () ->
                assertThat(response.getFirstname()).isEqualTo(firstname));

        step("Проверка значения поля lastname полученного в ответе. Значение соответствует переданному в запросе" +
                " на обновление бронирования", () ->
                assertThat(response.getLastname()).isEqualTo(lastname));

        step("Проверка значения поля totalPrice полученного в ответе. Значение соответствует переданному в запросе" +
                " на обновление бронирования", () ->
                assertThat(response.getTotalprice()).isEqualTo(99));

        step("Проверка значения поля depositpaid полученного в ответе. Значение соответствует переданному в запросе" +
                " на обновление бронирования", () ->
                assertThat(response.isDepositpaid()).isFalse());
        step("Проверка значения поля additionalneeds полученного в ответе. Значение соответствует переданному в запросе" +
                " на обновление бронирования", () ->
                assertThat(response.getAdditionalneeds()).isEqualTo("Sport"));
        step("Проверка дат бронирования. Дата начала соответствуют значению переданному в запросе на обновление " +
                " бронирования", () ->
                assertThat(response.getBookingdates().getCheckin().equals("2025-05-05")));
        step("Проверка дат бронирования. Дата окончания соответствуют значению переданному в запросе  на обновление " +
                " бронирования", () ->
                assertThat(response.getBookingdates().getCheckout().equals("2026-07-11")));

    }

    @Test
    @DisplayName("Обновление бронирования с обязательными полями, без опционального поля 'additionalneeds'")
    public void updateBookingRequiredFieldsTest() {

        int id = createBooking();

        requestUpdate = requestUpdate();

        UpdateResponseModel response = step("Отправка запроса на обновление бронирования",
                () -> given()
                        .spec(requestSpec)
                        .body(requestUpdate)
                        .when()
                        .put("booking/" + id)
                        .then()
                        .spec(responseSpec)
                        .statusCode(200)
                        .extract().as(UpdateResponseModel.class));

        step("Проверка значения поля firstname полученного в ответе. Значение соответствует переданному в запросе" +
                " на обновление бронирования", () ->
                assertThat(response.getFirstname()).isEqualTo(firstname));

        step("Проверка значения поля lastname полученного в ответе. Значение соответствует переданному в запросе" +
                " на обновление бронирования", () ->
                assertThat(response.getLastname()).isEqualTo(lastname));

        step("Проверка значения поля additionalneeds полученного в ответе. Значение поля не изменилось и осталось" +
                "с тем же значением, что и при создании бронирования", () ->
                assertThat(response.getAdditionalneeds()).isEqualTo("Breakfast"));

    }
}
