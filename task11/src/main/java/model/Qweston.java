package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class Qweston {
    private StringProperty question;
    private ArrayList<StringProperty> answergood;
    private  ArrayList<StringProperty> badanswer;

    public Qweston(String qw){
        question=new SimpleStringProperty(qw);
        answergood= new ArrayList<>();
        badanswer= new ArrayList<>();
    }

    public int addTrue(String s){
        answergood.add(new SimpleStringProperty(s));
        return answergood.size();
    }

    public int addFalse(String s){
        badanswer.add(new SimpleStringProperty(s));
        return badanswer.size();
    }

    public void SetQuest(String s){
        question=new SimpleStringProperty(s);
    }

    public ArrayList<StringProperty> getAnswergood() {
        return answergood;
    }

    public void setAnswergood(ArrayList<StringProperty> answergood) {
        this.answergood = answergood;
    }

    public ArrayList<StringProperty> getBadanswer() {
        return badanswer;
    }

    public void setBadanswer(ArrayList<StringProperty> badanswer) {
        this.badanswer = badanswer;
    }

    public String getQuestion() {
        return question.get();
    }

    public StringProperty questionProperty() {
        return question;
    }

}
