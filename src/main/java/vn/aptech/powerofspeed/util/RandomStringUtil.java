package vn.aptech.powerofspeed.util;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

public class RandomStringUtil {

    private static final Pattern NONLATIN = Pattern.compile("[^\\w_-]");
    private static final Pattern SEPARATORS = Pattern.compile("[\\s\\p{Punct}&&[^-]]");

    // function to generate a random string of length n
    public static String getAlphaNumericString(int n, String inputString) {

        // chose a Character random from this String
        String inputStringUcase = inputString.trim().toUpperCase().replaceAll(" ", "").concat("123456789");

        // create StringBuffer size of inputString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to inputString variable length
            int index
                    = (int) (inputStringUcase.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(inputStringUcase
                    .charAt(index));
        }

        return sb.toString();
    }

    public static String makeSlug(String input) {
        String noseparators = SEPARATORS.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(noseparators, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH).replaceAll("-{2,}","-").replaceAll("^-|-$","");
    }
}

