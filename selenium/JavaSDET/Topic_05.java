package JavaSDET;

import org.testng.annotations.Test;

import java.util.Random;

public class Topic_05 {
    String prefixEmail = "thu";
    String postfixEmail = "@gmail.com"; // Web mail server
    String fullName = prefixEmail + postfixEmail;
    @Test
    public void testEmail() {
        Random rand = new Random();
        //Local
        String fullName = prefixEmail + rand.nextInt(999999) + postfixEmail;
        System.out.printf(fullName);
    }
}
