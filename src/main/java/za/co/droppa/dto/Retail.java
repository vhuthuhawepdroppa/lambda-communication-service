package za.co.droppa.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class Retail extends PersistentObject  {

    private String registrationNumber;


    private String name;

    private Person owner;

    private LocalDateTime invoiceDate;

    private String vatNo;

    private String accountManagerOid;

    private boolean circleRouteRate;

    public boolean priority;

    private String whitelistingId;

}
