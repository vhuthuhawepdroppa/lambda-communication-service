package za.co.droppa.dto;

import lombok.Data;

@Data
public class CommodityItems
{
    private String id;
    private String category;
    private String description_retail;
    private int quantity;
    private String hs_code;
    private double amount;
    private String description;
    private String detail;
    private double vatAndDuties;
    private String country_of_origin;
}
