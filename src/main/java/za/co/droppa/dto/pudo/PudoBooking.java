package za.co.droppa.dto.pudo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import za.co.droppa.dto.PersistentObject;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class PudoBooking extends PersistentObject {
    private String transactionId;
    private String reservationType;

    private LocalDateTime bookingCreatedDate;
    private String customerEmail;
    private String wayBillNumber;
    private String collectionNumber;
    private String pin;
    private double price;
    private String receiverName;
    private String receiverCompanyName;
    private String receiverEmail;
    private String receiverPhone;
    private String senderName;
    private String senderEmail;
    private String senderPhone;
    private ToAddress toAddress;
    private ToAddress fromAddress;
    private String fromTerminalId;
    private String fromterminalAddress;
    private String ToterminalAddress;
    private String toTerminalId;
    private LocalDateTime creationStamp;
}
