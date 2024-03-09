package JavaSDET;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Topic_01_DataType {
    //2 nhom kieu du lieu
    //cach khai bao:
    // access modifier: pham vi truy cap (prive, public, procted, default)
    //C1: access modifier - kieu du lieu -  ten bien - gia tri cua bien(ngoai ham/trong ham)
    //C2.1: access modifier - kieu du lieu - ten bien
    //2.2: ten bien - gia tri ban sau (ham)

    //nhom1 - kieu du lieu nguyen thuy(8 loai)
        //char: ky tu: khi gan gia tri (khoi tao) thi nam trong dau nhay don (')
        public char cName = 'b';
        private char cAddress;
        public void clickToElement() {
            cAddress = 'c';
            char cCity = 'd';
        }
        //byte/ short/ int/ long: so nguyen: khi gan tri khong nam trong dau gif het
        byte bNumber = 120;
        //float/ double: so thuc
        float dNumber = 17.8f;
        double dNumbers = 17.1234;
        //boolean: logic
        boolean gender = false;
    //nhom2 - kieu du lieu tham chieu
    //String: chuoi: khi gan gia tri thi nam trong dau nhay doi (")
    String fullName = "Automation FC";
    //class
    FirefoxDriver fDriver = new FirefoxDriver();
    Topic_01_DataType topic01 = new Topic_01_DataType();

    //interface
    WebDriver driver;
    JavascriptExecutor jsExcutor;
//
    //arrray
    String[] studenName = {"Hien", "an"};
    Integer[] studenPhone = {123,123,123};

    //list/set, map,
    List<String> studentAddress = new ArrayList<String>();
    //map
    Map<String, Integer> zip = new HashMap<String, Integer>();
    // object
    //convention: quy uoc khi lap trinh
    //ten bien / ten ham: viet duoi dang camel case
    //chu cai dau tien luon viet thuong, chu cai dau tien cua tu tiep theo viet hoa
}
