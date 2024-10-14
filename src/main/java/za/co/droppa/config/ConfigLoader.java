package za.co.droppa.config;

import org.kohsuke.github.GHBranch;
import org.kohsuke.github.GHContent;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class ConfigLoader {

    private static Properties properties;

    private static ConfigLoader instance;

    private final String AUTH_TOKEN = "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";
    private final String REPO = "droppa2016/droppa-config";
    private final String BRANCH = "main";
    private final String FILE_NAME = "droppa-conf.properties";


    public ConfigLoader() {
        try {
            GitHub github = GitHub.connectUsingOAuth(AUTH_TOKEN);

            GHRepository repo = github.getRepository(REPO);

            GHBranch branch = repo.getBranch(BRANCH);

            GHContent fileContent = repo.getFileContent(FILE_NAME, branch.getName());

            loadProperties(fileContent.getContent());


        } catch (Exception e) {
            System.out.println("Unable to connect to repository: " + e.getMessage());
        }
    }

    public static synchronized ConfigLoader config() {
        if (instance == null) {
            instance = new ConfigLoader();
        }
        return instance;
    }

    private void loadProperties(String content) throws IOException {
        properties = new Properties();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new java.io.ByteArrayInputStream(content.getBytes())))) {
            properties.load(reader);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public String getMongoConnectionString() {
        return getProperty("mongo.url");
    }

    public String smtpUsername() { return getProperty("mail.smtp.username"); }

    public String smtpPwd() { return getProperty("mail.smtp.password"); }

    public String smtpFromEmail() {return getProperty("mail.smtp.from.email"); }

    public String smtpEnableAuth() {return getProperty("mail.smtp.auth"); }

    public String smtpTlsEnabled() {return getProperty("mail.smtp.starttls.enable"); }

    public String smtpHost() {return getProperty("mail.smtp.host"); }

    public String smtpSslPort() {return getProperty("mail.smtp.ssl.port"); }
    public String smtpNewRetailEmails() {return getProperty("mail.new.retail.notify.emails"); }

    public String smtpSslFactoryClass() {return getProperty("mail.smtp.socketFactory.class"); }

    public String smtpPort() {return getProperty("mail.smtp.port"); }

    public String smtpAccountEmail() {return getProperty("mail.smtp.accounts"); }

    public String smtpAccountPassword() { return getProperty("mail.smtp.accounts.password"); }

    public boolean isProduction() {return Boolean.parseBoolean(getProperty("app.production.environment")); }

    public String droppaBanner() { return getProperty("droppa.banner"); }

    public String bookingsEmail() {return getProperty("mail.smtp.bookings.email"); }

    public String driversEmail() {return getProperty("mail.smtp.driver.email"); }

    public String driversEmailPwd() {return getProperty("mail.smtp.driver.password"); }

    public String bookingsEmailPwd() {return getProperty("mail.smtp.bookings.password"); }

    public String contractPath() { return getProperty("droppa.contract.path"); }

    public String skynetDriverForm(){return getProperty("droppa.skynetForm.path");}

    public String skynetConsentForm(){return getProperty("droppa.consentFom.path");}

    public String bookingSkynetTrackingUrl() { return getProperty("booking.skynet.tracking.url"); }

    public String invoiceFile() { return getProperty("invoice.file.path"); }

    public String fleetAttachment() { return getProperty("fleet.file.path"); }

    public String thashanoEmail() { return getProperty("mail.smtp.thashano.email"); }
    public String thashanoEmailPwd() { return getProperty("mail.smtp.thashano.email.pwd"); }

    public String skynetEmail02() { return getProperty("mail.smtp.skynet.02.email"); }

    public String tenderCCRecipientEmail() { return getProperty("tender.cc.recipient.email"); }

    public String tenderRecipientEmail() { return getProperty("tender.recipient.email"); }

}
