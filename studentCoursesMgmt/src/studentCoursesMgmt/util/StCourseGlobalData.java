package studentCoursesMgmt.util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import javafx.util.Pair;

public class StCourseGlobalData {
    static Queue<StudentPreference> queqeOfAllStudents = new LinkedList<StudentPreference>();
    static HashMap<String, Pair<Integer,Integer>> map = new HashMap<>();

    static void addStudent(StudentPreference stdPrfObj){
         //System.out.println(stdPrfObj.studentId +"StCourseGlobalData.java : Student ID");
        queqeOfAllStudents.add(stdPrfObj);
    }
    static void addCourseInfo(String cName, int vS, int cT){  
        //System.out.println(cName + vS + cT +"StCourseGlobalData.java : vS value");
        map.put(cName, new Pair<Integer, Integer>(vS,cT));
    }
    
    //For debugging purpose
    static void printQueue(){
        Queue<StudentPreference> qCopy = new LinkedList<StudentPreference>();
        qCopy = queqeOfAllStudents;
        while(!qCopy.isEmpty()){
            while(!qCopy.isEmpty()){
                StudentPreference sob = qCopy.remove();
                //System.out.println(sob.studentId+"  ::StCourseGlobalData.java : :Student ID \n");
                for(String it: sob.courseName){
                   // System.out.print(it+" ");
                }
                 //System.out.println("\n");
            }
        }

    }
    ////For debugging purpose
    static void printMap(){
        HashMap<String, Pair<Integer,Integer>> mapCopy = new HashMap<>();
        mapCopy = map;
        for (HashMap.Entry<String, Pair<Integer,Integer>> entry : mapCopy.entrySet()) {
            Pair<Integer,Integer> p1 = entry.getValue();
            //System.out.println(entry.getKey() + ":HashMap.Entry globaldata " + p1.getKey() + " $$ "+p1.getValue());
       }

    }

}
