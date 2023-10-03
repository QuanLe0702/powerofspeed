package vn.aptech.powerofspeed.service.impl;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.util.Map;
import vn.aptech.powerofspeed.service.EmailService;
import vn.aptech.powerofspeed.util.EmailUtils;


@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    @Value("${spring.mail.verify.host}")
    private String host;
    @Value("${spring.mail.username}")
    private String fromEmail;
    private final JavaMailSender emailSender;
    private final TemplateEngine templateEngine;

    @Override
    @Async
    public void sendSimpleMail(String name, String to) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject("New User Account Verification");
            message.setFrom(fromEmail);
            message.setTo(to);
            message.setText(EmailUtils.getEmailMessage(name, host));
            emailSender.send(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

   @Override
    public void sendHTMLemail(String name, String to) {
        try {
            Context context = new Context();
            /*context.setVariable("name", name);
            context.setVariable("url", getVerificationUrl(host, token));*/
            context.setVariables(Map.of("name", name));
            String text = templateEngine.process("email.html", context);
            MimeMessage message = getMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setPriority(1);
            helper.setSubject("NEW_USER_ACCOUNT_VERIFICATION");
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setText(text, true);
            //Add attachments (Optional)
            /*FileSystemResource fort = new FileSystemResource(new File(System.getProperty("user.home") + "/Downloads/images/fort.jpg"));
            FileSystemResource dog = new FileSystemResource(new File(System.getProperty("user.home") + "/Downloads/images/dog.jpg"));
            FileSystemResource homework = new FileSystemResource(new File(System.getProperty("user.home") + "/Downloads/images/homework.docx"));
            helper.addAttachment(fort.getFilename(), fort);
            helper.addAttachment(dog.getFilename(), dog);
            helper.addAttachment(homework.getFilename(), homework);*/
            emailSender.send(message);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            throw new RuntimeException(exception.getMessage());
        }
    }

    private MimeMessage getMimeMessage() {
        return emailSender.createMimeMessage();
    }

}
