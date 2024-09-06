import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Client extends User{
    String clientID;
    String clientLv;
    String regisTime;
    double totalCost;
    private String phoneNum;
    private String mailAddress;
    int count=0;
    Map<String,Item> shopCart=new HashMap<>();
    List<MyItem> myItems=new ArrayList<>();
    public Client(){
        Random random=new Random();
        clientID=String.valueOf(random.nextInt(1000));
        clientLv="铜牌用户";
        Date date=new Date();
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        regisTime= df.format(date);
    }
    public String getMailAddress(){
        return mailAddress;
    }
    public void setMailAddress(String mailAddress){
        this.mailAddress=mailAddress;
    }
    public String getPhoneNum(){return phoneNum;}
    public void setPhoneNum(){
        Scanner input=new Scanner(System.in);
        System.out.print("请输入手机号码：");
        this.phoneNum=input.next();
    }
    public void changePass(){
        Scanner input=new Scanner(System.in);
        System.out.println("请输入密码:"+"（密码长度大于8个字符，必须是大小写字母、数字和标点符号的组合）");
        String clientPassword=input.next();
        while (clientPassword.length() <= 8 || !isPasswordComplex(clientPassword)){
            System.out.println("密码格式不正确请重新输入");
            System.out.println("请输入密码:"+"（用户名长度不少于5个字符）");
            clientPassword=input.next();
        }
        setPassword(clientPassword);
        System.out.println("修改成功");
    }
    public boolean logIn(String password) {
        if (password.equals(getPassword())){
            System.out.println("登录成功！");
            return true;
        }
        else if(count==5){
            System.out.println("账户锁定无法登录");
            return false;
        }
        else {
            System.out.println("密码错误");
            System.out.println("请重新登陆！");
            count++;
            return false;
        }
    }
    public void register(){
        Scanner input=new Scanner(System.in);
        System.out.println("请输入邮箱地址:");
        String mailAddress=input.next();
        setMailAddress(mailAddress);
        System.out.println("请输入用户名:"+"（用户名长度不少于5个字符）");
        String clientName=input.next();
        while (clientName.length()<5){
            System.out.println("用户名长度小于5个字符请重新输入");
            System.out.println("请输入用户名:"+"（用户名长度不少于5个字符）");
            clientName=input.next();
        }
        setName(clientName);
        System.out.println("请输入密码:"+"（密码长度大于8个字符，必须是大小写字母、数字和标点符号的组合）");
        String clientPassword=input.next();
        while (clientPassword.length() <= 8 || !isPasswordComplex(clientPassword)){
            System.out.println("密码格式不正确请重新输入");
            System.out.println("请输入密码:"+"（密码长度大于8个字符，必须是大小写字母、数字和标点符号的组合）");
            clientPassword=input.next();
        }
        setPassword(clientPassword);
        System.out.println("注册成功！");

    }
    public void resetPass(){
        Scanner input = new Scanner(System.in);
        System.out.print("你的用户名：");
        String userName=input.next();
        System.out.print("你的邮箱地址：");
        String userMail=input.next();
        System.out.println("已向"+userName+"的邮箱"+userMail+"发送随机密码");
        System.out.println(getName()+"密码已重置");
    }
    public void putItem(Map<String,Item> itemMap){
        Scanner input=new Scanner(System.in);
        System.out.println("请输入要选购的商品编号:");
        String buyNum=input.next();
        if(itemMap.containsKey(buyNum)){
            shopCart.put(buyNum,itemMap.get(buyNum));
            shopCart.get(buyNum).buyAmount++;
            System.out.println("添加购物车成功");
            if (shopCart.get(buyNum).buyAmount>itemMap.get(buyNum).amount){
                System.out.println("购买数量超过最大销售值已移除商品");
                shopCart.get(buyNum).buyAmount=0;
                shopCart.remove(buyNum);
            }
        }
        else {
            System.out.println("该商品超市中不存在");
        }
    }
    public void removeItem(){
        Scanner input=new Scanner(System.in);
        System.out.println("请输入要移除的商品编号:");
        String removeNum=input.next();
        if(shopCart.containsKey(removeNum)){
            System.out.print("你确定要删除该商品吗？yes/no");
            String ifDel=input.next();
            if(ifDel.equals("yes")){
                shopCart.get(removeNum).buyAmount=0;
                shopCart.remove(removeNum);
                System.out.println("移除商品成功成功");
            }
            else {
                System.out.println("取消移除商品");
            }
        }
        else {
            System.out.println("该商品购物车中不存在");
        }
    }
    public void changeAmount(){
        Scanner input=new Scanner(System.in);
        System.out.print("请输入要修改的商品编号:");
        String changeNum=input.next();
        if(shopCart.containsKey(changeNum)){
            System.out.print("请输入修改的数量:");
            try{
                shopCart.get(changeNum).buyAmount=input.nextInt();
                System.out.println("修改成功");
            }catch (InputMismatchException e){
                System.err.println("请输入整数");
            }
            if(shopCart.get(changeNum).buyAmount<=0||shopCart.get(changeNum).buyAmount>shopCart.get(changeNum).amount){
                shopCart.get(changeNum).buyAmount=0;
                shopCart.remove(changeNum);
                System.out.println("购买数量超过最大销售值已移除商品");
            }
        }
        else {
            System.out.println("该商品购物车中不存在");
        }
    }
    public void pay(Map<String,Item> itemMap){
        Scanner input=new Scanner(System.in);
        double sum=0;
        Set<String> keys=shopCart.keySet();
        for (String key:keys){
            sum+=shopCart.get(key).price*shopCart.get(key).buyAmount;
        }
        System.out.println("总金额为："+sum);
        System.out.print("请选择支付方式：1.微信 2.支付宝 3.银行卡 4.退出");
        String choose=input.next();
        if (choose.equals("1")||choose.equals("2")||choose.equals("3")){
            totalCost+=sum;
            for (String key:keys){
                MyItem myItem=new MyItem(shopCart.get(key).itemNum,shopCart.get(key).itemName,
                        shopCart.get(key).price,shopCart.get(key).buyAmount);
                myItems.add(myItem);
                if(shopCart.get(key)!=null){
                    itemMap.get(key).amount-=shopCart.get(key).buyAmount;
                    shopCart.get(key).buyAmount=0;
                }
            }
            shopCart.clear();
        }
        if(choose.equals("1")){
            System.out.println("已经调用微信");
        }
        else if(choose.equals("2")){
            System.out.println("已经调用支付宝");
        }
        else if(choose.equals("3")){
            System.out.println("已经调用银行卡");
        }
        else {
            System.out.println("取消支付");
        }
    }
    public void buyHistory(){
        Iterator<MyItem> it=myItems.iterator();
        while (it.hasNext()){
            MyItem myItem=it.next();
            myItem.print();
        }
    }
}
