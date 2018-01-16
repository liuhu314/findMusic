/**
 * Created by liuhu on 18/1/11.
 */
public class Test {
    public static void main(String[] args) {
        String s = null;
        try {
            System.out.println("try");
       //     s.equals(""); // 让catch 捕获一个 NullPointerException对象；
 //           return;
        } catch (Exception e) {
            System.out.println("catch");
            e.printStackTrace();
//            return;
        } finally {
            System.out.println("finally");
 //           return;
        }
       System.out.println("other");
       return;
    }
}
