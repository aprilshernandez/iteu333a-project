/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iteu333a.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author easalinas
 */
public class Iteu333aProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        compiler();// TODO code application logic here
    }
    
    public static void compiler(){
        String str = null;
        int strlen=0;
        
        try {
            Scanner input;
            input = new Scanner(new File("input.txt"));
            
            while(input.hasNextLine()){
                str = input.nextLine();
                strlen = str.length();
                //COMMENT
                if (str.contains("//")){
                    for (int i=0; i<str.length(); i++){
                        if (str.charAt(i) == '/' && str.charAt(i-1) == '/'){
                            System.out.println(str.substring(0, i-1)+"COMMENT");//CHECKING IF A LINE CONTAINS A COMMENT
                            strlen = i-1;
                        }
                    }
                }
                //CHECK SEMI COLON
                if (!str.contains("for(") || !str.contains("for (") && !str.contains("if(") || !str.contains("if (") && !str.contains("switch(") || !str.contains("switch (")){
                    System.out.println("check");
                    if (str.charAt(strlen-1) != ';'){
                        System.out.println("SYNTAX ERROR");
                    }
                }
                
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Iteu333aProject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
