/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sicassembler;

/**
 *
 * @author Mahmoud
 */
public class ListFile {
    
    private String address;
    private String opcode;
    private String label;
    private String operation;
    private String operand;
    
    public ListFile(){
        
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address.toUpperCase();
    }

    public String getOpcode() {
        return opcode;
    }

    public void setOpcode(String opcode) {
        this.opcode = opcode;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getOperand() {
        return operand;
    }

    public void setOperand(String operand) {
        this.operand = operand;
    }
    
    @Override
    public String toString()
    {String space1="";
    String space2="";
    String space3="";
    for(int i=1;i<=9-label.length();i++)
        space1=space1+" ";
    for(int i=1;i<=8-operation.length();i++)
        space2=space2+" ";
    for(int i=1;i<=15-operand.length();i++)
        space3=space3+" ";
    
        return label+space1+operation+space2+operand+space3+address;
    }
   
}
