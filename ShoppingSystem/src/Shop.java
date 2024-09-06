import java.util.*;
public class Shop {
    Mgr mgr=new Mgr();

    public void homePage() {
        Scanner input = new Scanner(System.in);
        System.out.println("欢迎来到购物管理系统");
        System.out.println("****************");
        while (true) {
            System.out.println("      登录       ");
            System.out.println("用户名：");
            System.out.println("密码：");
            System.out.println("输入1注册新账户");
            System.out.println("输入2重置密码");
            System.out.println("输入3退出登录界面");
            System.out.print("请输入用户名：");
            String userName = input.next();
            if (userName.equals("1")) {
                Client client = new Client();
                client.register();
                mgr.clientMap.put(client.getName(), client);
                continue;
            }
            if(userName.equals("2")){
                System.out.print("你的用户名：");
                userName=input.next();
                System.out.print("你的邮箱地址：");
                String userMail=input.next();
                System.out.println("已向"+userName+"的邮箱"+userMail+"发送随机密码");
                continue;
            }
            if (userName.equals("3")) {
                break;
            }
            if (userName.equals(mgr.administrator.getName())) {
                System.out.print("请输入密码：");
                String userPass=input.next();
                boolean ifInput=mgr.administrator.logIn(userPass);
                function(ifInput,mgr.administrator);
            } else if (mgr.clientMap.containsKey(userName)) {
                System.out.print("请输入密码：");
                String userPass=input.next();
                boolean ifInput=mgr.clientMap.get(userName).logIn(userPass);
                function(ifInput,mgr.clientMap.get(userName));
            }else {
                System.out.println("该用户不存在请重新输入");
            }
        }
    }
    public void function(boolean ifInput,User user){
        while (ifInput){
            Scanner input = new Scanner(System.in);
            if(user instanceof Administrator){
                System.out.print("请选择：1.密码管理 2.客户管理 3.商品管理 4.退出登录");
                String choose=input.next();
                if(choose.equals("1")){
                    mgr.passMgr(user);
                }
                else if (choose.equals("2")){
                    mgr.clientMgr(mgr.administrator);
                }
                else if (choose.equals("3")){
                    mgr.itemMgr(mgr.administrator);
                }
                else if(choose.equals("4")){
                    ifInput=false;
                }
                else {
                    System.out.println("无效输入请重试");
                }
            }
            else if(user instanceof Client){
                System.out.print("请选择：1.密码管理 2.购物管理 3.退出登录");
                String choose=input.next();
                if(choose.equals("1")){
                    mgr.passMgr(user);
                }
                else if(choose.equals("2")){
                    mgr.buyMgr(mgr.clientMap.get(user.getName()));
                }
                else if(choose.equals("3")){
                    ifInput=false;
                }
                else {
                    System.out.println("无效输入请重试");
                }
            }
        }
    }
}