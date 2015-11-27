
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sicassembler;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author Mahmoud
 */
public class readInstructions {
   
     private static ArrayList<ListFile> x = new ArrayList();
     private static Scanner scan;
     private static  String a;
     private static String b;
     private static String c;
    
    public static ArrayList<ListFile> getInstructions()
    {
        openfile();
    
        return x;
    }
    
    private static void openfile(){
        
        try{
            scan = new Scanner(new  File(System.getProperty("user.dir")+"\\SRCFILE"));
            add();
            closefile();
        }
        catch(Exception e){
            System.out.println("File does not exist!");
        }
    }
    private static void add(){
         
        while (scan.hasNext()){
            String z  = scan.nextLine();
           ListFile l = new ListFile();
            a = z.substring(0, 9);
            a=a.trim();
            b = z.substring(9, 17);
            b=b.trim();
            c = z.substring(17);
            c=c.trim();
            l.setLabel(a);
            l.setOperation(b);
            l.setOperand(c);
            x.add(l);
        }
    }
    private static void closefile(){
       
        scan.close();
    }
}
