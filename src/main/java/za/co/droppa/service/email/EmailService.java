package za.co.droppa.service.email;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import za.co.droppa.dto.Pair;
import za.co.droppa.dto.email.BookingCancelledDTO;
import za.co.droppa.dto.email.BookingCreationDTO;
import za.co.droppa.dto.email.PartnerRegistrationDTO;
import za.co.droppa.service.email.sender.MailSender;
import za.co.droppa.util.ThymleafTemplatesUtil;

import javax.mail.MessagingException;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static za.co.droppa.config.ConfigLoader.config;


@Service
@AllArgsConstructor
public class EmailService {

    AttachmentService attachmentService;

    public void sendBookingCreationMail(BookingCreationDTO dto) throws MessagingException {
        String subject = dto.getSubject();
        String personName = dto.getPersonName();
        String body = ThymleafTemplatesUtil.bookingCreationTemplate(dto.getBookings().get(0), personName);

        List<Pair<String, InputStream>> attachments = attachmentService.bookingCreationAttachments(dto.getBookings().get(0).getTrackNo());

        MailSender sender = new MailSender(config().bookingsEmail(), config().bookingsEmailPwd(),
                subject,dto.getRecipients(),body,attachments);

        sender.send();

    }

    public void sendPartnerRegistrationMail(PartnerRegistrationDTO dto) throws MessagingException {
        String subject = dto.getSubject();
        String personName = dto.getPersonName();
        String body = ThymleafTemplatesUtil.partnerRegistrationTemplate(personName);

        MailSender sender = new MailSender(config().bookingsEmail(), config().bookingsEmailPwd(),
                subject,dto.getRecipients(),body,new ArrayList<>());

        sender.send();
    }

    public void sendBookingCancellationMail(BookingCancelledDTO dto) throws MessagingException {
        String subject = dto.getSubject();
        String personName = dto.getPersonName();
        String body = ThymleafTemplatesUtil.cancelBookingTemplate(personName,dto.getPickup(),dto.getDropoff());

        MailSender sender = new MailSender(config().bookingsEmail(), config().bookingsEmailPwd(),
                subject,dto.getRecipients(),body,new ArrayList<>());

        sender.send();
    }
}
