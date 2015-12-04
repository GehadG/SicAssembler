


package sicassembler;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class readInstructions {
   
     private static ArrayList<ListFile> x = new ArrayList();
     private static Scanner scan;
     private static  String a;
     private static String b;
     private static String c;
     private static String d;
    private static  ArrayList<String> Labels = new ArrayList();
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
             JOptionPane.showMessageDialog(null, "File Not Found", "Error", JOptionPane.WARNING_MESSAGE);
             System.exit(0);
        }
    }
    private static void add(){
         
        while (scan.hasNext()){
            String z  = scan.nextLine();
           if(z.trim().length()==0)
               continue;
           ListFile l = new ListFile();
           if(z.startsWith("."))
           {
               l.setComment(z);
               l.setLabel("Not Valid");
               x.add(l);
               continue;
           }
            a = z.substring(0, 9);
            a=a.trim();
            b = z.substring(9, 17);
            b=b.trim();
            if(z.length()<35){
                c = z.substring(17);
            c=c.trim(); 
            }
            else
            {
                c = z.substring(17,35);
            c=c.trim();
            d=z.substring(35);
            d.trim();
            l.setComm(d);
            
                
            }
            
            l.setLabel(a);
            l.setOperation(b);
            l.setOperand(c);
            
            if(Labels.contains(a)==false){
            Labels.add(a);
            if (a.equals(""))
                Labels.remove(a);
            x.add(l);
            }
            else 
            {
                l.setLabelError(true);
                x.add(l);
            }
            
        }
    }
    private static void closefile(){

        scan.close();
    }
}
