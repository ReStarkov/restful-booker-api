package models.getbooking;

import lombok.Data;

@Data
public class GetResponseModel {
    String firstname, lastname, additionalneeds;
    int totalprice;
    boolean depositpaid;
    Object bookingdates;

    //TO DO переделать по аналогии с созданием, вынести даты в отдельный оьъект

}
