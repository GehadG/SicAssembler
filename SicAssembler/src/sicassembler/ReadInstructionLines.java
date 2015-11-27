
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sicassembler;
import java.io.File;
import java.util.ArrayList;
/**
 *
 * @author Mahmoud
 */
public class ReadInstructionLines {
   
    ArrayList<ListFile> x = new ArrayList();
    /*String line;
    String line1 = line.substring(0, 9);
    String line2 = line.substring(9, 17);
    String line3 = line.substring(17);
    ListFile[]l= new ListFile[5];
    for (int i=1;i<=5;i++)
    {
        l[i].setLabel(line1);
        l[i].setOperation(line2);
        l[i].setOperand(line3);
    }*/
    
    
    public ReadInstructionLines()
    {
        File f = new  File("C:\\java\\SRCFILE");
    
        if(f.exists()){
            System.out.println("hi");
    }
    }
}
