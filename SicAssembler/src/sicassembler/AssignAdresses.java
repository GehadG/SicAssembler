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
        y=readInstructions.getInstructions();
        
        String initial= y.get(0).getOperand();
        y.get(1).setAddress(initial);
        long decimal = Long.parseLong(initial, 16);
        System.out.println(decimal);
        for (int i=1;i<y.size();i++){
            if(y.get(i).getOperation().toLowerCase().equals("byte"))
            {
            String hex = Long.toHexString(decimal);
            y.get(i).setAddress(hex);
                decimal=decimal+y.get(i).getOperand().length()-3;
                
            }
            else
            {
                String hex = Long.toHexString(decimal);
            y.get(i).setAddress(hex);
            decimal = decimal +3;
            
                    }
        }
        
         for(ListFile l:y)
         {
             System.out.println(l.toString());
             
         }
    }
    
}
