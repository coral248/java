public class Boxcar
{
   public static void main (String[] args)
   {
      Student[] my_class = { new Student("smith", 95), 
            new Student("bauer", 123),
            new Student("jacobs", 195), 
            new Student("renquist", 148),
            new Student("3ackson", 108), 
            new Student("perry", 225),
            new Student("loceff", 44), 
            new Student("stollings", 452),
            new Student("charters", 295), 
            new Student("cassar", 321),
            };
  
      Student.PrintArrayConsole("The Array to be Searched:", my_class);
      Student.ArraySort(my_class);
      Student.PrintArrayConsole("The Array to be Searched:", my_class);
 
      String last;
      int found;
 
      last = "stollings";
      found = Student.BinarySearch(my_class, last, 0, 
            my_class.length - 1);
      if ( found >= 0 )
         System.out.println(last + " IS in list at position " + found);
      else
         System.out.println(last + " is NOT in list.");
 
      last = "Jacobs";
      found = Student.BinarySearch(my_class, last, 0, 
            my_class.length - 1);
     if ( found >= 0 )
         System.out.println(last + " IS in list at position " + found);
      else
         System.out.println(last + " is NOT in list.");

      last = "Smart";
      found = Student.BinarySearch(my_class, last, 0, 
            my_class.length - 1);
     if ( found >= 0 )
         System.out.println(last + " IS in list at position " + found);
      else
         System.out.println(last + " is NOT in list.");
    }
}

class Student
{
   private String last_name;
   private long total_points;
   
   // constructor requires parameters - no default supplied
   public Student( String lst,long pts)
   {
      last_name = "zz-error";
      total_points = 0;
      // at least require that it stars with a letter
      if (lst != null && Character.isLetter(lst.charAt(0)))
         last_name = lst;
      if (pts >= 0 && pts <= 1000)
         total_points = pts;    
   }
   
   public String toString()
   {
      String strStudent = last_name + " Points: " + total_points;
      return strStudent;
   }
 
   // print out array with string as a title for the message box
   public static void PrintArrayConsole(String title, Student[] data)
   {
      
      System.out.println("\n********** " + title + " **********\n");
      for (int k =0; k < data.length; k++)
         System.out.println( data[k].toString() );
      System.out.println();
  }

   // returns true if a modification was made to the array
   private static boolean FloatLargestToTop(Student[] data, int top)
   {
      boolean changed = false;
      Student temp;
      
      // notice we stop at length -2 because of expr. k+1 in loop
      for (int k =0; k < top; k++)
        if (data[k].last_name.compareToIgnoreCase(data[k+1].last_name)>0)
        {
           temp = data[k];
           data[k] = data[k+1];
           data[k+1] = temp;
           changed = true;
        }
      return changed;
    }
   
    public static void ArraySort(Student[] array)
    {
       for (int k = 0; k < array.length; k++)
          if ( !FloatLargestToTop(array, array.length-1-k) )
             return;
    }
    
    public static int BinarySearch(Student[] array, String keyLast
         , int first_index, int last_index)
    {
       int middle_index, result;
       
       if (first_index > last_index)
          return -1;
       middle_index = (first_index + last_index) / 2;
       result 
          = keyLast.compareToIgnoreCase(array[middle_index].last_name);
       
       if (result==0)
          return middle_index;   //found him!
       else if (result < 0)
          return BinarySearch( array, keyLast, 
                first_index, middle_index-1);
       else
          return BinarySearch( array, keyLast, 
                middle_index+1, last_index); 
    }
}