

-----------------------------------------------------------------------
-----------------------------------------------------------------------


Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in studentCoursesMgmt/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

####Command: ant -buildfile studentCoursesMgmt/src/build.xml clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

####Command: ant -buildfile studentCoursesMgmt/src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

####Command: ant -buildfile studentCoursesMgmt/src/build.xml run -Darg0=coursePrefs.txt -Darg1=courseInfo.txt -Darg2=registration_results.txt -Darg3=regConflicts.txt -Darg4=errorLog.txt

## Replace <fileName.txt> with real file names. For example, if the files are available in the path,
## you can run it in the following manner:

Note: Arguments accept the absolute path of the files.

-----------------------------------------------------------------------
## Description:
To implement this program, I followed below steps:
1. At first i have decided the number of classes and its different attributes.

2. I have defined below classes:
  a)  StudentPreference.java: This class stores information about each students and their choices. I have used a string and an arraylist to store a student ID and his respective choices. 

  b)  FileProcessor.java: It is a generic class to read and write from a given file using file pointer and its read and write buffer. This class reads student preference information from coursePrefs.txt and parses input file and creates student preference object and stores this object inside a global static queue.  Similarly, it also reads and parses course information from courseInfo.txt and store courseinfo name as a key and available seat, course timing as a value inside a global static map.

  c)  StCourseGlobalData.java: This class stores a global static preference queue which stores object for all student preferences and a hashmap for the courseinfo.

  d)  CourseDistribution.java: This Class implements the core logic of selection of student and his respective choices based on FCFS algorithm. The registration_results.txt file prints the student Id, selected courses and satisfaction rate. The regConflicts.txt file prints the conflicts informations. This class implements two interfaces FileDisplayInterface.java and StdoutDisplayInterface.java.
  I have used CourseDistribution.java in place of Results.java, only for better readability.

  e)  StudentPrefInterface.java: It is used for the debugging purpose to verify if I have correctly read the coursePrefs.txt and correctly store student preference object inside the queue.

3. I have a generic file writer class so that all kinds of file read-write operations inside any file can happen by creating an instance of this class. Reading student preference, course info or writing the selection result or conflict result all activities on these files happens by creating an instance of this class.

4. I have also used a separate class for appending the error log message inside the error log file.

5. I have used the BufferedReader to read from a file and BUfferedWriter to write to the file, because I am using the BUfferedWriter so I am doing fflush() after doing each write inside the file. This makes our code more reliable because I am writing the select or conflict result of a student as soon as it is available to the disk to make it persistent quickly.

6.  I have implemented the distribution logic for  a number of students by using the FCFS algorithm.

7. I have handled the case when the conflict of timing happens, skip that course for a particular student and write this conflict information inside the conflict log file. To find the conflict I have a local HASHmap which stores information about student id as its key and the array list as value which contains a list of already selected timing for this student. I have used a HASHmap so that  the searching for a student id can happen in O(1) time to improve the performance of the code.

8. I have also implemented the corner case where, if by mistake a user provides multiple entries for the same student ID inside the course preference input file, the program considers only the first entry of the student id and its prefered course choices present inside the file and ignores the duplicate entry for the same student id.
If the user gives less than or greater than 5 arguements, then program throws an error and user needs to provide exactly 5 numbers of arguement.

9. I have implemented the logic to calculate satisfaction rate for each student and also the total average satisfaction rate of overall students.

10. I have used two global data structures inside a static class. I have used this static class because I needed only one instance of these data structures inside the whole program and can be easily accessed by other classes. I have defined a global HASHmap of course info to search the student information inside the set in O(1) time to improve the performance of the program.  This HASHmap stores the course name as key and its availability and course timing as value.I have also used one global queue which stores the student preference object for each student inside this queue in FCFS order. I have used a queue because the queue guarantees the same order  in which the request comes. Each student preference object present inside the queue has values like, student id as string and set of preferend student courses as a string of array list.

11. I have verified my program by providing different combinations of course preference for the students inside the course preference file and also by providing different combinations of seat availability and the course timings for different courses.

12. I have used two input files to read the student preference information and course information and three input files to print the selected course result, conflict result and error messages. I have opened the student preference and course information input files in read only mode. I have opened the result file and conflict files to open in write only mode. I have opened the error file to open the file in append mode. I have used the error file to open in append mode so that It can append errors for each run. It will be helpful in debugging.

13. I have defined a course preference interface also, so that in future if needed I can use it to print the selected result information inside the command line and it can also be used for the debugging purpose.

14. I have closed all the opened files by the program at the correct place so that the flush of the file can happen to the disk and the file handle can return to the OS gracefully.

15. I have used ANT to build and verify my program multiple times to test my program with different input values.









