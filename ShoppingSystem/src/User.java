import java.util.Scanner;

public abstract class User {
    private String name;
    private String password;
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public boolean logIn(String password) {
        if (password.equals(getPassword())){
            System.out.println("登录成功！");
            return true;
        }
        else {
            System.out.println("密码错误");
            System.out.println("请重新登陆");
            return false;
        }
    }
    public void changePass(){
        Scanner input=new Scanner(System.in);
        System.out.print("请输入新密码：");
        String password=input.next();
        setPassword(password);
        System.out.println("修改成功");
    }
    public static boolean isPasswordComplex(String password) {
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasDigit = false;
        boolean hasPunctuation = false;

        // 检查每个字符
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (isPunctuation(c)) {
                hasPunctuation = true;
            }
        }

        // 检查是否满足所有条件
        return hasUpperCase && hasLowerCase && hasDigit && hasPunctuation;
    }

    // 判断字符是否为标点符号
    public static boolean isPunctuation(char c) {
        String punctuations = "!?,.';:！？，。‘’、";
        return punctuations.indexOf(c) != -1;
    }
}
