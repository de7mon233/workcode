import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Mgr {
    Administrator administrator=new Administrator();
    Map<String, Client> clientMap = new HashMap<>();
    Map<String,Item> itemMap=new HashMap<>();
    public void passMgr(User user){
        Scanner input = new Scanner(System.in);
        if(user instanceof Administrator){
            System.out.print("请选择：1.修改密码 2.重置客户密码 3.返回");
            String choose=input.next();
            if (choose.equals("1")){
                user.changePass();
            }
            else if (choose.equals("2")){
                System.out.print("请输入需要重置的用户名");
                String reUser=input.next();
                ((Administrator) user).resetPass(clientMap.get(reUser));
            }
            else if(choose.equals("3")){
                System.out.println("已返回");
            }
            else {
                System.out.println("无效输入请重试");
            }
        }
        if (user instanceof Client){
            System.out.print("请选择：1.修改密码 2.重置密码 3.修改手机号码 4.返回");
            String choose=input.next();
            if (choose.equals("1")){
                user.changePass();
            }
            else if (choose.equals("2")){
                ((Client) user).resetPass();
            }
            else if(choose.equals("3")){
                ((Client) user).setPhoneNum();
            }
            else if(choose.equals("4")){
                System.out.println("已返回");
            }
            else {
                System.out.println("无效输入请重试");
            }
        }
    }
    public void clientMgr(Administrator administrator){
        Scanner input = new Scanner(System.in);
        System.out.print("请选择：1.列出所有客户信息 2.删除客户信息 3.查询客户信息 4.返回");
        String choose=input.next();
        if(choose.equals("1")){
            administrator.showClient(clientMap);
        }
        else if (choose.equals("2")){
            administrator.delClient(clientMap);
        }
        else if (choose.equals("3")) {
            administrator.seekClient(clientMap);
        }
        else if(choose.equals("4")){
            System.out.println("已返回");
        }
        else {
            System.out.println("无效输入请重试");
        }
    }
    public void itemMgr(Administrator administrator){
        Scanner input = new Scanner(System.in);
        System.out.print("请选择：1.列出所有商品信息 2.添加商品信息 3.修改商品信息信息 4.删除商品信息 5.查找商品信息 6.返回");
        String choose=input.next();
        if(choose.equals("1")){
            administrator.showItem(itemMap);
        }
        else if (choose.equals("2")){
            Item item=new Item();
            administrator.addItem(item);
            itemMap.put(item.itemNum,item);
        }
        else if (choose.equals("3")){
            administrator.setItem(itemMap);
        }
        else if(choose.equals("4")){
            administrator.delItem(itemMap);
        }
        else if(choose.equals("5")){
            administrator.searchItem(itemMap);
        }
        else if(choose.equals("6")){
            System.out.println("已返回");
        }
        else {
            System.out.println("无效输入请重试");
        }
    }
    public void buyMgr(Client client){
        Scanner input = new Scanner(System.in);
        System.out.print("请选择：1.添加商品到购物车 2.将商品移除购物车 3.修改商品购买数量 4.付款 5.查看购物历史 6.返回");
        String choose=input.next();
        if (choose.equals("1")){
            client.putItem(itemMap);
        }
        else if (choose.equals("2")){
            client.removeItem();
        }
        else if (choose.equals("3")){
            client.changeAmount();
        }
        else if (choose.equals("4")){
            client.pay(itemMap);
        }
        else if (choose.equals("5")){
            client.buyHistory();
        }
        else if (choose.equals("6")){
            System.out.println("已返回");
        }
        else {
            System.out.println("无效输入请重试");
        }
    }
}
