package models.createbooking;

import lombok.Data;

@Data
public class CreateResponseModel {
    int  bookingid;
    private BookingObjectResponseModel booking;
}
