/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iteu333a.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author easalinas
 */
public class Iteu333aProject {
    static int line = 0;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        compiler();// TODO code application logic here
    }
    
    public static void compiler() throws FileNotFoundException, UnsupportedEncodingException{
        Set<String> stringVars = new HashSet<String>();
        Set<String> numVars = new HashSet<String>();
        StringBuffer strBuff = new StringBuffer("");
        strBuff.append("public class Output {"
                    + "public static void main(String[] args{");
        String str = "";
        String temp = "";
        String pattern = "";
        int strlen=0;
        int error = 0;
        
        try {
            Scanner input;
            input = new Scanner(new File("input.txt"));
            
            while(input.hasNextLine()){
                ++line;
                System.out.println("LINE " + line);
                str = input.nextLine();
                
                temp = str;
                strlen = str.length();
                //COMMENT
                if (str.contains("//") && str.charAt(0) != '/' && strlen != 0){
                    for (int i=0; i<str.length(); i++){
                        if (str.charAt(i) == '/' && str.charAt(i-1) == '/'){
                            System.out.println(str.substring(0, i-1)+"COMMENT");//CHECKING IF THE LINE CONTAINS A COMMENT
                            strlen = i-1;
                        }
                    }
                    int count= strlen-1;
                    for (int i = count; i>0; i--){
                        if (str.charAt(i) == ' ')
                            strlen--;
                        else
                            break;
                    }
                    temp = str.substring(0, strlen);
                }
                if (strlen != 0){
                    //CHECK SEMI COLON
                    if (temp.charAt(strlen-1) != ';'){
                        if (!temp.contains("for(") || !temp.contains("for (") && !temp.contains("if(") || !temp.contains("if (") && !temp.contains("switch(") || !temp.contains("switch (")){
                            System.out.println("SYNTAX ERROR (no semicolon): Line "+line);
                            error = 1;
                        }
                    }
                    //DOUBLE SPACE
                    else if (temp.contains("  ")){
                        System.out.println("SYNTAX ERROR (Too many spaces): Line "+line);//CHECKING IF THERE IS A DOUBLE SPACE 
                        error = 1;
                    }
                    
                    else if (temp.contains("=") || temp.contains(" = ")){//VARIABLE DECLARATION
                        String []split = temp.split(" ");
                        
                        if (temp.contains("String ")){
                            if(temp.matches("String\\s\\w*\\s=\\s\\w*;")){
                                if (numVars.contains(split[1])){
                                    System.out.println("SYNTAX ERROR (variable already exist): Line "+line);
                                    error = 1;
                                }
                                else
                                    stringVars.add(split[1]);
                            }
                            else{
                                System.out.println("SYNTAX ERROR: Line "+line);
                                error = 1;
                            }
                        }
                        else if (temp.contains("int ") || temp.contains("double ") || temp.contains("float ")){
                            if(temp.matches("int\\s\\w*\\s=\\s(\\d*\\.?\\d*);")){
                                if (numVars.contains(split[1])){
                                    System.out.println("SYNTAX ERROR (variable already exist): Line "+line);
                                    error = 1;
                                }
                                else{
                                    numVars.add(split[1]);
                                }
                            }else{
                                System.out.println("SYNTAX ERROR (invalid data type): Line "+line);
                                error = 1;
                            }
                        }
                        else{//value initialization
                            if (numVars.contains(split[0])){//check if value is numerical
                                
                            }
                            else if (stringVars.contains(split[0])){//check if value is a valid string
                                if (!split[2].matches("\".*\";")){
                                    System.out.println("SYNTAX ERROR (invalid value): Line "+line);
                                    error = 1;
                                }
                            }
                            else{//if variable is not a string or int then NO DATA TYPE
                                System.out.println("SYNTAX ERROR (no data type): Line "+line);
                                error = 1;
                            }
                        } 
                    }
                    else if (temp.contains("labas(")){
                        temp = temp.replaceAll("labas", "System.out.println");
                    }
                    else if (temp.contains("makeCompyut(")){
                        temp = temp.replaceAll("makeCompyut", "System.out.println");
                    }
//                    else if (temp.contains("makeSukat(")){
//                        
//                        temp.replaceAll("makeSukat", "get");
//                    }
                    
                }
                strBuff.append("\n"+temp);
            }
            
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex);
            Logger.getLogger(Iteu333aProject.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (error == 0){
            PrintWriter pw = new PrintWriter("Output.java", "UTF-8");
            pw.println(strBuff+"\n}");
        }
        
        JOptionPane.showMessageDialog(null, strBuff);
    }
    
    }
