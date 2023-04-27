import java.net.CacheRequest;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ListClass { //2
    
    private String listName;
    private ArrayList<ItemsClass> items = new ArrayList<ItemsClass>();
    private int userMoney;

    public ListClass (String listName, int userMoney)
    {

        this.listName = listName;
        this.userMoney = userMoney;

    }
    
    public int getitemAmount() {
    return items.size();
    }
    public String getListName() {
        return listName;
    }
    public int getUserMoney() {
        return userMoney;
    }
    public int getPriceTotal() {
        int re = 0;
        if(items.size()>0)
        {
        
        for (ItemsClass aitems : items) {
            re = re + aitems.getPriceTotal();
        }
        }
        else{
            re = 0;
        }

        return re ;
    }
    
    public void printItemsInThelist() //has potentail to fuck up
    {   int i = 1;
        if(getitemAmount() >0)
        {
        for (ItemsClass aItem : items) {
            if(aItem.getItemQuntity()>1)
            {
                System.out.println("  "+i+". "+aItem.getItemName()+" price per pc "+aItem.getItemPrice()+" bath "+aItem.getItemQuntity()+" pcs = "+aItem.getPriceTotal()+" bath");
            }
            else
            {
                System.out.println("  "+i+". "+aItem.getItemName()+" price per pc "+aItem.getItemPrice()+" bath "+aItem.getItemQuntity()+" pc = "+aItem.getPriceTotal()+" bath");
            }
            i++;
        }
        }
        else{System.out.println("N/A");}
        System.out.println("");
        System.out.println("Total item "+getitemAmount()+" | Total price "+getPriceTotal()+" baht | Your money "+userMoney+" baht | Change "+(userMoney-getPriceTotal())+" baht");
    }

    public void editList()
    {
       
        inputEdit();


    }
    private void inputEdit()
    {   boolean using = true;
        String inputChoice;
        while(using)
        {
            System.out.println("\n________________________________________________");
        System.out.println("\nList --"+this.listName+"--\n");
      
        printItemsInThelist();
        
        System.out.println("Choose (1-6)   1.Add new item | 2.Edit item | 3.Delete item | 4.Edit money | 5.Change list's name | 6. to main menu");
        if(getPriceTotal()>getUserMoney())
        {
            System.out.println("Not enough money!!!");
        }
        
        System.out.println("Please choose 1-6");
        
        Scanner sc = new Scanner(System.in);
        inputChoice = sc.nextLine();
       
        
        switch(inputChoice)
        {
            case "1": addItem(); break;
            
            case "2": editItem(); break;

            case "3": deleteItem(); break;

            case "4": editMoney(); break;
            case "5":   System.out.println("Please enter new name for the list.");
                        this.listName = sc.nextLine(); break;
            case "6": System.out.println(" Go to menu ");
            using = false;
            break;

            default: System.out.println("Please choose 1-6");
        }


        }
    }

    private void editMoney()
    {   boolean rightType= false;
        Scanner sc = new Scanner(System.in);
        while(!rightType)
        {   try{
             System.out.println("How much money do you want to add?");
             userMoney = userMoney + Integer.parseInt(sc.nextLine());
             
             rightType = true;
            }
            catch(Exception e)
            {
                System.out.println(" Number only!!!");
            }
        }
    }

    private void deleteItem()
    { boolean found = false,exit = false;
        String choose;
        Scanner sc = new Scanner(System.in);
        if(getitemAmount()>0)
        {
       while(!exit)
           {
                try{
               System.out.println("Which item you want to delete? (type item's name or item's number / type N to cancel)");
               choose = sc.nextLine();
               
               for (ItemsClass aItem : items) {
                   if((aItem.getItemName()).equalsIgnoreCase(choose))
                   {
                       items.remove(items.indexOf(aItem));
                       found = true;
                       exit = true;
                   }

               }
               if(choose.equalsIgnoreCase("n"))
               {
                    found = true;
                    exit = true;
               }
               else if(!found&&isNumeric(choose))
               {
                   items.remove(Integer.parseInt(choose)-1);
                   found = true;
                   exit = true;
               }
               else if(!found){
                   System.out.println("Item Not found");
                   boolean out = false;
                   while(!out){
                   System.out.println("Cancle item deleting? (Y/N)");
                   choose = sc.nextLine();
                

                   if(choose.equalsIgnoreCase("y")||choose.equalsIgnoreCase("yes"))
                   {
                       exit = true;
                       out = true;
                   }
                   else if(choose.equalsIgnoreCase("n")||choose.equalsIgnoreCase("no"))
                   {
                       out =true;
                   }

                 }
               }
            }
            catch (IndexOutOfBoundsException e)
            {
                System.out.println("Wrong number!!!");
            }
           }
        }
        else{System.out.println("There is no item!!!");}
    }

    private void addItem()
    {   
        String itemName;
        String itemPrice = "0";
        String itemAmount = "0";
        boolean rightType = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("What's the item name?");
        itemName = sc.nextLine();
        
        while(!rightType){
            try{
        System.out.println("What's the price for the "+itemName+"?");
            itemPrice = sc.nextLine();
            try{
            if(isNumeric(itemPrice));
            {   
                if(Integer.parseInt(itemPrice)<0)
                {
                    System.out.println("Only positive number!!!");
                }
                else
                {
                rightType = true;
                }
            }
            }
            catch(NumberFormatException e)
            {   
                System.out.println("Only positive number!!!");
            }
            }
            catch(NumberFormatException e)
            {
                System.out.println("Only positive number!!!");
            }
        }

        rightType = false;

        while(!rightType){
            try{
            System.out.println("How many of "+itemName+" you want to buy?");
                itemAmount = sc.nextLine();
                if(isNumeric(itemAmount));
                {
                        if(Integer.parseInt(itemAmount)<0)
                        {
                        System.out.println("Only positive number!!!");
                        }
                        else
                        {
                        rightType = true;
                        }
                }
                }
                catch(NumberFormatException e)
                {
                    System.out.println(" Only positive number!!!");
                }
            }
        
        items.add(new ItemsClass(itemName,Integer.parseInt(itemPrice) , Integer.parseInt(itemAmount)));  
        


    }

    private void editItem()
    {    boolean found = false,exit = false;
         String choose;
         Scanner sc = new Scanner(System.in);
        if(getitemAmount()>0)
        {
        while(!exit)
            {
                try{
                System.out.println("Which item you want to edit? (type item's name or item's number / type N to cancel)");
                choose = sc.nextLine();
                
                for (ItemsClass aItem : items) {
                    if((aItem.getItemName()).equalsIgnoreCase(choose))
                    {
                        aItem.editItem();
                        found = true;
                        exit = true;
                    }

                }
                if(choose.equalsIgnoreCase("n"))
                {
                    found = true;
                    exit = true;
                }
                else if(!found&&isNumeric(choose))
                {
                    items.get(Integer.parseInt(choose)-1).editItem();
                    found = true;
                    exit = true;
                }
                else if(!found){
                    System.out.println("Item Not found");
                    boolean out = false;
                    while(!out)
                    {
                    System.out.println("Cancle item editing? (Y/N)");
                    choose = sc.nextLine();
                    

                    if(choose.equalsIgnoreCase("y")||choose.equalsIgnoreCase("yes"))
                    {
                        exit = true;
                        out = true;
                    }
                    else if (choose.equalsIgnoreCase("n")||choose.equalsIgnoreCase("no"))
                    {
                        out = true;
                    }
                    }
                }
                }
                catch(IndexOutOfBoundsException e)
                {
                    System.out.println(" Wrong number!!!");
                }
            }
        }
        else{System.out.println(" There is no item!!!");}
    }

    private static boolean isNumeric(String str) { 
        try {  
          Double.parseDouble(str);  
          return true;
        } catch(NumberFormatException e){  
          return false;  
        }  
    }
    

   
    
    
}
