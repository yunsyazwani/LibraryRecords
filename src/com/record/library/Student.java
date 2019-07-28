/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.record.library;

import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author syazwani.s
 */
public class Student {
    
        public static void register(){
        try{
            boolean found = false;
            String filename= "std.txt";
            FileWriter fw = new FileWriter(filename,true); //the true will append the new data
            
            String username, password, name, id;
            
            System.out.println();
            System.out.println("------Register------");
            System.out.println();
            
            Scanner input = new Scanner(System.in);
            System.out.print("Enter username : ");
            username = input.nextLine();
            System.out.print("Enter Password : ");
            password = input.nextLine();
            System.out.print("Enter Name : ");
            name = input.nextLine();
            System.out.print("Enter Student ID : ");
            id = input.nextLine();
            
            String scanUsername = "";
            String scanPassword = "";
            String scanName = "";
            String scanID = "";
            
            //compare if have same username in the file, retry register2
            
            try{
                Scanner scan = new Scanner(new File("std.txt"));
                scan.useDelimiter("[,\n]");
    
                while(scan.hasNext() && !found){
                    scanUsername = scan.next();
                    scanPassword = scan.next();
                    scanName = scan.next();
                    scanID = scan.next();
    
                    if(scanUsername.trim().equals(username.trim()) ){
                        found = true;                    
                    }
                }
                scan.close();
            }
            catch(Exception ex){
                System.out.println("Some errors");
            }
            
            if(found == true){
                //dah exist
                int contChoice;
                Scanner cont = new Scanner(System.in);
                System.out.println("Username already exist ! Try different username.");
                System.out.println();
                System.out.println("1 - Continue with registration");
                System.out.println("2 - Login");
                System.out.print("Choice : ");
                contChoice = cont.nextInt();
                
                if(contChoice == 1){
                    Student.register();
                }
                else{
                    Student.Login(username, password);
                }
            } else {

                // else, add to txt file      
                
                fw.write(username + ", " + password + ", " + name + ", " + id);//appends the string to the file
                fw.write(System.getProperty( "line.separator" ));
                fw.close();
                
                // not found , and created, only then go to login .                  
                Login(username, password);
            }
            
        }
        catch(IOException ioe){
            System.err.println("IOException: " + ioe.getMessage());
        }
    }
        
