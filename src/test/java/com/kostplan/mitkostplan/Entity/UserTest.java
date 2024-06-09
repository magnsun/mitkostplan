package com.kostplan.mitkostplan.Entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.sql.Date;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private User user;

    /*
    Vi laver en @BeforeEach som gør at hver gang vi skal køre en test for en user skal den user altid have den data.
     */

    @BeforeEach
    void setUp(){


        user = new User ();


        user.setSex((byte) 0);
        user.setDateBirth(Date.valueOf("2000-01-01"));
        user.setHeightCm(186);
        user.setWeightKg(75);
//        user.setGoal((byte)2);
//        user.setActivity((byte)3);

    }
    /*
    Laver den første test, ved at beregne BMR, den tager de informationer der lavet af vores user,
    De bliver udregnet i koden, resultatet de så får skal så give det samme som, hvis vi kørte programmet i dens helhed
    Delta er sat til 1 og 2 da værdierne, normalt er komma tal
     */
    @Test
    void testCalulateBMRMaleQuiet() {

        user.setActivity((byte) 0);
        double BMRcalulations = (10 * 75) + (6.25 * 186) - (5 * 24) + 5;
        BMRcalulations *= 1.2;
        double BMRresult = user.calculateBMR();
        assertEquals(BMRcalulations, BMRresult, 1);
    }

        @Test
    void testCalulateBMRMaleLight() {

            user.setActivity(( byte)1);
            double BMRcalulations = (10 * 75) + (6.25 * 186) - (5 * 24) + 5;
            BMRcalulations *= 1.5;
            double BMRresult = user.calculateBMR();
            assertEquals(BMRcalulations, BMRresult, 1);
        }


    @Test
    void testCalulateBMRMaleMedium(){

        user.setActivity((byte) 2);
        double BMRcalulations = (10 * 75) + (6.25 * 186) - (5 * 24) + 5;
        BMRcalulations *= 1.7;
        double BMRresult = user.calculateBMR();
        assertEquals(BMRcalulations, BMRresult, 1);
    }




        @Test
    void testCalulateBMRMaleActive(){

        user.setActivity((byte)3);
        double BMRcalulations = (10 * 75) + (6.25 * 186) - (5 * 24) + 5;
        BMRcalulations*=1.9;
        double BMRresult = user.calculateBMR();
        assertEquals(BMRcalulations, BMRresult, 1);


    }
    @Test
    void testCalulateBMRMaleElite() {

        user.setActivity((byte) 4);
        double BMRcalulations = (10 * 75) + (6.25 * 186) - (5 * 24) + 5;
        BMRcalulations *= 2.4;
        double BMRresult = user.calculateBMR();
        assertEquals(BMRcalulations, BMRresult, 1);
    }

    @Test
    void testCalulateBMRMaleDefault(){

        user.setActivity((byte) 5);
        double BMRcalulations = (10 * 75) + (6.25 * 186) - (5 * 24) + 5;
        BMRcalulations *= 1.0;
        double BMRresult = user.calculateBMR();
        assertEquals(BMRcalulations, BMRresult, 1);
    }

    /*
    Gør det samme som den ovenover, men vi tilføjer setGoal til yderliger udregnning
     */
    @Test
    void testGoalLoseWeight() {
        user.setGoal((byte) 0);
        user.setActivity((byte) 5);
        double BMRcalulations = (10 * 75) + (6.25 * 186) - (5 * 24) + 5;
        BMRcalulations *= 1;
        double BMRadjust = BMRcalulations -500;
        double BMRresult = user.adjustCaloriesForGoal();
        assertEquals(BMRadjust, BMRresult, 1);
    }

    @Test
    void testGoalGainWeight() {
        user.setGoal((byte) 1);
        user.setActivity((byte) 5);
        double BMRcalulations = (10 * 75) + (6.25 * 186) - (5 * 24) + 5;
        BMRcalulations *= 1;
        double BMRadjust = BMRcalulations + 500;
        double BMRresult = user.adjustCaloriesForGoal();
        assertEquals(BMRadjust, BMRresult, 1);
    }

        @Test
    void testGoalKeep(){
        user.setGoal((byte)2);
        user.setActivity((byte)5);
        double BMRcalulations = (10 * 75) + (6.25 * 186) - (5 * 24) + 5;
        BMRcalulations *=1;
        double BMRadjust = BMRcalulations + 0;
        double BMRresult = user.adjustCaloriesForGoal();
        assertEquals(BMRadjust, BMRresult, 1);
    }
    @Test
    void testGoalGainMuscle() {
        user.setGoal((byte) 3);
        user.setActivity((byte) 5);
        double BMRcalulations = (10 * 75) + (6.25 * 186) - (5 * 24) + 5;
        BMRcalulations *= 1;
        double BMRadjust = BMRcalulations + 300;
        double BMRresult = user.adjustCaloriesForGoal();
        assertEquals(BMRadjust, BMRresult, 1);
    }


    @Test
    void adjustMeals(){
        user.setGoal((byte) 1);
        user.setActivity((byte) 4);

        double mealCaloriesTest = user.adjustCaloriesForGoal();
        double breakfast = mealCaloriesTest*0.4;
        double lunch= mealCaloriesTest*0.3;
        double dinner = mealCaloriesTest*0.3;

        Map<Byte, Double> mealCalories = user.splitDailyCalories();


        assertEquals(breakfast, mealCalories.get((byte) 0), 2);
        assertEquals(lunch,mealCalories.get((byte) 1 ), 2);
        assertEquals(dinner,mealCalories.get((byte)2), 2);

    }



