package models.utils.services.mailServices;

import constants.config.ConfigData;
import constants.messages.ua_UA.MailMessages;
import models.shop.Order;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Calendar;
import java.util.Properties;

public class MailService {

    public MailStates sendEmail(Order order) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", ConfigData.MAIL_SERVER_HOST);
        properties.put("mail.smtp.port", ConfigData.MAIL_SMTP_PORT);
        properties.put("mail.smtp.ssl.trust", ConfigData.MAIL_SERVER_HOST);

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(ConfigData.MAIL_USER, ConfigData.MAIL_PASSWORD);
            }
        });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(ConfigData.MAIL_SENDER));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(ConfigData.MAIL_RECIPIENT));

            // Set Subject: header field
            message.setSubject(MailMessages.MAIL_SUBJECT_NEW_ORDER);

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Now set the actual message
            messageBodyPart.setText("" +
                    "You have new order:\t" + order.getOrderId() +
                    "\nFrom:\t" + order.getShoppingCart().getCustomer() +
                    "\nAt:\t" + order.getOrderDate().get(Calendar.DAY_OF_MONTH) + "." +
                    order.getOrderDate().get(Calendar.MONTH) + "\t" +
                    order.getOrderDate().get(Calendar.HOUR) + ":" +
                    order.getOrderDate().get(Calendar.MINUTE) +
                    "\nOrder status:\t" + order.getOrderStatus() +
                    "\nDelivery method:\t" + order.getDeliveryMethod() +
                    "\n\n" +
                    "Order Cart:\n" + order.getShoppingCart().toString());

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

            return MailStates.OK;

        } catch (MessagingException e) {
            e.printStackTrace();
            return MailStates.ERROR;
        }
    }
}
