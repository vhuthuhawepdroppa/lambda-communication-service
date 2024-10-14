package za.co.droppa.dto;
import lombok.Data;

import java.util.List;


@Data
public class BookingMetaData
{
    private String dropOffCountry;
    private String exportCode;
    private String zip;
    private String state;
    private List<String> files;

}
