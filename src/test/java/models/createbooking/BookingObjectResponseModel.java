package models.createbooking;

import lombok.Data;
import models.datesmodel.BookingDatesModel;

@Data
public class BookingObjectResponseModel {
    private String firstname, lastname, additionalneeds;
    private int totalprice;
    private boolean depositpaid;
    private BookingDatesModel bookingdates;
}
