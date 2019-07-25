/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.record.library;

import java.io.*;
import java.util.Scanner;

/**
 *
 * @author syazwani.s
 */
class Admin {
    
        public static void register(){
        try{
            boolean found = false;
            String filename= "admin.txt";
            FileWriter fw = new FileWriter(filename,true); //the true will append the new data
            
            String username;
            String password;
            
            System.out.println();
            System.out.println("------Register------");
            System.out.println();
            
            Scanner input = new Scanner(System.in);
            System.out.print("Enter username : ");
            username = input.next();
            System.out.print("Enter Password : ");
            password = input.next();
            
            String scanUsername = "";
            String scanPassword = "";
            
            //compare if have same username in the file, retry register2
            
            try{
                Scanner scan = new Scanner(new File("admin.txt"));
                scan.useDelimiter("[,\n]");
    
                while(scan.hasNext() && !found){
                    scanUsername = scan.next();
                    scanPassword = scan.next();
    
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
                boolean run = true;
                while(true){
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
                        Admin.register();
                        run = false;
                    }
                    else if (contChoice == 2){
                        Admin.Login(username, password);
                        run = false;
                    }
                    else{
                        run = true;
                        System.out.println("Error : You should only input 1 or 2 !");
                        System.out.println();
                    }
                }
            } else {

                // else, add to txt file      
                fw.write(System.getProperty( "line.separator" ));
                fw.write(username + ", " + password);//appends the string to the file
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
                Scanner scan = new Scanner(new File("admin.txt"));
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

    private static void Menu(String user) {
      
      int menu;
      System.out.println();
      System.out.println("------Menu------");
      System.out.println();
      System.out.println("1 - View Book");
      System.out.println("2 - Add Book");
      System.out.println("3 - Delete Book");
      System.out.println("4 - Search Book");
      System.out.println("5 - Quit");
      System.out.print("Choose option : ");
      Scanner input = new Scanner(System.in);
      menu = input.nextInt();
      
      switch(menu){
          case 1:
              Student.viewBook(user);
              break;
          case 2:
              Admin.addBook(user);
              break;
          case 3:
              Admin.deleteBook(user);
              break;
          case 4:
              Student.searchBook(user);
              break;
          case 5:
              System.out.println();
              System.out.println("Goodbye...");
              System.exit(0);              
              break;
          default:
              Menu(user);
      }
    }
       
    private static void addBook(String user){
        try{
            boolean found = false;
            String filename= "book.txt";
            FileWriter fw = new FileWriter(filename,true); //the true will append the new data
            
            String bookName;
            String bookAuthor;
            String bookID;
            String category;
            String location;
            
            int option;
            
            System.out.println();
            System.out.println("------Add Book-------");
            System.out.println();
            
            Scanner input = new Scanner(System.in);
            System.out.print("Enter Book Name : ");
            bookName = input.nextLine();
            System.out.print("Enter Book Author : ");
            bookAuthor = input.nextLine();
            System.out.print("Enter Book ID : ");
            bookID = input.next();
            System.out.println("1 - Category Novel");
            System.out.println("2 - Category Comics");
            System.out.print("Enter Category : ");
            option = input.nextInt();
            
            if(option == 1){
                category = "Novel";
            }
            else{
                category = "Comics";
            }
            
            System.out.print("Enter Book Location : ");
            location = input.next();
            
            String scanBookName = "";
            String scanBookAuthor = "";
            String scanBookID = "";
            String scanCategory = "";
            String scanLocation = "";
                        
            //compare if have same book id in the file, retry add book
            
            try{
                Scanner scan = new Scanner(new File("book.txt"));
                scan.useDelimiter("[,\n]");
    
                while(scan.hasNext() && !found){
                    scanBookName = scan.next();
                    scanBookAuthor = scan.next();
                    scanBookID = scan.next();
                    scanCategory = scan.next();
                    scanLocation = scan.next();
   
                    if(scanBookID.trim().equals(bookID.trim()) ){
                        found = true;                    
                    }
                }
                scan.close();
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
            
            if(found == true){
                //already exist
                System.out.println("Book already exist ! Make sure the book have the correct ID as inserted.");
                addBook(user);
            } else {

                // else, add to txt file                      
                fw.write(bookName + ", " + bookAuthor + ", " + bookID + ", " + category + ", " + location);//appends the string to the file
                fw.write(System.getProperty( "line.separator" ));                
                fw.close();
                
                // not found , and created, only then go to menu .                  
                Menu(user);
            }
            
        }
        catch(IOException ioe){
            System.err.println("IOException: " + ioe.getMessage());
        }
    }
    
    private static void deleteBook(String user){
        
        String oriFilepath = "book.txt";
        
        Scanner del = new Scanner(System.in);
        System.out.println();
        System.out.println("-----Delete Book------");
        System.out.println();
        System.out.print("Book ID to Delete : ");
        String removeTerm = del.next();
        
        String tempFile = "temp.txt";
        File oldFile = new File(oriFilepath);
        File newFile = new File(tempFile);
        String bookName = "";
        String bookAuthor = "";
        String bookID = "";
        String category = "";
        String location = "";
        
        try{
            FileWriter fw = new FileWriter(tempFile,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            Scanner scan = new Scanner(new File(oriFilepath));
            scan.useDelimiter("[,\n]");
            
            while(scan.hasNext()){
                bookName= scan.next();
                bookAuthor = scan.next();
                bookID = scan.next();
                category = scan.next();
                location = scan.next();
                
                if(!bookID.trim().equals(removeTerm.trim()) ){ //if text found not equal with removeTerm, write in temp.txt
                    pw.println(bookName + ", " + bookAuthor + ", " + bookID + ", " + category + ", " + location);
                }
            }
            scan.close();
            pw.flush();
            pw.close();
            oldFile.delete();
            File dump = new File(oriFilepath);
            newFile.renameTo(dump);
            System.out.println("Delete completed!");
            Menu(user);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
}