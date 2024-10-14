package za.co.droppa.dto;

import lombok.Data;

@Data
public class ParcelDimension {

    private double parcel_length;

    private double parcel_breadth;

    private double parcel_height;

    private double parcel_mass;

    private String parcel_reference;
    private String description;
    private String parcel_number;
    private double volume;
    private String item;
    private int quantity;
    private String hs_code;
    private String price;

}
