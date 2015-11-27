/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sicassembler;

import java.io.File;

/**
 *
 * @author Mahmoud
 */
public class SicAssembler {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String s="test     start   1000";
        String sx =s.substring(9, 17);
        String sx2 =s.substring(0, 9);
        String sx3 =s.substring(17);
        
        System.out.println(sx);
        System.out.println(sx2);
        System.out.println(sx3);
        
        File f = new  File("C:\\java\\SRCFILE");
    
        if(f.exists()){
            System.out.println("hi");
    }
        
    }
    
}
