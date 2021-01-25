package models.utils.services.notifications;

import constants.Admin;
import constants.MailMessages_UA;

import java.io.File;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailSenderService {

    public void sendEmail(File file) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", Admin.MAIL_SERVER_HOST);
        properties.put("mail.smtp.port",  Admin.MAIL_SMTP_PORT);
        properties.put("mail.smtp.ssl.trust", Admin.MAIL_SERVER_HOST);

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Admin.MAIL_USER, Admin.MAIL_PASSWORD);
            }
        });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(Admin.MAIL_SENDER));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(Admin.MAIL_RECIPIENT));

            // Set Subject: header field
            message.setSubject(MailMessages_UA.MAIL_SUBJECT_NEW_ORDER);

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Now set the actual message
            messageBodyPart.setText("This is message body");

            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            String filename = "C:\\Users\\VovIra\\Desktop\\public class Admin.txt";
//            String filename = "DATAFILE.txt";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart);

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


//        try {
//            MimeMessage message = new MimeMessage(session); // email message
//            message.setFrom(new InternetAddress(Admin.MAIL_SENDER)); // setting header fields
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(Admin.MAIL_RECIPIENT));
//            message.setSubject(MailMessages_UA.MAIL_SUBJECT_NEW_ORDER); // subject line
//            // actual mail body
//
//            message.setText("You can send mail from Java program by using mail API, but you need" +
//                    "couple of more JAR files e.g. smtp.jar and activation.jar");
//            // Send message
//            Transport.send(message);
//            System.out.println("Email Sent successfully....");
//        } catch (
//                MessagingException mex) {
//            mex.printStackTrace();
//        }

    }


}
