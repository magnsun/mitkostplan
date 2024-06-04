package com.kostplan.mitkostplan.Service;

import com.kostplan.mitkostplan.Entity.User;
import org.junit.jupiter.api.Test;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class UseCaseTest {

    /* Her laver vi en test som, so beregner en brugers BMR, vi starter med at sætte en bruger op, men nogle givet data
    * derefter udregner vi det med den algoritme givet til os af minkostplan og derefter laver vi et kontrol resultat */

    @Test
    void calculateBMRMale() {

        User user = new User ();
        user.setName("Hans");
        user.setEmail("hans@mail.dk");
        user.setPassword("1230");

        user.setSex((byte) 1);
        user.setDateBirth(Date.valueOf("2000-01-01"));
        user.setHeightCm(186);
        user.setWeightKg(75);
        user.setGoal((byte)2);

        double bmr = (10*user.getWeightKg()) + (6.25*user.getHeightCm()) - (5 * user.getAge()) + 5;
        System.out.println(bmr);

        double bmrQuiet = bmr*1.2-500;
        System.out.println(bmrQuiet);
        double bmrActive = bmr*1.7+300;
        System.out.println(bmrActive);

        double bmrTest = (10*75) + (6.25*186) - (5 * 24) + 5;
        System.out.println(bmr);

        double bmrQuietTest = bmrTest*1.2-500;
        System.out.println(bmrQuietTest);
        double bmrActiveTest = bmrTest*1.7+300;
        System.out.println(bmrActiveTest);

        assertEquals(bmrTest,bmr);

        assertEquals(bmrQuietTest, bmrQuiet);
        assertEquals(bmrActiveTest, bmrActive);


    }

    @Test
    void calculateBMRFemale() {

        User user = new User();
        user.setName("Lise");
        user.setEmail("Lise@mail.dk");
        user.setPassword("1234");

        user.setSex((byte)0);
        user.setDateBirth(Date.valueOf("2000-01-01"));
        user.setHeightCm(175);
        user.setWeightKg(70);
        user.setGoal((byte) 2);

        double bmr = (10*user.getWeightKg())+(6.25*user.getHeightCm())-(5*user.getAge()) - 161;
        System.out.println(bmr);

        double bmrQuiet = bmr*1.2-500;
        System.out.println(bmrQuiet);
        double bmrActive = bmr*1.7+300;
        System.out.println(bmrActive);

        double bmrTest = (10*70) + (6.25*175) - (5 * 24) - 161;
        System.out.println(bmrTest);

        double bmrTestQuiet = bmrTest*1.2-500;
        System.out.println(bmrTestQuiet);
        double bmrTestActive = bmrTest*1.7+300;
        System.out.println(bmrTestActive);

        assertEquals(bmrTest,bmr);

        assertEquals(bmrTestQuiet, bmrQuiet);
        assertEquals(bmrTestActive, bmrActive);


    }
    @Test
    void activityLevel(){



    }

}
