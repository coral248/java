/*
 *    Source program for Assignment #6 for CS 1A
 *    Written by Shan Tao, 07/31/2013
 */

import java.util.*;
import java.text.NumberFormat;

public class Foothill
{
   public static void main(String[] args)
   {
      while(true)
      {
         System.out.print("\nSize of pizza ('s', 'm', 'l') or 'q' to quit: ");
         char input = getSizeFromUser();
         int size;
         
         if (input == 'q' || input == 'Q')
            break;
         else if (input == 's' || input == 'S')
            size = 0;
         else if (input == 'm' || input == 'M')
            size = 1;
         else if (input == 'l' || input == 'L')
            size = 2;
         else
            continue;
         
         PizzaOrder pizza = new PizzaOrder(size);
                  
         while(true)
         {
            System.out.println("\nCurrent Pizza: " + pizza.stringizeSize() + 
                  pizza.getToppings());
            displayMainMenu();
            String selection = getToppingFromUser();
            int topping;
            
            try
            {
               topping = Integer.parseInt(selection);
            }
            catch (NumberFormatException e)
            {
               System.out.println("Please enter a number.");
               continue;
            }
            
            if (topping == 0)
               break;
            
            if (!pizza.addTopping(topping))
               System.out.println("Please select a number between 0 and 7.");
         }
         
         pizza.displayPizza();
         pizza.resetTopping();
      }
   }
   
   static void displayMainMenu()
   {
      System.out.print("Select an item by number (0 when done):\n" + 
            "   1. Onions\n" + "   2. Bell Pepper\n" + "   3. Olives\n" + 
            "   4. Mushroom\n" + "   5. Pineapple\n" + "   6. Pepperoni\n" + 
            "   7. Sausage\n" + "Selection? ");
   }
   
   static char getSizeFromUser()
   {
      Scanner input = new Scanner(System.in);
      String str_input;
      str_input = input.nextLine();
      return str_input.charAt(0);
   }
   
   static String getToppingFromUser()
   {
      Scanner input = new Scanner(System.in);
      String selection = input.nextLine();
      return selection;
   }
}

class PizzaOrder
{
   public static String toppingsOffered[] = {"onions", "bell peppers", "olives",
      "mushroom","pineapple","pepperoni", "sausage"};
   public static double toppingsBaseCost = 2.;
   public static double basePrice = 6.;
   private int size;
   private int numToppings = 0;   
   private String toppings[] = new String[7];
   
   public PizzaOrder()
   {
      size = 0;
   }
   
   public PizzaOrder(int size)
   {
      if (! setSize(size))
         size = 0;
   }
   
   private boolean setSize(int size)
   {
      if (size != 0 && size != 1 && size != 2)
         return false;
      
      this.size = size;
      return true;
   }
   
   public int getSize()
   {
      return size;
   }
   
   public boolean addTopping(String topping)
   {
      for (int i = 0; i < toppingsOffered.length; i++ )
      {
         if (toppingsOffered[i].compareToIgnoreCase(topping) == 0)
         {
            for (int k = 0; k < numToppings; k++)
            {
               if (toppings[k].compareToIgnoreCase(topping) == 0)
                  return true;
            }
      
            toppings[numToppings] = toppingsOffered[i];
            numToppings++;
            return true;
         }
      }
      
      return false;
   }
   
   public boolean addTopping(int n)
   {
      if (n > 0 && n <= toppingsOffered.length)
      {
         String topping = toppingsOffered[n-1];
         return addTopping(topping);
      }
      
      return false;
   }
   
   private double getPrice()
   {
      double price;
      
      if (numToppings == 0)
         price = basePrice;
      else
         price = basePrice + toppingsBaseCost;
      
      for (int k = 0; k < numToppings; k++)
      {
         if (toppings[k].equals("pepperoni") || toppings[k].equals("sausage"))
            price += 3;
         else
            price += 1.5;
      }
      
      if (size == 1 )
         price *= 1.2;
      
      if (size == 2)
         price *= 1.3;
      
      return price;
   }
   
