package tests;

import io.qameta.allure.Step;
import models.createbooking.BookingDatesModel;
import models.createbooking.CreateBodyRequestModel;
import models.createbooking.CreateResponseModel;

import static io.restassured.RestAssured.given;
import static specs.BaseSpec.requestSpec;
import static specs.BaseSpec.responseSpec;

public class TestBase {
    @Step("Создание бронирования с полным перечнем полей")
    public int createBooking() {

        String firstname = "QA";
        String lastname = "GURU";
        int totalPrice = 17;
        boolean depositPaid = true;
        String additionalNeeds = "Breakfast";

        String checkIn = "2023-01-01";
        String checkOut = "2023-04-17";
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

        return response.getBookingid();
    }
}
