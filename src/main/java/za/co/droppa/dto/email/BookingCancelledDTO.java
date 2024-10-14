package za.co.droppa.dto.email;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BookingCancelledDTO extends MailDTO {
    String pickup;
    String dropoff;
}
