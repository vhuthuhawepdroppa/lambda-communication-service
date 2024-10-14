package za.co.droppa.dto.email;

import lombok.Data;

import java.util.List;

@Data
public class MailDTO {
    List<String> recipients;
    String subject;
    String personName;
}
