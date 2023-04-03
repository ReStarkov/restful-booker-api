package tests;

import io.qameta.allure.Step;
import models.datesmodel.BookingDatesModel;
import models.createbooking.CreateBodyRequestModel;
import models.createbooking.CreateResponseModel;
import models.getbooking.GetResponseModel;
import models.updatebooking.UpdateBodyRequestModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.BaseSpec.requestSpec;
import static specs.BaseSpec.responseSpec;

public class TestBase {

    BookingDatesModel bookingDates = new BookingDatesModel();
    CreateBodyRequestModel requestCreate = new CreateBodyRequestModel();
    UpdateBodyRequestModel requestUpdate = new UpdateBodyRequestModel();
    String firstName;
    String additionalNeed;
    String lastName;
    String checkIn;
    String checkOut;
    int totalPrice;
    boolean depositPaid;

    @Step("Создание бронирования с полным перечнем полей. Получение id созданного бронирования")
    public int createBooking() {

        String checkIn = "2023-01-01";
        String checkOut = "2023-04-17";
        bookingDates.setCheckin(checkIn);
        bookingDates.setCheckout(checkOut);

        requestCreate.setAdditionalneeds("Breakfast");
        requestCreate.setBookingdates(bookingDates);

        CreateResponseModel response = given()
                .spec(requestSpec)
                .body(requestCreate)
                .when()
                .post("/booking")
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .extract().as(CreateResponseModel.class);

        return response.getBookingid();
    }

    @Step("Получение информации по бронированию по bookingId")
    public GetResponseModel getBookingInfo(int id) {

        GetResponseModel response = step("Отправка запроса на получение данных о пользователе",
                () -> given(requestSpec)
                        .when()
                        .get("booking/" + id)
                        .then()
                        .spec(responseSpec)
                        .statusCode(200)
                        .extract().as(GetResponseModel.class));

        return response;
    }
}
