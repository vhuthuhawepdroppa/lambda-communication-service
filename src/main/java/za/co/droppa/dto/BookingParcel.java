package za.co.droppa.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingParcel{

    private String parcel_number;
    private double parcel_length;
    private double parcel_breadth;
    private double parcel_height;
    private double parcel_mass;
    private String customer_reference;
    private String costCenter;
    private String noBox;



}
