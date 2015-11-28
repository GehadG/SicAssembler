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
           int x=0;
          String b2= b.get(i).getOperation();
          if(!b2.equalsIgnoreCase("resw")&&!b2.equalsIgnoreCase("resb")&&!b2.equalsIgnoreCase("end")&&!b2.equalsIgnoreCase("word")&&!b2.equalsIgnoreCase("byte"))
          {String b3=b22.getOP(b2);
          String b4 = b.get(i).getOperand();
          if(b4.indexOf(',')!=-1){
               x=1;
          }
          String ba=b.get(i).getAddress();
          long dec = Long.parseLong(ba, 16);
          String bin = String.format("%16s",Long.toBinaryString(dec)).replace(' ','0');
          bin=""+x+""+bin.substring(1);
          long bin2 = Long.parseLong(bin,2);
          String hex=java.lang.Long.toHexString(bin2);
          String objcode=b3+hex;
          b.get(i).setObjcode(objcode);
          System.out.println(bin );
          //System.out.println();
          System.out.println(objcode);
          }
          
       }
       return b;
   }
}
