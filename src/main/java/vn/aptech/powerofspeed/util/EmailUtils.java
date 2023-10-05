package vn.aptech.powerofspeed.util;

public class EmailUtils {
    
    public static String getEmailMessage(String name, String host){
        return "Hello" + name + "\n\n Your account has been created. Bye!\n\n" + 
                      "\n\nThank you.";
    }

    private static String getVerifycationUrl(String host, String token) {
        return host + "/api/users?token=" + token;
    }
}
