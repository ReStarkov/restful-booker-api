package models.createbooking;

import lombok.Data;
import models.datesmodel.BookingDatesModel;

@Data
public class CreateBodyRequestModel {
    private String firstname;
    private String lastname;
    private String additionalneeds;
    private Integer totalprice;
    private Boolean  depositpaid;
    private BookingDatesModel bookingdates;

    public CreateBodyRequestModel() {
        this.firstname = "defaultName";
        this.lastname = "defaultLastName";
        this.totalprice = 20;
        this.depositpaid = true;
    }
    public void setNullLastName() {
        this.lastname = null;
    }

    public void setNullFirstName() {
        this.firstname = null;
    }

    public void setNullTotalPrice() {
        this.totalprice = null;
    }

    public void setNullDepositPaid() {
        this.depositpaid = null;
    }

    public void setNullAdditionalNeeds() {
        this.additionalneeds = null;
    }

    public void setNullBookingdates() {
        this.bookingdates = null;
    }

}