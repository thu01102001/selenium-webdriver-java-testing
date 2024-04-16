package JavaSDET;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic04_AndOr {
    @Test
    public void verify() {
        String contactInformation = "thu le nguyen\n" +
                "nguyenthilethu2001vn@gmail.com";
        Assert.assertTrue(contactInformation.contains("thu le nguyen") && contactInformation.contains("nguyenthilethu2001vn@gmail.com"));
    }
}
