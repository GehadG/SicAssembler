/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sicassembler;

import java.util.ArrayList;
import java.util.HashMap;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import static jdk.nashorn.internal.runtime.JSType.isNumber;

/**
 *
 * @author Mahmoud
 */
public class AssignAdresses {
    
   private static ArrayList<ListFile> y = new ArrayList<ListFile>();
   private static  HashMap<String,String> symbolTable= new HashMap();
    public static ArrayList<ListFile> assign() throws ScriptException
    {
        y=readInstructions.getInstructions();
        
        String initial= y.get(0).getOperand();
        y.get(1).setAddress(initial);
        y.get(0).setAddress(initial);
        long decimal = Long.parseLong(initial, 16);
        
        for (int i=1;i<y.size();i++){
            if(y.get(i).getComment().equalsIgnoreCase("No Comment")==false)
            {
                continue;
            }
            if(y.get(i).getOperation().equalsIgnoreCase("LTORG"))
            {
                y.get(i).setAddress("");
                continue;
            }
            if(y.get(i).getOperation().equalsIgnoreCase("end")||y.get(i).getOperation().equalsIgnoreCase("ltorg"))
            {
                String hex = Long.toHexString(decimal);
                y.get(i).setAddress(hex);
                continue;
            }
            if (y.get(i).getOperation().equalsIgnoreCase("ORG")){
                y.get(i).setAddress("    ");
                y.get(i).setObjcode("      ");
                if(!y.get(i).isOrgErr())
                {
                    for(int j=0;j<i;j++)
                    {
                        if(y.get(i).getOperand().equalsIgnoreCase(y.get(j).getLabel())){
                            String temp = y.get(j).getAddress();
                            decimal = Long.parseLong(temp,16);
                        }
                            
                    }
                }
                else if(y.get(i).getOperand().contains("+")||y.get(i).getOperand().contains("-")||isNumber(y.get(i).getOperand()))
                {   
                    decimal = Long.parseLong(eval(y.get(i).getOperand()),16);
                }
                continue;
            }
            
            if(y.get(i).getOperation().equalsIgnoreCase("EQU"))
            {
                y.get(i).setAddress(Long.toHexString(Long.parseLong(eval(y.get(i).getOperand()),16)));
                continue;
            }
            
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
            
            if(y.get(i).getLabel()!=""&&symbolTable.containsKey(y.get(i).getLabel())==false)
            {   
                symbolTable.put(y.get(i).getLabel(), y.get(i).getAddress());
            }
        }
     
         return y;
    }
    
    private static String eval(String op) throws ScriptException
    {
        String delim = "[+-]";
        String[] labels = new String[op.split(delim).length];
        labels = op.split(delim);
        for(int i=0;i<labels.length;i++)
        {  
            if(symbolTable.containsKey(labels[i])){
            op=op.replace(labels[i],""+Integer.parseInt(symbolTable.get(labels[i]),16));
            
        }
        else if(isNumber(labels[i]))
        {
            op=op.replace(labels[i],labels[i]);
        }
        else
        {
            //error;
        }
        
    }
         ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        System.out.println(op);
        String finall=Integer.toHexString(Integer.parseInt(engine.eval(op).toString()));
        if(finall.length()<4)
        {int m= finall.length();
         
        for(int i=0;i<4-m;i++)
            finall="0"+finall;
            
        }
       
        return finall;
    
}
    
    private static boolean isNumber(String s)
    {//System.out.println(s);
        boolean check = true;
        
        try {

        Integer.parseInt(s);

    }catch (NumberFormatException e) {
        check = false;
    }
    return check;
    }
}
