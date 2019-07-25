/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.record.library;

import java.time.LocalDate;
import java.util.Scanner;

/**
 *
 * @author syazwani.s
 */
class Library_Record {

    /**
     * @param args the command line arguments
     */    
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        mainFunction:
        System.out.println("Welcome to My Libary");
            try{
                int category;
                Scanner choice = new Scanner(System.in);
                System.out.println();
                System.out.println("1 - Student ");
                System.out.println("2 - Admin ");
                System.out.print("Enter choice (1 or 2) : ");
                category = choice.nextInt();
                
                if(category == 2){
                        int option;
                        Scanner choose = new Scanner(System.in);
                        System.out.println();
                        System.out.println("1 - Register ");
                        System.out.println("2 - Login ");
                        System.out.print("Enter choice (1 or 2) : ");
                        option = choose.nextInt();

                        if(option == 2){

                        String username = null;
                        String password = null;

                        Admin.Login(username, password);

                        }
                        else if (option == 1){
                            Admin.register();

                        }
                        else{
                        }
                    
                }
                
                else if(category == 1){

                        int option;
                        Scanner choose = new Scanner(System.in);
                        System.out.println();
                        System.out.println("1 - Register ");
                        System.out.println("2 - Login ");
                        System.out.print("Enter choice (1 or 2) : ");
                        option = choose.nextInt();

                        if(option == 2){

                            String username = null;
                            String password = null;

                            Student.Login(username, password);

                        }
                        else if(option == 1){
                            Student.register();
                        }
                        else{
                            System.out.println("Error : You should only input 1 or 2 !");
                        }
                    
                }
                else{
                    System.out.println("Error : You should only input 1 or 2 !");
                }
            }
            catch(Exception ex){
                
                System.out.println("Error : You should only input 1 or 2 !");
                
            }
        }

}
        
       