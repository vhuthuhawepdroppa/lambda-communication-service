package za.co.droppa.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import za.co.droppa.dto.email.BookingCancelledDTO;
import za.co.droppa.dto.email.BookingCreationDTO;
import za.co.droppa.dto.email.PartnerRegistrationDTO;
import za.co.droppa.service.email.EmailService;

import javax.mail.MessagingException;

@RestController
@EnableWebMvc
@RequestMapping("/mail")
@AllArgsConstructor
public class EmailController {

    EmailService emailService;

    @PostMapping(path = "/booking/creation")
    public ResponseEntity<String> sendBookingCreationBooking(@Valid @RequestBody BookingCreationDTO dto) {
        try {
            emailService.sendBookingCreationMail(dto);
            return ResponseEntity.ok("Mail sent successfully");
        } catch (MessagingException e) {

            System.out.println(String.format("Failed to send booking creation email: %s", e.getMessage()));

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send booking confirmation email.");
        }
    }

    @PostMapping(path = "/partner/registration")
    public ResponseEntity<String> sendPartnerRegistration(@Valid @RequestBody PartnerRegistrationDTO dto) {
        try {
            emailService.sendPartnerRegistrationMail(dto);
            return ResponseEntity.ok("Mail sent successfully");
        } catch (MessagingException e) {

            System.out.println(String.format("Failed to send booking creation email: %s", e.getMessage()));

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send booking confirmation email.");
        }
    }

    @PostMapping(path = "/booking/cancellation")
    public ResponseEntity<String> sendBookingCancellation(@Valid @RequestBody BookingCancelledDTO dto) {
        try {
            emailService.sendBookingCancellationMail(dto);
            return ResponseEntity.ok("Mail sent successfully");
        } catch (MessagingException e) {

            System.out.println(String.format("Failed to send booking creation email: %s", e.getMessage()));

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send booking confirmation email.");
        }
    }
}
