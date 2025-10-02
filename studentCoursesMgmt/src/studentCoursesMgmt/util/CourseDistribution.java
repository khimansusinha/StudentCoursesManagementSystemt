package studentCoursesMgmt.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.util.Pair;

public class CourseDistribution implements FileDisplayInterface,StdoutDisplayInterface{
    HashMap<String, ArrayList<Integer>> mapForStIDSelectedCT = new HashMap<>();
    public void distributeCourses(FileProcessor resultFp, FileProcessor conflictFp, ErrorLog efp){
       // System.out.println("studentId::"+"class CourseDistribution");
        int sumofAllStudentSatisfactionRatio = 0;
        int totalNoOfStudents = StCourseGlobalData.queqeOfAllStudents.size();
        while(!(StCourseGlobalData.queqeOfAllStudents).isEmpty()){
            StudentPreference stdpref = (StCourseGlobalData.queqeOfAllStudents).remove();
            int cT = 0,sR = 0;
            int satisfactionCounterSum = 0;
            int satisfactionIndexValue = 9;
            int totalSelectedCourseCountOfSt = 0;
            String selectedCourseListForResultPrint ="";
            if((mapForStIDSelectedCT).containsKey(stdpref.studentId)){
                efp.writeError("Duplicate Student Id "+stdpref.studentId +" found, skipping it\n");
                //System.out.println(stdpref.studentId +"studentId::"+"class CourseDistribution");
                continue;
            }
            for(String it: stdpref.courseName){
                //System.out.print(it+" ");
                if(totalSelectedCourseCountOfSt == 3){
                    break;
                } 
                if(checkAvailability(stdpref.studentId, it, cT, sR, resultFp, conflictFp)){
                    satisfactionCounterSum = satisfactionCounterSum + satisfactionIndexValue;
                    totalSelectedCourseCountOfSt++;
                    if(totalSelectedCourseCountOfSt == 3)
                       selectedCourseListForResultPrint = selectedCourseListForResultPrint + it + "::";
                    else 
                       selectedCourseListForResultPrint = selectedCourseListForResultPrint + it + ",";
                 }
                 satisfactionIndexValue--;
            }
            int satisfactionRatio = satisfactionCounterSum/3;
            writeInFP(resultFp, ""+stdpref.studentId +":" +selectedCourseListForResultPrint+ 
            "Satisfaction Rating = "+satisfactionRatio+" \n");
            sumofAllStudentSatisfactionRatio = sumofAllStudentSatisfactionRatio + satisfactionRatio;
        }

        int avgOfsatisfactionRatioAllSt = sumofAllStudentSatisfactionRatio/totalNoOfStudents;
        writeInFP(resultFp,"AverageSatisfactionRating = "+avgOfsatisfactionRatioAllSt+"\n");  
        try {
            resultFp.wf.close();
            conflictFp.wf.close();
        }catch (IOException e) {

            efp.writeError("Error in course distribution during closing of result and conflict file\n");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            try {
               efp.af.close();
            }catch (IOException e) {
            // TODO Auto-generated catch block
               e.printStackTrace();
            }
        }
    }

    boolean checkAvailability(String studentId, String courseN, int classTiming, 
    int satisfacRatio,FileProcessor resultFp, FileProcessor conflictFp){
        boolean ret = true;

        if((StCourseGlobalData.map).containsKey(courseN)){
            int vacantSeat =((StCourseGlobalData.map).get(courseN)).getKey();
            if(vacantSeat == 0){
                return false;
            }
            int cT = ((StCourseGlobalData.map).get(courseN)).getValue();
            if((mapForStIDSelectedCT).containsKey(studentId)){
                ArrayList<Integer> selectedCT = mapForStIDSelectedCT.get(studentId);
                for(int it: selectedCT){
                    if(cT == it){
                        //printStdOut("Conflict in time"+ "CourseDistribution.java");
                        writeInFP(conflictFp, "Conflict in time for StudentID: " +studentId 
                        +" for class timing: "+cT +" For Course " +courseN +" \n");
                        return false;
                    }
                }
              //  printStdOut("Selected Student= "+studentId +" classTiming = "+cT+
                //" courseN = " +courseN+"CourseDistribution.java");
                vacantSeat--;
                StCourseGlobalData.map.put(courseN, new Pair<Integer, Integer>(vacantSeat,cT));
                selectedCT.add(cT);
                mapForStIDSelectedCT.put(studentId, selectedCT);
            }else{
                ArrayList<Integer> a1 = new ArrayList<>();
                a1.add(cT);
                mapForStIDSelectedCT.put(studentId, a1);
                //printStdOut("Else part Selected Student= "+studentId +" classTiming = "+cT+
                //" courseN = " +courseN+"CourseDistribution.java");
            }
        }
        return ret;
    }
    
    @Override
    public void printStdOut(String msg) {
       System.out.println(msg);
    }

    @Override
    public void writeInFP(FileProcessor fp, String msg) {
        try {
            if(fp.modeFp == "w"){
                fp.wf.write(msg);
            }else if(fp.modeFp == "a"){
                 fp.af.write(msg);
            } 
            fp.wf.flush();
        } catch (IOException e) {
           // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
