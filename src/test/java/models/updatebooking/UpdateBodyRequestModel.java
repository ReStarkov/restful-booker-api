package models.updatebooking;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import models.datesmodel.BookingDatesModel;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateBodyRequestModel {
    private String firstname;
    private String lastname;
    private String additionalneeds;
    private Integer totalprice;
    private Boolean  depositpaid;
    private BookingDatesModel bookingdates;
}