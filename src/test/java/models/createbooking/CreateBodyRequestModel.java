package models.createbooking;

import lombok.Data;
import models.datesmodel.BookingDatesModel;

@Data
public class CreateBodyRequestModel {
    private String firstname;
    private String lastname;
    private String additionalneeds;
    private int totalprice;
    private boolean depositpaid;
    private BookingDatesModel bookingdates;

}