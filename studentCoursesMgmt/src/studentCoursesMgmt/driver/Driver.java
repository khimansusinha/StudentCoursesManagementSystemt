package studentCoursesMgmt.driver;




import java.io.IOException;

import studentCoursesMgmt.util.CourseDistribution;
import studentCoursesMgmt.util.ErrorLog;
import studentCoursesMgmt.util.FileProcessor;



/**
 * @author placeholder
 *
 */
public class Driver {
	public static void main(String[] args) {

		/*
		 * As the build.xml specifies the arguments as argX, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */

		

	     if (args.length != 5 || args[0].equals("${arg0}") || args[1].equals("${arg1}") || args[2].equals("${arg2}")
				|| args[3].equals("${arg3}") || args[4].equals("${arg4}")) {

			System.err.println("Error: Incorrect number of arguments. Program accepts 5 argumnets.");
			System.exit(0);
		}
		String argStr0 = args[0];
		String argStr1 = args[1];
		String argStr2 = args[2];
		String argStr3 = args[3];
		String argStr4 = args[4];

		//Error message inside error file
		ErrorLog errorFp = new ErrorLog(argStr4, "a");

		//course Preference file
		FileProcessor fpr = new FileProcessor(argStr0, "r");
		fpr.fanoutStudentObj(errorFp);

		//course info file
		FileProcessor fpr1 = new FileProcessor(argStr1, "r");
		fpr1.fanoutCourseObj(errorFp);

		//registration result file
		FileProcessor resFp = new FileProcessor(argStr2, "w");
		
		//conflict file
		FileProcessor conFp = new FileProcessor(argStr3, "w");

		CourseDistribution cd = new CourseDistribution();
		cd.distributeCourses(resFp, conFp, errorFp);
	}
}
