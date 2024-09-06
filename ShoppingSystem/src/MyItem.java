import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyItem extends Item{
    String buyTime;
    public MyItem(String itemNum,String itemName,double price,int amount){
        this.itemNum=itemNum;
        this.itemName=itemName;
        this.price=price;
        this.amount=amount;
        Date date=new Date();
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd-hh-mm");
        buyTime=df.format(date);
    }
    public void print(){
        System.out.println("编号:"+itemNum+" 名称:"+itemName+" 价格:"+price+" 数量:"+amount+" 购买时间:"+buyTime);
    }
}
