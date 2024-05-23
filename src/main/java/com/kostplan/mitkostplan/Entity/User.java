package com.kostplan.mitkostplan.Entity;

import jakarta.persistence.*;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password;
    private byte sex;
    private Date dateBirth;
    private int heightCm;
    private int weightKg;
    private int bmr;
    private byte goal;
    private byte activity;
    private boolean subscribed;

    public User() {

    }

    public User(int id, String name, String email, String password, byte sex, Date dateBirth, int heightCm, int weightKg, byte goal) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.sex = sex;
        this.dateBirth = dateBirth;
        this.heightCm = heightCm;
        this.weightKg = weightKg;
        this.goal = goal;
        calculateBMR();
    }

    public User(int id, String name, String email, String password, byte sex, Date dateBirth, int heightCm, int weightKg, int bmr, byte goal, boolean subscribed) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.sex = sex;
        this.dateBirth = dateBirth;
        this.heightCm = heightCm;
        this.weightKg = weightKg;
        this.goal = goal;
        this.bmr = bmr;
        this.subscribed = subscribed;
        calculateBMR();
    }

    //calculateBMR
    public void calculateBMR(){
        double baseBMR = 0.0;
        if (getSex() == 0) {
            baseBMR = (10 * getWeightKg()) + (6.25 * getHeightCm()) - (5 * getAge() + 5);
        } else if (getSex() == 1) {
            baseBMR = (10 * getWeightKg()) + (6.25*getHeightCm()) - (5*getAge()) - 161;
        }
        bmr = (int) (baseBMR * getActivityMultiplier() + regulateWithGoal());
    }

    private double getActivityMultiplier(){
        switch (activity){
            case 0:
                return 1.2;
            case 1:
                return 1.5;
            case 2:
                return 1.7;
            case 3:
                return 1.9;
            case 4:
                return 2.4;
            default:
                return 1;
        }
    }

    private int regulateWithGoal(){
        switch (goal){
            case 0:
                return -500;
            case 1:
                return 500;
            case 2:
            default:
                return 0;
            case 3:
                return 300;
        }
    }

    // up stilling af angving informson  af User

    public int getAge() {
        LocalDate birthDate = getDateBirth().toLocalDate();
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte getSex() {
        return sex;
    }

    public void setSex(byte sex) {
        this.sex = sex;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public int getHeightCm() {
        return heightCm;
    }

    public void setHeightCm(int heightCm) {
        this.heightCm = heightCm;
    }

    public int getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(int weightKg) {
        this.weightKg = weightKg;
    }

    public double getBmr() {
        return bmr;
    }

    public void setBmr(int bmr) {
        this.bmr = bmr;
    }

    public byte getGoal() {
        return goal;
    }

    public void setGoal(byte goal) {
        this.goal = goal;
    }

    public byte getActivity() {
        return activity;
    }

    public void setActivity(byte activity) {
        this.activity = activity;
    }

    public boolean isSubscribed() {
        return subscribed;
    }

    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", dateBirth=" + dateBirth +
                ", heightCm=" + heightCm +
                ", weightKg=" + weightKg +
                ", bmr=" + bmr +
                ", goal=" + goal +
                ", subscribed=" + subscribed +
                '}';
    }
}


