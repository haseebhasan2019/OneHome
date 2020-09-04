// import java.util.Scanner;
// import UI.*;

// public class AppConsole 
// {
//     public static void mainConsole(String[] args) throws Exception 
//     {
//         // Declare Variables
//         String action = "";
//         Scanner scanner = new Scanner(System.in);


//         // Client
//         while (!action.equals("5"))
//         {
//             //Start-up menu
//             System.out.print("=== OneHome App ===\n1 - Add New Property\n2 - Display All Properties\n3 - Edit existing property\n4 - Delete Property\n5 - Exit\nSelect action (1-5): ");
//             action = scanner.nextLine();
//             if (action.equals("1"))
//                 PropertyConsole.addProperty();
//             //else if (action.equals("2"))
//             //    PropertyConsole.displayAllProperties();
//             else if (action.equals("3"))
//                 PropertyConsole.editProperty();
//             else if (action.equals("4"))
//                 PropertyConsole.deleteProperty();
//         }
//         scanner.close();
//     }
// }