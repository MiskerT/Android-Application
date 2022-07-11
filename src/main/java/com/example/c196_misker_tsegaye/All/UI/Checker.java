package com.example.c196_misker_tsegaye.All.UI;

import com.example.c196_misker_tsegaye.All.Entities.AssessmentEntity;
import com.example.c196_misker_tsegaye.All.Entities.CourseEntity;
import com.example.c196_misker_tsegaye.All.Entities.NoteEntity;
import com.example.c196_misker_tsegaye.All.Entities.TermEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Checker {
    TermEntity term;
    CourseEntity course;
    AssessmentEntity assessment;
    NoteEntity note;
    boolean valid;
    boolean valid2;
    boolean valid3;
    boolean enter;

    Checker(TermEntity term){
        this.term = term;
        valid = isDate(term.getStartDate()) && isDate(term.getEndDate());
        if(!term.getTermName().toString().isEmpty() &&  valid){
            enter = true;
        }
        else{
            enter = false;
        }
    }
    Checker(CourseEntity course){
        this.course = course;
        valid = isDate(course.getStartDate()) && isDate(course.getEndDate());
        valid2 = isEmail(course.getEmail());
        valid3 =course.getCourseName().toString().isEmpty() && course.getInstName().toString().isEmpty()
                && course.getStatus().toString().isEmpty() && course.getPhone().toString().isEmpty();
        if(valid && valid2 && !valid3){
            enter = true;
        }else{
            enter = false;
        }
    }
    Checker(AssessmentEntity assessment){
        this.assessment = assessment;
        valid = isDate(assessment.getDueDate());
        valid2 = assessment.getAssessmentName().toString().isEmpty();
        valid3 = assessment.getType().toString().isEmpty();
        if(valid && !valid2 && !valid3){
            enter = true;
        }else{
            enter = false;
        }
    }
    Checker(NoteEntity note){
        this.note = note;
        valid = note.getNoteName().toString().isEmpty();
        valid2 = note.getNoteText().toString().isEmpty();
        if(!valid && !valid2){
            enter = true;
        }else{
            enter = false;
        }
    }

    public boolean isDate(String date){
        SimpleDateFormat form = new SimpleDateFormat("mm/dd/yyyy");
        try {
            form.parse(date);
            return true;
        }
        catch(ParseException e){
            return false;
        }
    }

    public boolean isEmail(String email){
        String emailPat = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailPat);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
