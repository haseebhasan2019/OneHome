// package UI;

// import com.onehome.service.*;
// import com.onehome.model.*;
// import java.util.*;
// //import java.sql.*;

// public class PropertyConsole 
// {
//     static PropertyService propertyService = new PropertyService();

//     private static Property takeUserInput()
//     {
//         Property p = new Property();
        
//         // Take Input from user
//         Scanner scanner = new Scanner(System.in);

//         System.out.print("Enter Property Title: ");
//         p.setTitle(scanner.nextLine());

//         System.out.print("Enter Property Address1: ");
//         p.setAddress1(scanner.nextLine());

//         System.out.print("Enter Property Address2: ");
//         p.setAddress2(scanner.nextLine()); 
        
//         System.out.print("Enter Property City: ");
//         p.setCity(scanner.nextLine());
       
//         System.out.print("Enter Property State: ");
//         p.setState(scanner.nextLine());

//         System.out.print("Enter Property Zip: ");
//         p.setZip(scanner.nextLine());
       
//         System.out.print("Enter Property Country: ");
//         p.setCountry(scanner.nextLine());
       
//         System.out.print("Enter if Property is Primary Residence? True or false only: ");
//         p.setPrimaryResidence(Boolean.parseBoolean(scanner.nextLine()));
       
//         System.out.print("Enter Property Size: ");
//         p.setSize(scanner.nextLine());
       
//         System.out.print("Enter Property Current Resident: ");
//         p.setCurrentResident(scanner.nextLine());
       
//         System.out.print("Enter Property Moved In Date (YYYYMMDD): ");
//         p.setMovedInDate(scanner.nextLine());
       
//         System.out.print("Enter Property Moved Out Date (YYYYMMDD): ");
//         p.setMovedOutDate(scanner.nextLine());

//         System.out.print("Enter Property Created On Date (YYYYMMDD): ");
//         p.setCreatedOn(scanner.nextLine());

//         System.out.print("Enter Property Created By: ");
//         p.setCreatedBy(scanner.nextLine());

//         System.out.println();
//         scanner.close();

//         return p;
//     }

//     public static void addProperty() 
//     {
//         Property p = takeUserInput();
//         System.out.println(propertyService.addProperty(p));
// 	}

//     public static void editProperty() 
//     {
//         //Declare Variables
//         Scanner scanner = new Scanner(System.in);
//         Property oldRow;

//         //1. Get the user input for ID
//         System.out.print("Enter the ID of the property you want to edit: ");
//         int id = scanner.nextInt();

//         //2. Check to see if that ID exists in the db - 
//         oldRow = propertyService.searchPropertyByID(id);
//         if (oldRow == null)
//         {
//             System.out.println("Invalid Property ID");
//         }
//         else
//         {
//             //3. Display oldRow
//             System.out.println(oldRow.getId() + " |" +  oldRow.getTitle()+ " |" + oldRow.getAddress1() + " |" + oldRow.getAddress2() + " |" + oldRow.getCity() + " |" + oldRow.getState() + " |" + oldRow.getZip() + " |" + oldRow.getCountry() + " |" + oldRow.isPrimaryResidence() + " |" + oldRow.getSize() + " |" + oldRow.getCurrentResident() + " |" + oldRow.getMovedInDate() + " |" + oldRow.getMovedOutDate() + " |" + oldRow.getCreatedOn() + " |" + oldRow.getCreatedBy());
            
//             //4. Take user input for newRow
//             System.out.println("Hit enter if you would like to keep the value the same");

//             //5. call edit property service - update & display error/confirmation messgaes
//             //System.out.println(propertyService.editProperty(oldRow, newRow));
//         }
//         scanner.close();

// 	}

//     public static void deleteProperty() 
//     {
//         System.out.print("Enter the iD of the property you want to delete: ");
//         Scanner scanner = new Scanner(System.in);
//         int id = scanner.nextInt();
//         System.out.println(propertyService.deleteProperty(id));
//         scanner.close();

// 	}
// /*
//     public static void displayAllProperties() throws SQLException
//     {
//         ResultSet result = propertyService.displayAllProperties();

//         if (result == null)
//         {
//             System.out.println("Unable to display all properties");
//         }
//         else
//         {
//             int columnsNumber = result.getMetaData().getColumnCount();
//             for (int i = 1; i <= columnsNumber; i++) 
//             {
//                 if (i == 1 || i == 6 || i ==10) 
//                 {
//                     String format = String.format("| %%%s%ds", "", 5);
//                     System.out.print(String.format(format, result.getMetaData().getColumnName(i)));
//                 }
//                 else if (i == 3 || i == 4 || i ==9)
//                 {
//                     String format = String.format("| %%%s%ds", "", 20);
//                     System.out.print(String.format(format, result.getMetaData().getColumnName(i)));
//                 }
//                 else
//                 {
//                     String format = String.format("| %%%s%ds", "", 15);
//                     System.out.print(String.format(format, result.getMetaData().getColumnName(i)));
//                 }
//             }
//             System.out.println("");
//             while (result.next()) 
//             {
//                 for (int i = 1; i <= columnsNumber; i++) 
//                 {
//                     if (i == 1 || i == 6 || i ==10) 
//                     {
//                         String columnValue = result.getString(i);
//                         String format = String.format("| %%%s%ds", "", 5);
//                         System.out.print(String.format(format, columnValue));
//                     }
//                     else if (i == 3 || i == 4 || i==9)
//                     {
//                         String columnValue = result.getString(i);
//                         String format = String.format("| %%%s%ds", "", 20);
//                         System.out.print(String.format(format, columnValue));
//                     }
//                     else
//                     {
//                         String columnValue = result.getString(i);
//                         String format = String.format("| %%%s%ds", "", 15);
//                         System.out.print(String.format(format, columnValue));
//                     }
//                 }
//                 System.out.println();
//             }
//         }
// 	}*/

// }