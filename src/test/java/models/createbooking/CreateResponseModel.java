package models.createbooking;

import lombok.Data;

@Data
public class CreateResponseModel {
    private int bookingid;
    private BookingObjectResponseModel booking;
}
