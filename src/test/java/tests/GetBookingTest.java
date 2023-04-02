package tests;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static specs.BaseSpec.requestSpec;
import static specs.BaseSpec.responseSpec;

public class GetBookingTest extends TestBase {


    @Test
    @Description("Получение информацию по существующему бронированию")
    public void getExistingBookingTest() {

        int id = createBooking();

        given(requestSpec)
                .when()
                .get("booking/" + id)
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .body("firstname.", is("QA"));
    }


}
