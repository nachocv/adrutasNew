package adrutas.com;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Mail {

    public static void sendMail(Map<String, Object> mArgs) throws MessagingException {
        Session session = Session.getDefaultInstance(new Properties(), null);
        Message msg = new MimeMessage(session);
        msg.setFrom((Address) mArgs.get("from"));
        msg.addRecipient(Message.RecipientType.TO, (Address) mArgs.get("to"));
        msg.setSubject((String) mArgs.get("subject"));
        msg.setText((String) mArgs.get("msgBody"));
        Transport.send(msg);
    }

    public static void sendHtmlMail(Map<String, Object> mArgs) throws MessagingException {
        Session session = Session.getDefaultInstance(new Properties(), null);
        Message msg = new MimeMessage(session);
        msg.setFrom((Address) mArgs.get("from"));
        msg.addRecipient(Message.RecipientType.TO, (Address) mArgs.get("to"));
        msg.setSubject((String) mArgs.get("subject"));
        Multipart mp = new MimeMultipart();
        MimeBodyPart htmlPart = new MimeBodyPart();
        htmlPart.setContent(mArgs.get("htmlBody"), "text/html");
        mp.addBodyPart(htmlPart);
        msg.setContent(mp);
        Transport.send(msg);
    }

    public static void sendMailBCC(Map<String, Object> mArgs) throws MessagingException, UnsupportedEncodingException {
        Session session = Session.getDefaultInstance(new Properties(), null);
        Message msg = new MimeMessage(session);
        Multipart mp = new MimeMultipart();
        MimeBodyPart htmlPart = new MimeBodyPart();
        List<Map<String, Object>> list = (List<Map<String, Object>>) mArgs.get("bcc");
        Address[] addresses = new Address[list.size()];
        int i = 0;
        for (Map<String, Object> bean: list) {
            addresses[i] = new InternetAddress((String) bean.get("email"), (String) bean.get("nombre"));
            i++;
        }
        msg.setFrom((Address) mArgs.get("from"));
        msg.addRecipients(Message.RecipientType.BCC, addresses);
        msg.setSubject((String) mArgs.get("subject"));
        htmlPart.setContent(mArgs.get("msgBody"), "text/html");
        mp.addBodyPart(htmlPart);
        msg.setContent(mp);
        Transport.send(msg);
    }
}
