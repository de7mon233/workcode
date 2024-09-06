import java.util.InputMismatchException;
import java.util.Scanner;

public class Item {
    String itemNum;
    String itemName;
    String manufacturer;
    String productDate;
    String type;
    double purchase;
    double price;
    int amount;
    int buyAmount=0;
    public void change(){
        Scanner input = new Scanner(System.in);
        System.out.println("请选择你要修改的商品信息");
        System.out.print("1.商品编号 2.商品名称 3.生产厂家 4.生产日期 5.型号 6.进货价 7.零售价 8.数量 9.返回");
        String choose=input.next();
        switch (choose){
            case "1":{
                System.out.print("请输入商品编号：");
                itemNum=input.next();
                break;
            }
            case "2":{
                System.out.print("请输入商品名称：");
                itemName=input.next();
                break;
            }
            case "3":{
                System.out.print("请输入生产厂家：");
                manufacturer=input.next();
                break;
            }
            case "4":{
                System.out.print("请输入生产日期：");
                productDate=input.next();
                break;
            }
            case "5":{
                System.out.print("请输入型号：");
                type=input.next();
                break;
            }
            case "6":{
                System.out.print("请输入进货价：");
                try {
                    purchase=input.nextDouble();
                }catch (InputMismatchException e){
                    System.err.println("请输入整数或小数");
                }
                break;
            }
            case "7":{
                System.out.print("请输入零售价：");
                try {
                    price=input.nextDouble();
                }catch (InputMismatchException e){
                    System.err.println("请输入整数或小数");
                }
                break;
            }
            case "8":{
                System.out.print("请输入数量：");
                try {
                    amount=input.nextInt();
                }catch (InputMismatchException e){
                    System.err.println("请输入整数");
                }
                break;
            }
            case "9":{
                break;
            }
            default:System.out.println("输入错误请重试");
        }
    }
}
