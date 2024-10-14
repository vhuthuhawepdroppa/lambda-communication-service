package za.co.droppa.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class BucketBooking extends PersistentObject {

    private List<Booking> bookings;

    private Retail retail;

    private String retailOid;

    private double price;

    private String driverOid;

    private String dropOid;

    private String vehicleType;

    private LocalDateTime date;

    private String time;

    private String trackNo;

    private String comments;


    private boolean availableExpress;

    private boolean isExpress;

    private LocalDateTime bookingCreatedDate;

    private Type type;

    private String toEmail;
}
