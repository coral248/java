import java.util.Scanner;

public class Foothill
{
   static Scanner input_stream = new Scanner(System.in);
   
   public static void main(String[] args)
   {
      String user_name;
      double user_temp;
      int user_id;

      System.out.println("Patient #1 ---");

      user_name = GetPatientName();
      user_id = GetPatientID();
      user_temp = GetPatientTemp();

      System.out.println("Patient #1: " + user_name + " id: " + user_id 
         + " temp: " + user_temp + "\n");

      System.out.println("Patient #2 ---");

      user_name = GetPatientName();
      user_id = GetPatientID();
      user_temp = GetPatientTemp();

      System.out.println("Patient #2: " + user_name + " id: " + user_id 
         + " temp: " + user_temp + "\n");
   }
   
   static String GetPatientName()
   {
      String string_in;
      
      System.out.print("What's the patient's name? ");
      string_in = input_stream.nextLine();
      return string_in;
   }

   static int GetPatientID()
   {
      int id;
      String string_in;

      System.out.print("What's the patient's id #? ");
      string_in = input_stream.nextLine();
      id = Integer.parseInt(string_in);
      return id;
   }

   static double GetPatientTemp()
   {
      double temp;
      String string_in;
      
      System.out.print("What's the patient's temperature? ");
      string_in = input_stream.nextLine();
      temp = Double.parseDouble(string_in);
      return temp;
   }
}

/* ------------- RUN (OUTPUT) ----------------------
Patient #1 ---
What's the patient's name? Marsha Malone
What's the patient's id #? 11111
What's the patient's temperature? 98.5
Patient #1: Marsha Malone id: 11111 temp: 98.5

Patient #2 ---
What's the patient's name? Frank Lewis
What's the patient's id #? 9876
What's the patient's temperature? 22.5
Patient #2: Frank Lewis id: 9876 temp: 22.5
--------------- END OF RUN ------------------------- */