package models.getbooking;

import lombok.Data;
import models.datesmodel.BookingDatesModel;

@Data
public class GetResponseModel {
    String firstname, lastname, additionalneeds;
    int totalprice;
    boolean depositpaid;
    BookingDatesModel bookingdates;

    public boolean isDepositpaid() {
        return depositpaid;
    }
}
