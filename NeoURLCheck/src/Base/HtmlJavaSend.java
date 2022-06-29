

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class HtmlJavaSend {

    public void sendHtmlEmail(String host, String port,
                              final String userName, final String password, String toAddress,
                              String subject, String message) throws AddressException,
            MessagingException {

        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.tls.trust","mail.gmail.com"); // Could not convert socket to TLS; Server is not trusted
        properties.put("mail.debug", "true");

        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };

        Session session = Session.getInstance(properties, auth);

        // creates a new e-mail message
        Message msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = {new InternetAddress(toAddress)};
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());

        // set plain text message
        msg.setContent(message, "text/html; charset=utf-8");

        // sends the e-mail
        Transport.send(msg);

    }

    public static void main(String[] args) {
        // SMTP server information
        //String host = "smtp.naver.com";
        String host = "smtp.gmail.com"; // gmail 발신 서버
        String port = "587"; // gmail 발신 포트
        String mailFrom = "neolab.tester@gmail.com"; // gmail 발신 계정
        String password = "kyqrxebyukvqgdal"; //

        // outgoing message information
        String mailTo = "_qa@neolab.net";
        String subject = "메일테스트";

        // message contains HTML markups
        String message = "<i>send email!</i><br>";
        message += "<b> 한글테스트!</b><br>";
        message += "<font color=red>finish</font>";

        HtmlJavaSend mailer = new HtmlJavaSend();

        try {
            mailer.sendHtmlEmail(host, port, mailFrom, password, mailTo,
                    subject, message);
            System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Failed to sent email.");
            ex.printStackTrace();
        }
    }
}
