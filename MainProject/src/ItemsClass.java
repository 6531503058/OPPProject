import java.util.InputMismatchException;
import java.util.Scanner;

public class ItemsClass { //3
    
    private String itemName;
    private int itemPrice;
    private int itemQuntity;
    private int priceTotal;

    public ItemsClass(String itemName, int itemPrice, int itemQuntity)
    {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemQuntity = itemQuntity;
        this.priceTotal = itemPrice*itemQuntity;
    }

    public String getItemName() {
        return itemName;
    }
    public int getItemPrice() {
        return itemPrice;
    }
    public int getItemQuntity() {
        return itemQuntity;
    }
    public int getPriceTotal() {
        return itemPrice*itemQuntity;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }
    public void setItemQuntity(int itemQuntity) {
        this.itemQuntity = itemQuntity;
    }

    public void editItem()
    {   
        String itemPriceHold = "0";
        String itemAmountHold = "0";
        boolean rightType = false;

        Scanner sc = new Scanner(System.in);
        System.out.println("What name you want to change item's name to?");
        this.itemName = sc.nextLine();
        
        while(!rightType)
        {
            try{
                System.out.println("What's the price of the "+this.itemName+"?");
                itemPriceHold = sc.nextLine();
                
                
                if(Integer.parseInt(itemPriceHold)<0)
                {
                   System.out.println("Onlay positive number!!!");
                }
                else{rightType = true;}
                
            }
            catch(Exception e)
            {
                System.out.println("Only positive number!!!");
            }
        }

        rightType = false;

        while(!rightType)
        {
            try{
                System.out.println("How many "+this.itemName+" do you want?");
                itemAmountHold = sc.nextLine();
                
                if(Integer.parseInt(itemAmountHold)<0)
                {
                   System.out.println("Only positive number!!!");
                }
                else{
                    rightType = true;
                }
            }
            catch(Exception e)
            {
                System.out.println("only positive number!!!");
            }
        }

        this.itemPrice = Integer.parseInt(itemPriceHold);
        this.itemQuntity = Integer.parseInt(itemAmountHold);

    }
        
    


   

}
