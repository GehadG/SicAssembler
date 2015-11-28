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
    public static ArrayList<ListFile> assign()
    {
        y=readInstructions.getInstructions();
        
        String initial= y.get(0).getOperand();
        y.get(1).setAddress(initial);
        y.get(0).setAddress(initial);
        long decimal = Long.parseLong(initial, 16);
        
        for (int i=1;i<y.size();i++){
            if(y.get(i).getOperation().toLowerCase().equalsIgnoreCase("byte"))
            {
                String m = y.get(i).getOperand();
                String n = m.substring(0, 1);
                if (n.equalsIgnoreCase("x")){
                    String hex = Long.toHexString(decimal);
                    y.get(i).setAddress(hex);
                    decimal = decimal +1;
                }
                
                else{
                     String hex = Long.toHexString(decimal);
                     y.get(i).setAddress(hex);
                     decimal=decimal+y.get(i).getOperand().length()-3;
                }
            }
            else if (y.get(i).getOperation().toLowerCase().equalsIgnoreCase("resw")){
                String k = y.get(i).getOperand();
                int k1 = Integer.parseInt(k);
                String hex = Long.toHexString(decimal);
                y.get(i).setAddress(hex);
                decimal = decimal +(3*k1);
            }
            else if(y.get(i).getOperation().toLowerCase().equalsIgnoreCase("resb")){
                String h = y.get(i).getOperand();
                int h1 = Integer.parseInt(h);
                String hex = Long.toHexString(decimal);
                y.get(i).setAddress(hex);
                decimal = decimal +(1*h1);
            }
            else
            {
                String hex = Long.toHexString(decimal);
                y.get(i).setAddress(hex);
                decimal = decimal +3;
            
                    }
        }
     
         return y;
    }
    
}
