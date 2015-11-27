
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
public class ReadInstructionLines {
   
    ArrayList<ListFile> x = new ArrayList();
     private Scanner scan;
     private String a;
     private String b;
     private String c;
    
    public ReadInstructionLines()
    {
        
    }
    public void openfile(){
        
        try{
            scan = new Scanner(new  File("C:\\java\\SRCFILE"));
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
            x.add(new ListFile());
        }
    }
    public void closefile(){
        scan.close();
    }
}
