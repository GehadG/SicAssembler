/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sicassembler;

import java.util.ArrayList;

/**
 *
 * @author user
 */
public class ObjectCode {
   
    private static ArrayList<ListFile> b = new ArrayList<ListFile>();
    private static OpTable b22=new OpTable();
   public static ArrayList<ListFile> objectcode(){
       b=AssignAdresses.assign();
       for(int i=1;i<b.size();i++){
           String ba="";
           int x=0;
          String b2= b.get(i).getOperation();
          if(!b2.equalsIgnoreCase("resw")&&!b2.equalsIgnoreCase("resb")&&!b2.equalsIgnoreCase("end")&&!b2.equalsIgnoreCase("word")&&!b2.equalsIgnoreCase("byte"))
          {String b3=b22.getOP(b2);
          String b4 = b.get(i).getOperand();
          if(b4.indexOf(',')!=-1){
               x=1;
          }
          if(b.get(i).getOperand().startsWith("0")){
            String z=  b.get(i).getOperand();
            z=z.replaceFirst("0", "");
           ba=z;
          }
          else
          {
             String buffer = b.get(i).getOperand();
             if (buffer.contains(","))
             {
                 int l = buffer.indexOf(",");
                 buffer=buffer.substring(0, l);
                 
             }
             for(int t=0;t<b.size();t++)
             {
                 for(ListFile l : b)
                 {
                     if(l.getLabel().equals(buffer))
                             {
                                 ba=l.getAddress();
                             }
                 }
             }
          }
          long dec = Long.parseLong(ba, 16);
          String bin = String.format("%16s",Long.toBinaryString(dec)).replace(' ','0');
          bin=""+x+""+bin.substring(1);
          long bin2 = Long.parseLong(bin,2);
          String hex=java.lang.Long.toHexString(bin2);
          String objcode=b3+hex;
          b.get(i).setObjcode(objcode.toUpperCase());
          }
          else if(b2.equalsIgnoreCase("byte"))
          {   String s=b.get(i).getOperand();
          if(s.startsWith("c'")){
             s= s.replace("c'", "");
             s= s.replace("'", "");
              String ascii="";
       for(int t=0;t<s.length();t++)
       {
           String z = Integer.toHexString(s.charAt(t));
           ascii=ascii+z;
           
           
       }
       b.get(i).setObjcode(ascii.toUpperCase());
       
          }
          
          else if( s.startsWith("x'"))
          {s= s.replace("x'", "");
             s= s.replace("'", "");
              b.get(i).setObjcode(s.toUpperCase());
          }
          }
          
          else if(b2.equalsIgnoreCase("word"))
          {String s=b.get(i).getOperand();
                int g = Integer.parseInt(s);
       String bearing ="";
       
           String z = Integer.toHexString(g);
         for(int k=0;k<6-z.length();k++)
         {
             bearing=bearing+"0";
         }
         bearing=bearing+z;
         b.get(i).setObjcode(bearing.toUpperCase());
          
          }
          
       }
       
       for(ListFile l : b)
       {
           l.print();
       }
       return b;
   }
}