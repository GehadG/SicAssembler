
package sicassembler;

import java.util.ArrayList;
import java.util.HashMap;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


/**
 *
 * @author Mahmoud
 */
public class AssignAdresses {

    private static ArrayList<ListFile> y = new ArrayList<ListFile>();
    private static HashMap<String, String> symbolTable = new HashMap();
    private static int flag = 0;
    private static int flag2=0;
    private static String kooso = "";
    private static String b2 = "";
         private static long decimal ;
    public static ArrayList<ListFile> assign() throws ScriptException {
        y = readInstructions.getInstructions();

        String initial = y.get(0).getOperand();
        y.get(1).setAddress(initial);
        y.get(0).setAddress(initial);
         decimal = Long.parseLong(initial, 16);
         for (int i = 1; i < y.size(); i++) {
             if (y.get(i).getComment().equalsIgnoreCase("No Comment") == false) {
                 continue;
             }
             if (y.get(i).getOperation().equalsIgnoreCase("LTORG")) {
                 y.get(i).setAddress("");
                 continue;
             }
             if(y.get(i).getOperand().equalsIgnoreCase("reqaddress"))
             {String hex = Long.toHexString(decimal);
             y.get(i).setObjcode(hex.toUpperCase());
             y.get(i).setOperand(hex.toUpperCase());
             }
             if (y.get(i).getOperation().equalsIgnoreCase("end") || y.get(i).getOperation().equalsIgnoreCase("ltorg")) {
                 String hex = Long.toHexString(decimal);
                 y.get(i).setAddress(hex);
                 continue;
             }
             if (y.get(i).getOperation().equalsIgnoreCase("ORG")) {
                 flag++;
                 y.get(i).setAddress("    ");
                 y.get(i).setObjcode("      ");
                 if (flag == 1) {
                     b2 = y.get(i - 1).getAddress();
                     kooso = y.get(i - 1).getOperand();
                     if (!y.get(i).isOrgErr()) {
                         for (int j = 0; j < i; j++) {
                             if (y.get(i).getOperand().equalsIgnoreCase(y.get(j).getLabel())) {
                                 String temp = y.get(j).getAddress();
                                 decimal = Long.parseLong(temp, 16);
                             }
                             
                         }
                     } else if (y.get(i).getOperand().contains("+") || y.get(i).getOperand().contains("-") || isNumber(y.get(i).getOperand())||y.get(i).getOperand().equalsIgnoreCase("*")) {
                         decimal = Long.parseLong(eval(y.get(i).getOperand(),y.get(i)), 16);
                     }
                 }
                 if (flag == 2) {
                     decimal = Long.parseLong(b2, 16) + Long.parseLong(kooso, 16);
                     flag = 0;
                 }
                 continue;
             }
             
             if (y.get(i).getOperation().equalsIgnoreCase("EQU")) {
                 String tempo=Long.toHexString(Long.parseLong(eval(y.get(i).getOperand(),y.get(i)), 16));
                 
                 y.get(i).setAddress(tempo);
                 continue;
             }
             
             if (y.get(i).getOperation().toLowerCase().equalsIgnoreCase("byte")) {
                 String m = y.get(i).getOperand();
                 String n = m.substring(0, 1);
                 if (n.equalsIgnoreCase("x")) {
                     String hex = Long.toHexString(decimal);
                     y.get(i).setAddress(hex);
                     decimal = decimal + 1;
                 } else {
                     String hex = Long.toHexString(decimal);
                     y.get(i).setAddress(hex);
                     decimal = decimal + y.get(i).getOperand().length() - 3;
                 }
             } else if (y.get(i).getOperation().toLowerCase().equalsIgnoreCase("resw")) {
                 String k = y.get(i).getOperand();
                 int k1 = Integer.parseInt(k);
                 String hex = Long.toHexString(decimal);
                 y.get(i).setAddress(hex);
                 decimal = decimal + (3 * k1);
             } else if (y.get(i).getOperation().toLowerCase().equalsIgnoreCase("resb")) {
                 String h = y.get(i).getOperand();
                 int h1 = Integer.parseInt(h);
                 String hex = Long.toHexString(decimal);
                 y.get(i).setAddress(hex);
                 decimal = decimal + (1 * h1);
             } else {
                 String hex = Long.toHexString(decimal);
                 y.get(i).setAddress(hex);
                 decimal = decimal + 3;
                 
             }
             
             if (y.get(i).getLabel() != "" && symbolTable.containsKey(y.get(i).getLabel()) == false) {
                 symbolTable.put(y.get(i).getLabel(), y.get(i).getAddress());
             }
         }
        return y;
    }

