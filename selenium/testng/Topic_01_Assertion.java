package testng;

import org.testng.Assert;

public class Topic_01_Assertion {
    public static void main(String[] args) {
        //3 hàm chính để kiểm tra tính đúng dắn của dữ liệu
        boolean gender = 3<5;
        //kiểm tra dữ liệu nó phải ĐÚNG
        Assert.assertTrue(gender);
        //kiểm tra dữ liệu nó phải SAI
        Assert.assertFalse(gender);
        //kiểm tra dữ liệu bằng với mong đợi (actual - expected)
        //kiểu dl phải giống nhau
        //giá trị của dữ liệu bằng nhau
        Assert.assertEquals(5,4);
        Assert.assertEquals("Name","NAME");
    }
}
