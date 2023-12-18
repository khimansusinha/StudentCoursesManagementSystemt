# StudentCoursesManagementSystemt
Java Application to assign courses to students based on their preferences.
Project Description 

Assignment Goal: Develop a program, using Java, to assign courses to students based on their preferences.

There are 9 courses being offered. Each course (only one section per course) has the following information.
Capacity - The total number of students that can be registered for this course, expressed as a two digit integer in the range [10,99]
Class Timings - For simplicity, timings are two digit integers in the range [10,99]
Course Name (A,B,C,D,E,F,G,H or I)
The following information is associated with each student.
Student ID (a 3 digit integer in the range [100,999])
The following rules MUST be followed when registering students to courses.

You can use FCFS to process input files, though you are free to use a different algorithm.
A student cannot be registered to multiple courses that have the same class timing. Any student request that is declined due to a conflict, should be logged with a meaningful message written to regConflicts.txt.
A student can take a maximum of 3 courses. It is possible that some students will be assigned less than 3 courses as it depends on the input file. It is also possible that some students will be assigned 0 courses due to non availability of space.
All students are required to provide preferences for all 9 courses..
Total number of students can exceed the total capacity across all 9 courses.
If a course has been filled up, then any further registration requests for that course are rejected, and a meaningful message is written in errorLog.txt.
Each student has a satisfaction rating for each requested course. The order in which a student requests courses defines the satisfaction rating of that student for those courses given by (9 - course.index).
For example, let A,B,C,D,E,F,G,H,I be the requested courses of a student. Then,
satisfaction rating of student for course A = 9
satisfaction rating of student for course B = 8
satisfaction rating of student for course C = 7
satisfaction rating of student for course D = 6
...
satisfaction rating of student for course 1 = 1
Note: The SatisfactionRating for a student is the following:

(sum_of_satisfaction_scores/ 3)

The satisfaction rating determines the quality of the course registration algorithm.
INPUT FORMAT 

Your program should take two input files - coursePrefs.txt and courseInfo.txt. Note that the input files will be well formatted. 


coursePrefs.txt will have the following format (note the data is whitespace delimited), 

<student_id> <PREF_1> <PREF_2> <PREF_3> <PREF_4> <PREF_5> <PREF_6> <PREF_7> <PREF_8> <PREF_9>; 


courseInfo.txt will have the following format, 

<course_name>:<capacity>:<class_time> 

INPUT EXAMPLES 

coursePrefs.txt

111 D C A B G I H F G;
222 F E D C B A H I G;
333 D A F E I C H B G;

courseInfo.txt

A:30:11
B:40:22
C:50:33
D:60:11
E:70:77
F:35:44
G:45:33
H:55:77
I:25:66



OUTPUT 

Your program should write the registration results to an output file called registration_results.txt. 


registration_results.txt will have the following format (Note that AverageSatisfactionRating is the average of all the individual satisfaction ratings)

<student1_id>:<course_1>,<course_2>,<course_3>::SatisfactionRating=<value>
<student2_id>:<course_1>,<course_2>,<course_3>::SatisfactionRating=<value>
<student3_id>:<course_1>,<course_2>,<course_3>::SatisfactionRating=<value>
...
AverageSatisfactionRating=<value>
