
package sicassembler;


public class ListFile {
    
    private String address;
    private String objcode="      ";
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

    public String getObjcode() {
        return objcode;
    }

    public void setObjcode(String objcode) {
        this.objcode = objcode;
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
     String space34="";
        for(int i=1;i<=6-objcode.length();i++)
        space34=space34+" ";
String lollipop="";
    if(objcode.length()>6)
    {int l=(int)(objcode.length()/6.0-1);
    int k=1;
    
       lollipop=( address+"  "+objcode.substring(0, 6)+"  "+label+space1+operation+space2+operand+"\n");
        while(k<l)
        
        {lollipop=lollipop+("      "+objcode.substring(k*6, (k*6)+6)+"\n"); 
        
        
      
        k++;
            
            
        
       
    }
       
        lollipop=lollipop+("      "+objcode.substring(k*6));
    }
    else
    { 
          lollipop=( address+"  "+objcode+space34+"  "+label+space1+operation+space2+operand);
    }
      
   
   return lollipop;
    
    }
}



