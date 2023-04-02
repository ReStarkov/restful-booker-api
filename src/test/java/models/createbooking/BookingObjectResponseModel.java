package models.createbooking;

import lombok.Data;

@Data
public class BookingObjectResponseModel {
    String firstname, lastname, additionalneeds;
    int totalprice;
    int  bookingid;
    boolean depositpaid;
    private BookingDatesModel bookingdates;
}
