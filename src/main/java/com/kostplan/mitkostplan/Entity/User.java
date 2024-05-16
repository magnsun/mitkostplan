package com.kostplan.mitkostplan.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private double bmr;
    private byte goal;
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
    }

    public User(int id, String name, String email, String password, byte sex, Date dateBirth, int heightCm, int weightKg, double bmr, byte goal, boolean subscribed) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.sex = sex;
        this.dateBirth = dateBirth;
        this.heightCm = heightCm;
        this.weightKg = weightKg;
        this.bmr = bmr;
        this.goal = goal;
        this.subscribed = subscribed;
    }

    public int getAge() {
        return LocalDate.now().getYear() - getDateBirth().getYear() - 1900;
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

    public void setBmr(double bmr) {
        this.bmr = bmr;
    }

    public byte getGoal() {
        return goal;
    }

    public void setGoal(byte goal) {
        this.goal = goal;
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