//    @Test
//    void calculateBMRMale() {
//
//    double bmr = (10*user.getWeightKg()) + (6.25*user.getHeightCm()) - (5 * user.getAge()) + 5;
//    System.out.println(bmr);
//
//    double bmrQuiet = bmr*1.2-500;
//    System.out.println(bmrQuiet);
//    double bmrActive = bmr*1.7+300;
//    System.out.println(bmrActive);
//
//    double bmrTest = (10*75) + (6.25*186) - (5 * 24) + 5;
//    System.out.println(bmr);
//
//    double bmrQuietTest = bmrTest*1.2-500;
//    System.out.println(bmrQuietTest);
//    double bmrActiveTest = bmrTest*1.7+300;
//    System.out.println(bmrActiveTest);
//
//    assertEquals(bmrTest,bmr);
//
//    assertEquals(bmrQuietTest, bmrQuiet);
//    assertEquals(bmrActiveTest, bmrActive);
//
//
//}
//
//    @Test
//    void calculateBMRFemale() {
//
//        User user = new User();
//        user.setName("Lise");
//        user.setEmail("Lise@mail.dk");
//        user.setPassword("1234");
//
//        user.setSex((byte)1);
//        user.setDateBirth(Date.valueOf("2000-01-01"));
//        user.setHeightCm(175);
//        user.setWeightKg(70);
//        user.setGoal((byte) 2);
//
//        double bmr = (10*user.getWeightKg())+(6.25*user.getHeightCm())-(5*user.getAge()) - 161;
//        System.out.println(bmr);
//
//        double bmrQuiet = bmr*1.2-500;
//        System.out.println(bmrQuiet);
//        double bmrActive = bmr*1.7+300;
//        System.out.println(bmrActive);
//
//        double bmrTest = (10*70) + (6.25*175) - (5 * 24) - 161;
//        System.out.println(bmrTest);
//
//        double bmrTestQuiet = bmrTest*1.2-500;
//        System.out.println(bmrTestQuiet);
//        double bmrTestActive = bmrTest*1.7+300;
//        System.out.println(bmrTestActive);
//
//        assertEquals(bmrTest,bmr);
//
//        assertEquals(bmrTestQuiet, bmrQuiet);
//        assertEquals(bmrTestActive, bmrActive);
//
//
//    }

}