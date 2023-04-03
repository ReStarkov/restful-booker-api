package tests;

import models.getbooking.GetResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.BaseSpec.requestSpec;
import static specs.BaseSpec.responseSpec;

public class GetBookingTest extends TestBase {

    @Test
    @DisplayName("Получение информацию по существующему бронированию")
    public void getExistingBookingTest() {

        int id = createBooking();

        GetResponseModel response = step("Отправка запроса на получение данных о бронировании",
                () -> given(requestSpec)
                        .when()
                        .get("booking/" + id)
                        .then()
                        .spec(responseSpec)
                        .statusCode(200)
                        .extract().as(GetResponseModel.class));

        step("Проверка значения поля firstName полученного в ответе. Значение соответствует переданному в запросе" +
                " на создание бронирования", () ->
                assertThat(response.getFirstname().equals("defaultName")));
        step("Проверка значения поля lastname полученного в ответе. Значение соответствует переданному в запросе" +
                " на создание бронирования", () ->
                assertThat(response.getFirstname().equals("defaultLastName")));
        step("Проверка значения поля totalprice полученного в ответе. Значение соответствует переданному в запросе" +
                " на создание бронирования", () ->
                assertThat(response.getTotalprice()).isEqualTo(20));
        step("Проверка значения поля depositpaid полученного в ответе. Значение соответствует переданному в запросе" +
                " на создание бронирования", () ->
                assertThat(response.isDepositpaid()).isTrue());
        step("Проверка значения поля additionalneeds полученного в ответе. Значение соответствует переданному в запросе" +
                " на создание бронирования", () ->
                assertThat(response.getAdditionalneeds()).isEqualTo("Breakfast"));
        step("Проверка дат бронирования. Дата начала соответствуют значению переданному в запросе на создание " +
                " бронирования", () ->
                assertThat(response.getBookingdates().getCheckin().equals("2023-01-01")));
        step("Проверка дат бронирования. Дата окончания соответствуют значению переданному в запросе на создание " +
                " бронирования", () ->
                assertThat(response.getBookingdates().getCheckout().equals("2023-04-17")));

    }

    @Test
    @DisplayName("Получение информацию по не существующему бронированию. В ответе ожидается код ошибки 404")
    public void getNotExistingBookingTest() {

        step("Отправка запроса на получение данных о бронировании и проверка результата",
                () -> given(requestSpec)
                        .when()
                        .get("booking/" + 0)
                        .then()
                        .spec(responseSpec)
                        .statusCode(404));
    }
}