    private static String eval(String op ,ListFile s) throws ScriptException {
        op=op.replaceAll(" ", "");
        String o=op;
        boolean flager=false;
        if(op.equals("*"))
            return Long.toHexString(decimal);
        String delim = "[+-]";
        String[] labels = new String[op.split(delim).length];
        labels = op.split(delim);
        for (int i = 0; i < labels.length; i++) {
            if (symbolTable.containsKey(labels[i])) {
                int c=0;
                for(int j=0;j<y.indexOf(s);j++)
                {c++;
                    if (y.get(j).getLabel().equalsIgnoreCase(labels[i]))
                        break;
                    
                        
                            
                }
                if(c==y.indexOf(s)-1)
                    flager =true;
                if(flager)
                {
                    s.setForwErr(true);
                    return Long.toHexString(decimal);
                }
                op = op.replace(labels[i], "" + Integer.parseInt(symbolTable.get(labels[i]), 16));
                
            } else if (isNumber(labels[i])) {
                op = op.replace(labels[i], labels[i]);
            }
            
          //  System.out.println(i<labels.length-2);   
             if(i<labels.length-2){
                if(!isNumber(labels[i])&&!isNumber(labels[i+1])&&!isNumber(labels[i+2])&&o.charAt(op.indexOf(labels[i])+labels[i].length())=='-'&&op.charAt(op.indexOf(labels[i+1])+labels[i+1].length())=='-')
                     s.setInvExp(true);
                s.setInvExp(true);
                s.setInvExp(true);
                s.setInvExp(true);
                   return Long.toHexString(decimal);
                    }
               
          if(i<labels.length-1){
              System.out.println(op);
            if(!isNumber(labels[i])&&!isNumber(labels[i+1])&&o.charAt(o.indexOf(labels[i])+labels[i].length())=='+'){
                      
                   s.setInvExp(true);
                   return Long.toHexString(decimal);
                    
                }}
          
           else  if(isNumber(labels[i])){
                
                //law awl7eta fiop b constant w eli b3dha relative w benhom minus
               if(labels.length-i>1){
                if(!isNumber(labels[i+1])&&op.charAt(op.indexOf(labels[i])+labels[i].length())=='-'){
                    
                   s.setInvExp(true);
                   return Long.toHexString(decimal);
                    
                }
                
                 int x=Integer.parseInt((labels[i]));
               op= op.replaceAll(labels[i],""+x);
               
                
            }}
           
            
           else if(!symbolTable.containsKey(labels[i])){
               //System.out.println(labels[i]);
                s.setForwErr(true);
                return Long.toHexString(decimal);
            }

        
        } 
         
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        
        String finall = Integer.toHexString(Integer.parseInt(engine.eval(op).toString()));
        System.out.println(finall);
        if (finall.length() < 4) {
            int m = finall.length();

            for (int i = 0; i < 4 - m; i++) {
                finall = "0" + finall;
            }

        }

        return finall;

    }

    private static boolean isNumber(String s) {//System.out.println(s);
        boolean check = true;

        try {

            Integer.parseInt(s);

        } catch (NumberFormatException e) {
            check = false;
        }
        return check;
    }
}
