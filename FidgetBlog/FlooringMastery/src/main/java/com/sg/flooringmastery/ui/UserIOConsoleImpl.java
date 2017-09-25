/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.ui;

import java.util.Scanner;



/**
 *
 * @author vincentsiciliano
 */
public class UserIOConsoleImpl implements UserIO{

    Scanner scanner = new Scanner(System.in);
    
    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    @Override
    public double readDouble(String prompt) {
        double dbl = Double.parseDouble(prompt);
        return dbl;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double dbl = Double.parseDouble(prompt);
        if(dbl>=min&&dbl<=max){
            return dbl;
        }else{
            System.out.println("Number out of bounds. Returned 0.");
            return 0;
        }
    }

    @Override
    public float readFloat(String prompt) {
        float flt = Float.parseFloat(prompt);
        return flt;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float flt = Float.parseFloat(prompt);
        if(flt>=min&&flt<=max){
            return flt;
        }else{
            System.out.println("Number out of bounds. Returned 0.");
            return 0;
        }
    }

    @Override
    public int readInt(String prompt) {
        System.out.println(prompt);
        String stringNum = scanner.nextLine();
        int num = 0;
        
        try{
        num = Integer.parseInt(stringNum);
        }catch(NumberFormatException e){
            print("Invalid entry.");
        }
        return num;
        
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        System.out.println(prompt);
        int num = 0;
        try{
            num = scanner.nextInt();
            if(num>=min&&num<=max){
                return num;
            }else{
                System.out.println("Number out of bounds. Returned 0.");
                return 0;
            }
        }catch(NumberFormatException e){
            print("Invalid entry.");
        }
        return num;
    }

    @Override
    public long readLong(String prompt) {
        long lng = Long.parseLong(prompt);
        return lng;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long lng = Long.parseLong(prompt);
        if(lng>=min&&lng<=max){
            return lng;
        }else{
            System.out.println("Number out of bounds. Returned 0.");
            return 0;
        }
        
    }

  
    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        String string = scanner.nextLine();       
        return string;   
    }

      
    
    
}
