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
            fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            
            bw.close();

        } catch (IOException ex) {
            Logger.getLogger(SicAssembler.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }

}
