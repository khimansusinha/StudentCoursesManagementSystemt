package studentCoursesMgmt.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ErrorLog {
    public File fp;
    public BufferedWriter af;
   
    public ErrorLog(String fname,String mode){
        fp = new File(fname);
            if(mode == "a"){
                try {
                    if(!fp.exists() || (fp.length() == 0)){
                        af = new BufferedWriter(new FileWriter(fp,false));
                        writeError("Error Message::\n");
                        af.close();
                    }
                    af = new BufferedWriter(new FileWriter(fp,true));
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }else{
                System.out.println("Invalid File Open Mode has given in input");
            }
    }

    public void writeError(String msg){
        try {
            af.write(msg);
            af.flush();
        } catch (IOException e) {
           // TODO Auto-generated catch block
            e.printStackTrace();
        }
     }
    
}
