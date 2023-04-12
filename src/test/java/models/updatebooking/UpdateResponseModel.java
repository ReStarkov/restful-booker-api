package models.updatebooking;

import lombok.Data;
import models.datesmodel.BookingDatesModel;

@Data
public class UpdateResponseModel {
    private String firstname, lastname, additionalneeds;
    private int totalprice;
    private boolean depositpaid;
    private BookingDatesModel bookingdates;
    public boolean isDepositpaid() {
        return depositpaid;
    }
}
