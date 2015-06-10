package com.wat.pum.usosmobile;

/**
 * Created by Pawel on 2015-05-31.
 *
 * This class is a container for information about grades
 */
public class GradesClass {
    /* subjectName - course name, subjectPart1Name - eg. lecture, subjectPart1Grade- ge grade for lecture */
    String subjectName;
    String subjectUnit1Name;
    String subjectUnit1Grade;
    String subjectUnit2Name;
    String subjectUnit2Grade;
    String subjectUnit3Name;
    String subjectUnit3Grade;
    String subjectUnit4Name;
    String subjectUnit4Grade;
    String subjectUnit5Name;
    String subjectUnit5Grade;

    // constructors
    public GradesClass(){
        this.subjectName = subjectName;
        this.subjectUnit1Name= "";
        this.subjectUnit1Grade= "";
        this.subjectUnit2Name= "";
        this.subjectUnit2Grade= "";
        this.subjectUnit3Name= "";
        this.subjectUnit3Grade= "";
        this.subjectUnit4Name= "";
        this.subjectUnit4Grade= "";
        this.subjectUnit5Name = "";
        this.subjectUnit5Grade = "";
    }

    public GradesClass(String subjectName, String subjectUnit1Name, String subjectUnit1Grade){
        this.subjectName = subjectName;
        this.subjectUnit1Name= subjectUnit1Name;
        this.subjectUnit1Grade= subjectUnit1Grade;
        this.subjectUnit2Name= "";
        this.subjectUnit2Grade= "";
        this.subjectUnit3Name= "";
        this.subjectUnit3Grade= "";
        this.subjectUnit4Name= "";
        this.subjectUnit4Grade= "";
        this.subjectUnit5Name = "";
        this.subjectUnit5Grade = "";


    }

    public GradesClass(String subjectName, String subjectUnit1Name, String subjectUnit1Grade, String subjectUnit2Name, String subjectUnit2Grade){
        this.subjectName = subjectName;
        this.subjectUnit1Name= subjectUnit1Name;
        this.subjectUnit1Grade= subjectUnit1Grade;
        this.subjectUnit2Name= subjectUnit2Name;
        this.subjectUnit2Grade= subjectUnit2Grade;
        this.subjectUnit3Name= "";
        this.subjectUnit3Grade= "";
        this.subjectUnit4Name= "";
        this.subjectUnit4Grade= "";
        this.subjectUnit5Name = "";
        this.subjectUnit5Grade = "";


    }


    public GradesClass(String subjectName, String subjectUnit1Name, String subjectUnit1Grade, String subjectUnit2Name, String subjectUnit2Grade, String subjectUnit3Name,
                       String subjectUnit3Grade) {
        this.subjectName = subjectName;
        this.subjectUnit1Name= subjectUnit1Name;
        this.subjectUnit1Grade= subjectUnit1Grade;
        this.subjectUnit2Name= subjectUnit2Name;
        this.subjectUnit2Grade= subjectUnit2Grade;
        this.subjectUnit3Name= subjectUnit3Name;
        this.subjectUnit3Grade= subjectUnit3Grade;
        this.subjectUnit4Name= "";
        this.subjectUnit4Grade= "";
        this.subjectUnit5Name = "";
        this.subjectUnit5Grade = "";

    }

    public GradesClass(String subjectName, String subjectUnit1Name, String subjectUnit1Grade, String subjectUnit2Name, String subjectUnit2Grade, String subjectUnit3Name,
                       String subjectUnit3Grade, String subjectUnit4Name, String subjectUnit4Grade) {
        this.subjectName = subjectName;
        this.subjectUnit1Name= subjectUnit1Name;
        this.subjectUnit1Grade= subjectUnit1Grade;
        this.subjectUnit2Name= subjectUnit2Name;
        this.subjectUnit2Grade= subjectUnit2Grade;
        this.subjectUnit3Name= subjectUnit3Name;
        this.subjectUnit3Grade= subjectUnit3Grade;
        this.subjectUnit4Name= subjectUnit4Name;
        this.subjectUnit4Grade= subjectUnit4Grade;
        this.subjectUnit5Name = "";
        this.subjectUnit5Grade = "";
    }

    public GradesClass(String subjectName, String subjectUnit1Name, String subjectUnit1Grade, String subjectUnit2Name, String subjectUnit2Grade, String subjectUnit3Name,
                       String subjectUnit3Grade, String subjectUnit4Name, String subjectUnit4Grade, String subjectUnit5Name, String subjectUnit5Grade) {
        this.subjectName = subjectName;
        this.subjectUnit1Name= subjectUnit1Name;
        this.subjectUnit1Grade= subjectUnit1Grade;
        this.subjectUnit2Name= subjectUnit2Name;
        this.subjectUnit2Grade= subjectUnit2Grade;
        this.subjectUnit3Name= subjectUnit3Name;
        this.subjectUnit3Grade= subjectUnit3Grade;
        this.subjectUnit4Name= subjectUnit4Name;
        this.subjectUnit4Grade= subjectUnit4Grade;
        this.subjectUnit5Name = subjectUnit5Name;
        this.subjectUnit5Grade = subjectUnit5Grade;

    }

}
