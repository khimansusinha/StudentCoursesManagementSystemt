package studentCoursesMgmt.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class StudentPreference implements StudentPrefInterface{
    String studentId;
    ArrayList<String> courseName = new ArrayList<String>();
    static Queue<StudentPreference> queqeOfAllStudents = new LinkedList<StudentPreference>();
    
    StudentPreference(String stId,  ArrayList<String> cName){
        studentId = stId;
        courseName = cName;
    }
   void addStudent(StudentPreference stdPrfObj){
        queqeOfAllStudents.add(stdPrfObj);
    }
//For debugging purpose   
    @Override
    public void printStudentPreference() {
       Queue<StudentPreference> qcopy = new LinkedList<StudentPreference>();
       qcopy = queqeOfAllStudents;

       while(!qcopy.isEmpty()){
            StudentPreference sob = qcopy.remove();
            System.out.println(sob.studentId+" :Student ID \n");
            for(String it: sob.courseName){
                System.out.print(it+" ");
            }
            System.out.println("\n");
        }
    }
}
