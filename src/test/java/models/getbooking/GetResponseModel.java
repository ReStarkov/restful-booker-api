package models.getbooking;

import lombok.Data;
import models.datesmodel.BookingDatesModel;

@Data
public class GetResponseModel {
    private String firstname, lastname, additionalneeds;
    private int totalprice;
    private boolean depositpaid;
    private BookingDatesModel bookingdates;
    public boolean isDepositpaid() {
        return depositpaid;
    }
}
