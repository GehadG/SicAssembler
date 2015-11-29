/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sicassembler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gehad
 */
public class FileCreator {

    private static ArrayList<ListFile> l2 = new ArrayList<ListFile>();

    public FileCreator() {
        l2 = ObjectCode.objectcode();
        createLIS();
       createOBJ();
    }

    private void createLIS() {
        

        File file = new File("LISFILE");
        FileWriter fw;

        try {
            fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("FistBump SIC Assembler v1.0");
            bw.newLine();
            bw.newLine();
            bw.newLine();
            for (ListFile l : l2) {
                bw.write(l.toString());
                bw.newLine();
            }
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(SicAssembler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private void createOBJ()
    {
        File file = new File("DEVF2");
        FileWriter fw;
        
        
        try {
            String head=getHead();
            String text=getText();
            String end=getEnd();
            fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(head);
            bw.newLine();
            bw.write(text);
            bw.newLine();
            bw.write(end);
            bw.close();

        } catch (IOException ex) {
            Logger.getLogger(SicAssembler.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
private String hexCal(String a,String b){
    int sa,ea;
    sa = Integer.parseInt(a,16);
    ea = Integer.parseInt(b,16);
    String hex=Integer.toHexString(ea-sa);
     hex=hex.toUpperCase();
     hex=String.format("%2s",hex).replace(' ', '0');
    return hex;
}
private String getHead(){
    int i;
            String hex,fi  =  l2.get(0).getLabel();
            String add=l2.get(0).getAddress();
           if(fi.length()<6){
               i=6-fi.length();
               fi=String.format("%-6s",fi);
           }
           if(add.length()<6)
           {
                add=String.format("%6s",add).replace(' ', '0');
           }
           hex=hexCal(l2.get(0).getAddress(),l2.get(l2.size()-1).getAddress());
           if(hex.length()<6){
               i=6-fi.length();
               hex=String.format("%6s",hex).replace(' ', '0');
           }
return "H"+fi+add+hex;
}
private String getText(){
    int max=10,i=1;
    String objcode="";
    String len,end=String.format("%6s",l2.get(1).getAddress()).replace(' ', '0'),start=String.format("%6s",l2.get(1).getAddress()).replace(' ', '0');
    String text="";
    for(i=1;i<=l2.size()-1;i++){
        
        if(text==""){
            text="T"; 
        }
        else if(text.charAt(text.length()-1)=='T'){
            text=text+end;
        }
        if(l2.get(i).getOperation().equals("resw")||l2.get(i).getOperation().equals("resb")){
           end=String.format("%6s",l2.get(i).getAddress()).replace(' ', '0');
            len=hexCal(start,end);
            end=String.format("%6s",l2.get(i+1).getAddress()).replace(' ', '0');
            start=String.format("%6s",l2.get(i+1).getAddress()).replace(' ', '0');
            text=text+len+objcode+"\r\nT";
            objcode="";
        }
        else if(objcode.length()<60)
        {
            objcode=objcode+l2.get(i).getObjcode();
            objcode=objcode.toUpperCase();
            if(l2.get(i).getOperation().equals("end")){
                end=String.format("%6s",l2.get(i).getAddress()).replace(' ', '0');
            len=hexCal(start,end);
            start=String.format("%6s",l2.get(i).getAddress()).replace(' ', '0');
            text=text+len+objcode;
            System.out.println("wasal");
            }
        }
        else if(objcode.length()==60){
            System.out.println(objcode);
            end=String.format("%6s",l2.get(i).getAddress()).replace(' ', '0');
            len=hexCal(start,end);
            start=String.format("%6s",l2.get(i).getAddress()).replace(' ', '0');
            text=text+len+objcode+"\r\nT";
            objcode=l2.get(i).getObjcode();
        }
    }
return text;
}
private String getEnd(){
    String text=String.format("%6s",l2.get(0).getAddress()).replace(' ', '0');;
    text="E"+text;
    return text;
}
}
