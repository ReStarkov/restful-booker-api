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
    public void removeLastName() {
        this.lastname = null;
    }

    public void removeFirstName() {
        this.firstname = null;
    }

    public void removeTotalPrice() {
        this.totalprice = null;
    }

    public void removeDepositPaid() {
        this.depositpaid = null;
    }

    public void removeAdditionalNeeds() {
        this.additionalneeds = null;
    }

    public void removeBookingdates() {
        this.bookingdates = null;
    }

}