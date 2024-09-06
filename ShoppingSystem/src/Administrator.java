import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Administrator extends User{
    String rePass="123456";
    public Administrator(){
        setName("admin");
        setPassword("ynuadmin");
    }
    public void resetPass(User user){
        user.setPassword(rePass);
        System.out.println("该用户密码已重置为"+rePass);
    }
    public void showClient(Map<String, Client> clientMap){
        Set<String> keys=clientMap.keySet();
        for (String key:keys){
            System.out.println("名称:"+key+" ID:"+clientMap.get(key).clientID+" Lv:"+clientMap.get(key).clientLv
                    +" 注册时间:"+clientMap.get(key).regisTime+" 总消费:"+clientMap.get(key).totalCost
                    +" 电话号码:"+clientMap.get(key).getPhoneNum()+" 邮箱:"+clientMap.get(key).getMailAddress());
        }
    }
    public void delClient(Map<String, Client> clientMap){
        Scanner input = new Scanner(System.in);
        System.out.print("请输入你要删除的用户名");
        String delName=input.next();
        if(clientMap.containsKey(delName)){
            System.out.print("你确定要删除该用户吗？yes/no");
            String ifDel=input.next();
            if(ifDel.equals("yes")){
                clientMap.remove(delName);
                System.out.println("已删除该用户");
            }
            else {
                System.out.println("取消删除用户");
            }
        }
        else {
            System.out.println("该用户不存在");
        }
    }
    public void seekClient(Map<String, Client> clientMap){
        Scanner input = new Scanner(System.in);
        System.out.print("请输入你要查找的用户名"+"(查询所有客户输入all)");
        String seekName=input.next();
        if(seekName.equals("all")){
            showClient(clientMap);
        }
        else if(clientMap.containsKey(seekName)){
            System.out.println(seekName+" ID:"+clientMap.get(seekName).clientID+" Lv:"+clientMap.get(seekName).clientLv
                    +" 注册时间:"+clientMap.get(seekName).regisTime+" 总消费:"+clientMap.get(seekName).totalCost
                    +" 电话号码:"+clientMap.get(seekName).getPhoneNum()+" 邮箱:"+clientMap.get(seekName).getMailAddress());
        }
        else {
            System.out.println("无效输入请重试");
        }
    }
    public void  showItem(Map<String,Item> itemMap){
        Set<String> keys=itemMap.keySet();
        for (String key:keys){
            System.out.println("编号:"+key+" 名称:"+itemMap.get(key).itemName+" 生产厂家:"+itemMap.get(key).manufacturer
                    +" 生产日期:"+itemMap.get(key).productDate+" 型号:"+itemMap.get(key).type
                    +" 进货价:"+itemMap.get(key).purchase+" 零售价:"+itemMap.get(key).price+
                    " 数量:"+itemMap.get(key).amount);
        }
    }
    public void addItem(Item item){
        Scanner input = new Scanner(System.in);
        System.out.print("请输入商品编号：");
        item.itemNum=input.next();
        System.out.print("请输入商品名称：");
        item.itemName=input.next();
        System.out.print("请输入生产厂家：");
        item.manufacturer=input.next();
        System.out.print("请输入生产日期：");
        item.productDate=input.next();
        System.out.print("请输入型号：");
        item.type=input.next();
        try {
            System.out.print("请输入进货价：");
            item.purchase=input.nextDouble();
            System.out.print("请输入零售价：");
            item.price=input.nextDouble();
            System.out.print("请输入数量：");
            item.amount=input.nextInt();
            System.out.println("添加成功");
        }catch (InputMismatchException e){
            System.err.println("请输入整数或小数");
        }finally {
            System.out.print("\n");
        }
    }
    public void setItem(Map<String,Item> itemMap){
        Scanner input = new Scanner(System.in);
        System.out.print("请输入你要修改的商品编号");
        String num=input.next();
        if(itemMap.containsKey(num)){
            while (true){
                itemMap.get(num).change();
                System.out.print("要继续修改吗？yes/no");
                if(input.next().equals("no")){
                    System.out.println("商品修改完成");
                    break;
                }
            }
        }
        else {
            System.out.println("没有找到商品请重试");
        }
    }
    public void delItem(Map<String,Item> itemMap){
        Scanner input = new Scanner(System.in);
        System.out.print("请输入你要删除的商品编号");
        String delItem=input.next();
        if(itemMap.containsKey(delItem)){
            System.out.print("你确定要删除该商品吗？yes/no");
            String ifDel=input.next();
            if(ifDel.equals("yes")){
                itemMap.remove(delItem);
                System.out.println("已删除该商品");
            }
            else {
                System.out.println("取消删除商品");
            }
        }
        else {
            System.out.println("该商品超市中不存在");
        }
    }
    public void searchItem(Map<String,Item> itemMap){
        Scanner input = new Scanner(System.in);
        System.out.print("请输入你要查找的商品名称:");
        String itemName=input.next();
        Set<String> keys=itemMap.keySet();
        boolean exist=false;
        for (String key:keys){
            if (itemMap.get(key).itemName.equals(itemName)){
                System.out.println("查找成功");
                System.out.println("编号:"+key+" 名称:"+itemMap.get(key).itemName+" 生产厂家:"+itemMap.get(key).manufacturer
                        +" 生产日期:"+itemMap.get(key).productDate+" 型号:"+itemMap.get(key).type
                        +" 进货价:"+itemMap.get(key).purchase+" 零售价:"+itemMap.get(key).price+
                        " 数量:"+itemMap.get(key).amount);
                exist=true;
                break;
            }
        }
        if (!exist)
            System.out.println("该商品不存在");
    }
}
