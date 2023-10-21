package vn.aptech.powerofspeed.service;

import java.util.Map;

import org.springframework.boot.context.properties.bind.DefaultValue;

public interface EmailService {
    void sendSimpleMail(String name, String to);

    void sendHTMLemail(String name, String to);

    String randomString();

    String templateResolve(String templateName, Map<String, Object> map);

    void sendTemplateMessage(String to, @DefaultValue(value = "") String from, String subject, String text);
}