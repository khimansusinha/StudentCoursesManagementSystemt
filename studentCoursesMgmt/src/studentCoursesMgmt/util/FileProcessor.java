package studentCoursesMgmt.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class FileProcessor {
	public File fp;
    public BufferedReader bf;
    public BufferedWriter wf, af;
    public String modeFp;
    BufferedWriter errFpObj;
    public FileProcessor(String fname,String mode){
        fp = new File(fname);
        modeFp = mode;
        try {
            if(mode == "r"){
               bf = new BufferedReader(new FileReader(fp));
            }else if(mode == "w"){
                try {
                    wf = new BufferedWriter(new FileWriter(fp,false));
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }else if(mode == "a"){
                 try {
                    af = new BufferedWriter(new FileWriter(fp,true));
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }else{
                System.out.println("Invalid File Open Mode has given in input");
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void fanoutCourseObj(ErrorLog efp){
        String str;
         try {
            while((str = bf.readLine()) != null){
                String courseName = "";
                StringTokenizer strTok = new StringTokenizer(str,":");
                Boolean flag = false;
                Boolean flag1 = false;
                int first = 0, second = 0;
                while(strTok.hasMoreTokens()){
                    if(flag == false){
                       courseName = strTok.nextToken();
                       flag = true;
                    }else if(flag1 == false){
                        first = Integer.parseInt(strTok.nextToken());
                        flag1 = true;
                    }else{
                        second = Integer.parseInt(strTok.nextToken());
                    }
                }
                StCourseGlobalData.addCourseInfo(courseName, first, second);
            }
            bf.close(); 
        } catch (IOException e) {
             efp.writeError( "Error in reading course info file\n");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    public void fanoutStudentObj(ErrorLog efp){
        String str;
        try {
            while((str = bf.readLine()) != null){
                String studentID = "";
                ArrayList<String> courseName = new ArrayList<>();
                StringTokenizer strTok = new StringTokenizer(str," ;");
                Boolean flag = false;
                while(strTok.hasMoreTokens()){
                    if(flag == false){
                       studentID = strTok.nextToken();
                       flag = true;
                    }else{
                        courseName.add(strTok.nextToken());
                    } 
                }
                StudentPreference stprf = new StudentPreference(studentID, courseName);
                StCourseGlobalData.addStudent(stprf);
            }
            bf.close(); 
        } catch (IOException e) {
            // TODO Auto-generated catch block
              efp.writeError("Error in reading course preference file\n");
            e.printStackTrace();
        }
    }
    
 }
