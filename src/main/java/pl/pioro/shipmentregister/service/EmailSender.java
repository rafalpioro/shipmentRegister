package pl.pioro.shipmentregister.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import pl.pioro.shipmentregister.config.EmailConfig;
import pl.pioro.shipmentregister.entity.User;

@Service
public class EmailSender {

    @Autowired
    private EmailConfig emailConfig;

    public void sendEmail(User user) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(emailConfig.getHost());
        mailSender.setPort(emailConfig.getPort());
        mailSender.setUsername(emailConfig.getUsername());
        mailSender.setPassword(emailConfig.getPassword());

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("shipmentregister@gmail.com");
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Hello "+user.getName()+" ; Shipment register - new account has been added");
        mailMessage.setText("Login: "+ user.getEmail() + "\n Password: " + user.getPassword());

        mailSender.send(mailMessage);
    }
}
