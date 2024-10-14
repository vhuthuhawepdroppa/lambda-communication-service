package za.co.droppa.service.email.sender;

import za.co.droppa.dto.Pair;

import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import static za.co.droppa.config.ConfigLoader.config;

public class MailSender {
    private final static Properties props;

    static {
        props = new Properties();

        props.put("mail.smtp.auth", config().smtpEnableAuth());
        props.put("mail.smtp.host", config().smtpHost());
        props.put("mail.smtp.port", config().smtpPort());
        props.put("mail.smtp.socketFactory.port", config().smtpSslPort()); //SSL Port
        props.put("mail.smtp.socketFactory.class", config().smtpSslFactoryClass());
    }

    protected Message message;
    protected Session session;
    protected BodyPart messageBodyPartBody;
    protected Multipart multipart;
    List<Pair<String,InputStream>> attachments;
    List<String> recipients;
    String body;
    String subject;

    public MailSender(String username, String password, String subject, List<String> recipients,
                         String body, List<Pair<String,InputStream>> attachments) {

        session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                if (!config().isProduction())
                    return new PasswordAuthentication("test@droppa.co.za", "Test@20_*");
                return new PasswordAuthentication(username, password);
            }
        });


        message = new MimeMessage(session);

        messageBodyPartBody = new MimeBodyPart();
        multipart = new MimeMultipart();


        this.attachments = attachments;
        this.body = body;
        this.recipients = recipients;
        this.subject = subject;


    }

    protected void setMessageHeaders() throws MessagingException {
        message.addHeader("Content-type", "text/html; charset=UTF-8");
        message.addHeader("format", "flowed");
        message.addHeader("Content-Transfer-Encoding", "8bit");
    }

    public void send() throws MessagingException {
        createEmail();
        System.out.println("executing email send");
        Transport.send(message);
    }

    private void setFrom() throws MessagingException {
        if (!config().isProduction())
            message.setFrom(new InternetAddress("test@droppa.co.za"));
        else
            message.setFrom(new InternetAddress("test@droppa.co.za"));
    }

    private void createEmail() throws MessagingException {
        setFrom();
        message.setSubject(subject);
        setMessageHeaders();
        setRecipients(recipients);

        message.setContent(body, "text/html");

        messageBodyPartBody.setContent(body, "text/html");

        addAttachments(attachments);

        multipart.addBodyPart(messageBodyPartBody);

        message.setContent(multipart);
    }

    private void addAttachments(List<Pair<String, InputStream>> attachments) {
        try {
            for (Pair<String, InputStream> stream : attachments) {
                MimeBodyPart attachmentPart = new MimeBodyPart();
                attachmentPart.setDataHandler(new DataHandler(new ByteArrayDataSource(stream.getValue(), "application/pdf"))); // Change MIME type as needed
                attachmentPart.setFileName(stream.getKey());
                multipart.addBodyPart(attachmentPart);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("unable to add attachments");
        }

    }

    private void setRecipients(List<String> recipients){
        try {
            String addressesString = String.join(",", recipients);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(addressesString));
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("unable to set recipients");
        }
    }

}
