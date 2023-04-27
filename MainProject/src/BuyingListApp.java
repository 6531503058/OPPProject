import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;



public class BuyingListApp { //1
    
    
    private ArrayList<ListClass> lists = new ArrayList<ListClass>();
    
    private boolean stay = true;
    private String inputChoice ;
   
    public int getSize()
    {
        try{
        return lists.size();
        }
        catch(NullPointerException e)
        {
            return 0;
        }

    }

    

    private int inMoneyUser(){
        boolean rightType = false;
        int re = 0;
        while (!rightType){
        try{
           System.out.println("How much money do you have?");
            Scanner sc = new Scanner(System.in);
            re = sc.nextInt();
            
            if(re<0)
            {
                throw new InputMismatchException();
            }
            rightType = true;
        }
        catch(InputMismatchException IME){
            System.out.println("Positive Number Only!!!");
        }
       }
       return re;
       
    }

   
    private void createList()
    {   String listName;
        int listMoney;
        System.out.println("Please enter the list name");
        Scanner sc = new Scanner(System.in);
        listName = sc.nextLine();
        
        listMoney = inMoneyUser();
        lists.add(new ListClass(listName , listMoney));
        
        

    }

    public void inputMain()
    {   boolean using = true;
        
        while(using)
        {
            System.out.println("\n____________________________________________________________\n   Buying list Application");
        
        
        System.out.println("          Lists");
       
        showLists();

        System.out.println("Choose (1-4)   1.Add new list | 2.Edit List | 3.Delete List | 4.leave");
        System.out.println("Total lists "+getSize()+" | Total items "+getTotalItems()+" | Total price "+getTotalPrice()+" bath | your money "+ getTotalMoney()+" bath | Money left "+(getTotalMoney()-getTotalPrice())+" bath");
        if(getTotalPrice()>getTotalMoney())
        {
            System.out.println("Not enough money!!!");
        }
        System.out.println("Please choose 1-4");
        
        Scanner sc = new Scanner(System.in);
        inputChoice = sc.nextLine();
        
        
        switch(inputChoice)
        {
            case "1": createList(); break;
            
            case "2": findList(sc); break;

            case "3": deleteList(); break;

            case "4": using = leave();break;

            default: System.out.println("Please choose 1-4");
        }


        }
    }

    private boolean leave()
    {   Scanner sc = new Scanner(System.in);
        String choose;
        boolean rightType = false;
        while(!rightType)
        {
        System.out.println("Do you really want to leave? (y/n)");
        choose = sc.nextLine();
        if(choose.equalsIgnoreCase("y")||choose.equalsIgnoreCase("yes"))
        {
            rightType = true;
            this.stay = false;
            System.out.println("Good bye~");
            return false;

        }
        else if(choose.equalsIgnoreCase("n")||choose.equalsIgnoreCase("no"))
        {
            rightType = true;
            return true;  
        }
        }
        return true;
    }

    private void deleteList()
    {
        boolean found = false,exit = false;
        String choose;
        Scanner sc = new Scanner(System.in);
        
        if(getSize()>0){
         while(!exit)
           {
            try{
                System.out.println("\n____________________________________________________");
               System.out.println("Which list you want to delete? (type list's number / type N to cancle)");
               showLists();
               choose = sc.nextLine();
               
               if(choose.equalsIgnoreCase("n"))
               {
                   found = true;
                   exit = true;
               }

               else if(!found&&isNumeric(choose))
               {
                   lists.remove(Integer.parseInt(choose)-1);
                   found = true;
                   exit = true;
               }
               
               else if(!found){
                   System.out.println("____________________________________________________________\n List Not found");
                   boolean out = false;
                   while (!out)
                   {
                   System.out.println("Cancle list deleting? (Y/N)");
                   choose = sc.nextLine();
                   

                   if(choose.equalsIgnoreCase("y")||choose.equalsIgnoreCase("yes"))
                   {
                       exit = true;
                       out = true;
                   }
                   else if (choose.equalsIgnoreCase("n")||choose.equalsIgnoreCase("no"))
                   {out = true;}
                }
               }
            }
            catch(IndexOutOfBoundsException e )
            {
                System.out.println("____________________________________________________________\n List Not found");
                   System.out.println("Cancle list deleting? (Y/N)");
                   choose = sc.nextLine();
                   

                   if(choose.equalsIgnoreCase("y")||choose.equalsIgnoreCase("yes"))
                   {
                       exit = true;
                   }
            }
        }
       }
       else{System.out.println("____________________________________________________________\n There is no list!!!");}
        
    }

    private void findList(Scanner sc)
    {
        boolean found = false,exit = false;
        String choose;
        
            if(getSize() >0)
            {
            
            while(!exit)
            {
                try{
                System.out.println("____________________________________________________________\n Which list you want to edit? (type list's name or list's number / Tyep N to cancle)");

                    showLists();

                choose = sc.nextLine();
                for (ListClass aList : lists) {
                    if((aList.getListName()).equalsIgnoreCase(choose))
                    {
                        aList.editList();
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
                    lists.get(Integer.parseInt(choose)-1).editList();
                    found = true;
                    exit = true;
                }
                else if(!found){
                    System.out.println("____________________________________________________________\n List Not found");
                    System.out.println("Cancle list editing? (Y/N)");
                    choose = sc.nextLine();
                   
                    if(choose.equalsIgnoreCase("y")||choose.equalsIgnoreCase("yes"))
                    {
                        exit = true;
                    }
                }
                }
                catch (IndexOutOfBoundsException e)
                {
                    System.out.println("____________________________________________________________\n Wrong index!!!");
                }

            }
        }
        else
        {
            System.out.println("____________________________________________________________\n There is no list!!!");
        }
    }

    public static boolean isNumeric(String str) { 
        try {  
          Double.parseDouble(str);  
          return true;
        } catch(NumberFormatException e){  
          return false;  
        }  
    }

  
 
    private void showLists()
    {   if(getSize() == 0)
        { System.out.println("\nN/A\n");}
        else{
        int i =1;
        for (ListClass alist : this.lists) {

            System.out.println();
            System.out.println(i+".) "+alist.getListName());
            alist.printItemsInThelist();
            System.out.println();

            i++;
        }
         }
        

        
    }

    private int getTotalPrice()
    {   try{
        int totalPrice = 0;
        for (ListClass aList : this.lists) {
            totalPrice = totalPrice+aList.getPriceTotal();
        }
        return totalPrice;
        }
        catch(NullPointerException e)
        {
            return 0;
        }
    }
    private int getTotalItems()
    {   try{
        int listSize =0;
        for (ListClass aList : lists) {
            listSize = listSize+aList.getitemAmount();
        }
        return listSize;
        }
        catch(NullPointerException e)
        {
            return 0;
        }
    }

    private int getTotalMoney()
    {   try{
        int listsMoney =0;
        for (ListClass aList : lists) {
            listsMoney = listsMoney+aList.getUserMoney();
        }
        return listsMoney;
        }
        catch(NullPointerException e)
        {
            return 0;
        }
    }
    
    

}
