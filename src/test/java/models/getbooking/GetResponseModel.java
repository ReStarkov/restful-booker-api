package models.getbooking;

import lombok.Data;

@Data
public class GetResponseModel {
    String firstname, lastname, additionalneeds;
    int totalprice;
    boolean depositpaid;
    Object bookingdates;

}
