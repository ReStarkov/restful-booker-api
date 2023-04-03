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

public class DeleteBookingTest extends TestBase {

    @Test
    @DisplayName("Удаление существующего бронирования")
    public void deleteExistingBookingTest() {

        int id = createBooking();

        step("Отправка запроса на удаление бронирования и проверка результата. Ожидается ответ 201",
                () -> given()
                        .spec(requestSpec)
                        .when()
                        .delete("booking/" + id)
                        .then()
                        .spec(responseSpec)
                        .statusCode(201));

        step("Отправка запроса на получение информации по удаленному бронированию. Ожидается ответ 404",
                () -> given()
                        .spec(requestSpec)
                        .when()
                        .get("booking/" + id)
                        .then()
                        .spec(responseSpec)
                        .statusCode(404));
    }

    @Test
    @DisplayName("Удаление не существующего бронирования. В ответе ожидается ответ 405 согласно реализации сервиса")
    public void deleteNotExistingBookingTest() {


        step("Отправка запроса на удаление бронирования и проверка результата. Ожидается ответ 405",
                () -> given()
                        .spec(requestSpec)
                        .when()
                        .delete("booking/" + 0)
                        .then()
                        .spec(responseSpec)
                        .statusCode(405));

    }
}
