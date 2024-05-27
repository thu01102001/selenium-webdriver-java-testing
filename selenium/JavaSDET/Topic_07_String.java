package JavaSDET;

public class Topic_07_String {
    public static void main(String[] args) {
        String link = "http://the-internet.herokuapp.com/basic_auth";
        String username = "";
        String password = "";

        String linkArray[] = link.split("//");
        // https://
        // the-internet.herokuapp.com/basic_auth
        System.out.println(linkArray[0]);
        System.out.println(linkArray[1]);
        link = linkArray[0] + "//" + username + ":" + password + "@" + linkArray[1];
        System.out.println(link);
    }
}
