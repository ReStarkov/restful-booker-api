package tests;

import jdk.jfr.Description;
import models.getbooking.GetResponseModel;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.BaseSpec.requestSpec;
import static specs.BaseSpec.responseSpec;

public class GetBookingTest extends TestBase {

    @Test
    @Description("Получение информацию по существующему бронированию")
    public void getExistingBookingTest() {

        int id = createBooking();

        GetResponseModel response = step("Отправка запроса на получение данных о пользователе",
                () -> given(requestSpec)
                        .when()
                        .get("booking/" + id)
                        .then()
                        .spec(responseSpec)
                        .statusCode(200)
                        .extract().as(GetResponseModel.class));

        step("Проверка значения поля firstname полученного в ответе. Значение соответствует переданному в запросе" +
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
}
