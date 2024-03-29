package utility;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

class Auth extends Authenticator {
    public PasswordAuthentication getPasswordAuthentication() {
        String username = "it.kmitl.system";
        String password = "it.kmitl.system2016";
        System.out.println("Authenticating...");
        return new PasswordAuthentication(username, password);
    }
}

public class EmailSender {
    
    
    public Boolean sendEmail(String mailTo, String sub, String tex) {
        
        Properties prop = new Properties();
        prop.put("mail.transport.protocol", "smtp");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");        
        Session session = Session.getDefaultInstance(prop, new Auth());
        
        
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("gmail.com","IT KMITL"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailTo));
            message.setSubject(sub);
            message.setContent(tex, "text/html;charset=UTF-8");
            Transport.send(message);
            System.out.println("Mail sent!");
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
}