   public String stringizeSize()
   {
      String strSize = null;
      
      if (size == 0)
         strSize = "small";
      
      if (size == 1)
         strSize = "medium";
      
      if (size == 2)
         strSize = "large";
      
      return strSize;
   }
   
   public String getToppings()
   {
      String topping = "";
      
      for (int k = 0; k < numToppings; k++)
      {
         topping += " + " + toppings[k];
      }
      
      return topping;
   }
   
   public void displayPizza()
   {
      NumberFormat tidy = NumberFormat.getInstance(Locale.US);
      tidy.setMaximumFractionDigits(2);
      tidy.setMinimumFractionDigits(2);
      
      System.out.println(stringizeSize() + getToppings());
      System.out.println("Total Price: $" + tidy.format(getPrice()) + 
            "\nThank you!" + "\n");
   }
   
   public void resetTopping()
   {
      numToppings = 0;
   }
   
}

/* ----- paste of run from console window -------


Size of pizza ('s', 'm', 'l') or 'q' to quit: s

Current Pizza: small
Select an item by number (0 when done):
   1. Onions
   2. Bell Pepper
   3. Olives
   4. Mushroom
   5. Pineapple
   6. Pepperoni
   7. Sausage
Selection? e
Please enter a number.

Current Pizza: small
Select an item by number (0 when done):
   1. Onions
   2. Bell Pepper
   3. Olives
   4. Mushroom
   5. Pineapple
   6. Pepperoni
   7. Sausage
Selection? 10
Please select a number between 0 and 7.

Current Pizza: small
Select an item by number (0 when done):
   1. Onions
   2. Bell Pepper
   3. Olives
   4. Mushroom
   5. Pineapple
   6. Pepperoni
   7. Sausage
Selection? 1

Current Pizza: small + onions
Select an item by number (0 when done):
   1. Onions
   2. Bell Pepper
   3. Olives
   4. Mushroom
   5. Pineapple
   6. Pepperoni
   7. Sausage
Selection? 2

Current Pizza: small + onions + bell peppers
Select an item by number (0 when done):
   1. Onions
   2. Bell Pepper
   3. Olives
   4. Mushroom
   5. Pineapple
   6. Pepperoni
   7. Sausage
Selection? 4

Current Pizza: small + onions + bell peppers + mushroom
Select an item by number (0 when done):
   1. Onions
   2. Bell Pepper
   3. Olives
   4. Mushroom
   5. Pineapple
   6. Pepperoni
   7. Sausage
Selection? 7

Current Pizza: small + onions + bell peppers + mushroom + sausage
Select an item by number (0 when done):
   1. Onions
   2. Bell Pepper
   3. Olives
   4. Mushroom
   5. Pineapple
   6. Pepperoni
   7. Sausage
Selection? 0
small + onions + bell peppers + mushroom + sausage
Total Price: $15.5
Thank you!


Size of pizza ('s', 'm', 'l') or 'q' to quit: e

Size of pizza ('s', 'm', 'l') or 'q' to quit: jump

Size of pizza ('s', 'm', 'l') or 'q' to quit: med

Current Pizza: medium
Select an item by number (0 when done):
   1. Onions
   2. Bell Pepper
   3. Olives
   4. Mushroom
   5. Pineapple
   6. Pepperoni
   7. Sausage
Selection? 1

Current Pizza: medium + onions
Select an item by number (0 when done):
   1. Onions
   2. Bell Pepper
   3. Olives
   4. Mushroom
   5. Pineapple
   6. Pepperoni
   7. Sausage
Selection? 5

Current Pizza: medium + onions + pineapple
Select an item by number (0 when done):
   1. Onions
   2. Bell Pepper
   3. Olives
   4. Mushroom
   5. Pineapple
   6. Pepperoni
   7. Sausage
Selection? 6

Current Pizza: medium + onions + pineapple + pepperoni
Select an item by number (0 when done):
   1. Onions
   2. Bell Pepper
   3. Olives
   4. Mushroom
   5. Pineapple
   6. Pepperoni
   7. Sausage
Selection? 0
medium + onions + pineapple + pepperoni
Total Price: $16.8
Thank you!


Size of pizza ('s', 'm', 'l') or 'q' to quit: q


------------------------------------------------- */