    public static void Login(String username, String password){
            
            boolean found = false;
            String scanUsername = "";
            String scanPassword = "";

            System.out.println();
            System.out.println("-----Login------");
            System.out.println();
            Scanner input = new Scanner(System.in);
            System.out.print("Enter Username : ");
            username = input.next();
            System.out.print("Enter Password : ");
            password = input.next();

            try{
                Scanner scan = new Scanner(new File("std.txt"));
                scan.useDelimiter("[,\n]");

                while(scan.hasNext() && !found){
                    scanUsername = scan.next();
                    scanPassword = scan.next();

                    if(scanUsername.trim().equals(username.trim()) && scanPassword.trim().equals(password.trim())){
                        found = true;     
                        Menu(username);                
                    }
                }
                scan.close();
               
            }
            catch(Exception ex){
                ex.printStackTrace();
            }

            if(found == false){
                System.out.println("Wrong username or password");
            }
        }

    
    public static void Menu(String user) {
      
      int menu;
      System.out.println();
      System.out.println("------Menu------");
      System.out.println();
      System.out.println("1 - View Book");
      System.out.println("2 - Borrow Book");
      System.out.println("3 - Return Book");
      System.out.println("4 - View Borrow Book");
      System.out.println("5 - Search Book");
      System.out.println("6 - Quit");
      System.out.print("Choose option : ");
      Scanner input = new Scanner(System.in);
      menu = input.nextInt();
      
      switch(menu){
          case 1:
              Student.viewBook(user);
              break;
          case 2:
              borrowBook(user);
              break;
          case 3:
              returnBook(user);
              break;
          case 4:
              viewBorrow(user);
              break;
          case 5:
              searchBook(user);
              break;
          case 6:
              System.out.println();
              System.out.println("Goodbye...");
              System.exit(0);              
              break;
          default:
              Menu(user);
      }
    }
       
    
    public static void viewBook(String user){
        int option;
        String categ = null ;
                
        boolean run = true;
        while(run){
            Scanner choose = new Scanner(System.in);
            System.out.println();
            System.out.println("1 - Novel ");
            System.out.println("2 - Comics");
            System.out.print("Choose Category : ");
            option = choose.nextInt();
            System.out.println();
            switch (option) {
                case 1:
                    categ = "Novel";
                    run = false;
                    break;
                case 2:
                    categ = "Comics";
                    run = false;
                    break;
                default:
                    run = true;
                    System.out.println("Error : You should only input 1 or 2 !");
                    break;
            }
        }
        
        //read all that have chosen category
        String filepath = "book.txt";
        
        try {
            Scanner filebook = new Scanner(new File(filepath));
            
            while(filebook.hasNextLine()){
                
                String line = filebook.nextLine();
                String[] split = line.split(",");
                
                if(split[3].contains(categ)){                    
                    System.out.println("Title : " + split[0]);
                    System.out.println("Author : " + split[1]);
                    System.out.println("Book ID : " + split[2]);
                    System.out.println("Category : " + split[3]);
                    System.out.println("Location : " + split[4]);
                    System.out.println();
                }
            }
           
           filebook.close();
           Menu(user);
           
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void borrowBook(String user){
         try{
            boolean found = false;
            String filename= "borrow.txt";
            FileWriter fw = new FileWriter(filename,true); //the true will append the new data
            
            String name = null, id = null, bookID;
            
            System.out.println();
            System.out.println("------Borrow Book-------");
            System.out.println();
            
            //-------------------------------------------
            //read username from login
            //scan username in std.txt and read name, id
            //display name and id
            String filepath = "std.txt";
            String findName = user;

            try {
                Scanner filestd = new Scanner(new File(filepath));

                while(filestd.hasNextLine()){

                    String line = filestd.nextLine();
                    String[] split = line.split(",");
                    
                    if(split[2].contains(findName)){                        
                        System.out.println("Name : " + split[2]);
                        System.out.println("ID : " + split[3]);
                        
                        name = split[2].trim();
                        id = split[3].trim();
                    }
                }

               filestd.close();

            } catch (FileNotFoundException ex) {
                Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
            }
            
                Scanner input = new Scanner(System.in);
                System.out.println();
                System.out.print("Enter Book ID : ");
                bookID = input.next();

                String scanName, scanId, scanBookID = "";

                //compare if have same book id in the file, retry borrow book
                String file = "borrow.txt";

                try {
                    Scanner fileborrow = new Scanner(new File(file));

                    while(fileborrow.hasNextLine()){

                        String line = fileborrow.nextLine();
                        String[] split = line.split(",");

                        if(split[2].trim().contains(bookID)){
                            found = true;  
                        }
                        else{
                            found = false;
                        }
                    }
                    
                    if(found == true){
                    //already exist
                    System.out.println("Book already Borrowed ! Make sure the book have the correct ID as inserted.");
                    borrowBook(user);

                } else {
                    // else, add to txt file          
                    LocalDate dateReturn = LocalDate.now().plusDays(7);
                    fw.write(name + ", " + id + ", " + bookID + ", " + dateReturn);
                    fw.write(System.getProperty( "line.separator" ));                
                    fw.close();

                    // not found , and created, only then go to menu .  
                    System.out.println("Must return on "+ dateReturn);
                    System.out.println("If you late return the book by 1 day = RM 1.00");
                    Menu(user);
                }

                   fileborrow.close();
                   Menu(user);

                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
                }
                
        }
        catch(IOException ioe){
            System.err.println("IOException: " + ioe.getMessage());
        }
         
    }
    
    public static void returnBook(String user){        
            try {
                Scanner book = new Scanner(System.in);

                System.out.println();
                System.out.println("-----Return Book------");
                System.out.println();
                System.out.print("Book ID to return : ");
                String bookReturn = book.next();//input book id
                File oldFile = new File("borrow.txt");
                File newFile = new File("temp.txt");
                FileWriter fw = new FileWriter(newFile,true); //the true will append the new data

                Scanner fileborrow = new Scanner(oldFile);
                
                try {
                    while(fileborrow.hasNextLine()){

                        String line = fileborrow.nextLine();
                        String[] split = line.split(",");
                        boolean found = false;

                        if(split[2].trim().equals(bookReturn)){
                            
                            System.out.println();
                            System.out.println("Name : " + split[0]);
                            System.out.println("ID : " + split[1]);
                            System.out.println("Book ID : " + split[2]);
                            System.out.println("Date Return : " + split[3]);
                            System.out.println();
                            
                            //baca tarikh kalau lambat return, kena bayar berapa, lebih sehari rm1
                            LocalDate dateMustReturn = LocalDate.parse(split[3].trim());    
                            LocalDate dateNow = LocalDate.now();
                            long days = ChronoUnit.DAYS.between(dateNow, dateMustReturn);
                            System.out.println("Days between: " + days);
                            double fees = days * 1;
                            System.out.println("Fees (1 day = RM1): RM " + String.format("%.2f", fees));
                            System.out.println();
                            System.out.println("Please pay the fee first. Thank you.");
                            System.out.println();

                            //soalan kluar dah bayar ke belum, kalau dah, proceed delete kat borrow.txt, kalau tak, menu.
                            Scanner pay = new Scanner(System.in);
                            System.out.print("Payment have made ? (yes/no) : ");
                            String payment = pay.next();

                            if("yes".equals(payment)){ continue; }
                        }
                        fw.write(split[0] + "," + split[1] + "," + split[2] + "," + split[3]);
                        fw.write(System.getProperty( "line.separator" ));
                    }

                    fileborrow.close();
                    fw.close();

                    //Delete the original, rename new file
                    if (!oldFile.delete()) { System.out.println("Could not delete file"); }
                    if (!newFile.renameTo(oldFile)){ System.out.println("Could not rename file"); }
                        
                    Menu(user);

                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException ioe) {
                System.err.println("IOException: " + ioe.getMessage());
            }
        }
        
    public static void searchBook(String user){
          
        String seachKey ;
        Scanner choose = new Scanner(System.in);
            
        System.out.println();
        System.out.print("Enter Book ID : ");
        seachKey = choose.nextLine();
        System.out.println();
           
        //read all that have chosen category
        String filepath = "book.txt";
        
        try {
            Scanner filebook = new Scanner(new File(filepath));
            
            while(filebook.hasNextLine()){
                
                String line = filebook.nextLine();
                String[] split = line.split(",");
                
                if(split[2].contains(seachKey)){
                    System.out.println("Book Name : " + split[0]);
                    System.out.println("Book Author : " + split[1]);
                    System.out.println("Book ID : " + split[2]);
                    System.out.println("Book Category : " + split[3]);
                    System.out.println("Book Location : " + split[4]);
                    System.out.println();
                }
            }
           
           filebook.close();
           Menu(user);
           
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void viewBorrow(String user){
  
        String borrowUser ;
            
        System.out.println();
        System.out.print("Name : " + user);
        System.out.println();
           
        //read all that have chosen category
        String filepath = "borrow.txt";
        
        try {
            Scanner fileborrow = new Scanner(new File(filepath));
            
            while(fileborrow.hasNextLine()){
                
                String line = fileborrow.nextLine();
                String[] split = line.split(",");
                
                if(split[0].contains(user)){
                    System.out.println("Book ID : " + split[2]);
                    System.out.println("Date Return : " + split[3]);
                    System.out.println();
                }
            }
           
           fileborrow.close();
           Menu(user);
           
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
