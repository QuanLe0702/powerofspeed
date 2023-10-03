package vn.aptech.powerofspeed.service;

public interface EmailService {
    void sendSimpleMail(String name, String to);

    void sendHTMLemail(String name, String to);
}
