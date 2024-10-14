package za.co.droppa.dto.email;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import za.co.droppa.dto.Booking;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class BookingCreationDTO extends MailDTO {

    @NotNull(message = "Bookings list is cannot be null")
    List<Booking> bookings;

}
