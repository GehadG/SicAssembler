
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
public class read {
   
     private ArrayList<ListFile> x = new ArrayList();
     private Scanner scan;
     private String a;
     private String b;
     private String c;
    
    public read()
    {
        openfile();
    }
    public void openfile(){
        
        try{
            scan = new Scanner(new  File("C:\\Users\\Gehad-PC\\Documents\\NetBeansProjects\\SicAssembler\\SicAssembler\\SRCFILE"));
            add();
            closefile();
        }
        catch(Exception e){
            System.out.println("File does not exist!");
        }
    }
    public void add(){
         
        while (scan.hasNext()){
           ListFile l = new ListFile();
            a = scan.next();
            b = scan.next();
            c = scan.next();
            l.setLabel(a);
            l.setOperation(b);
            l.setOperand(c);
            x.add(l);
        }
    }
    public void closefile(){
         
         for(ListFile l:x)
         {
             System.out.println(l.toString());
             
         }
        scan.close();
    }
}
