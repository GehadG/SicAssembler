/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sicassembler;

import java.util.ArrayList;

/**
 *
 * @author Mahmoud
 */
public class AssignAdresses {
    
   private static ArrayList<ListFile> y = new ArrayList<ListFile>();
    public static void assign()
    {
        y=readInstructions.getInstructions();//copy all objects of arraylist x to arraylist y
        
        String initial= y.get(0).getOperand();
        y.get(1).setAddress(initial);
        long decimal = Long.parseLong(initial, 16);
        for (int i=2;i<y.size();i++){
            decimal = decimal +3;
            String hex = Long.toHexString(decimal);
            y.get(i).setAddress(hex);
        }
    }
    
}
