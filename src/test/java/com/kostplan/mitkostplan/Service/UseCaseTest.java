package com.kostplan.mitkostplan.Service;

import com.kostplan.mitkostplan.Entity.User;
import org.junit.jupiter.api.Test;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class UseCaseTest {

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

        double bmrTest = (10*75) + (6.25*186) - (5 * 26) + 5;
        System.out.println(bmr);

        double bmrQuietTest = bmrTest*1.2-500;
        System.out.println(bmrQuietTest);
        double bmrActiveTest = bmrTest*1.7+300;
        System.out.println(bmrActiveTest);

    }

    @Test
    void calculateBMRFemale() {

        double bmr = (10*70) + (6.25*175) - (5 * 26) - 161;
        System.out.println(bmr);

        double bmrquiet = bmr*1.2-500;
        System.out.println(bmrquiet);
        double bmractive = bmr*1.7+300;
        System.out.println(bmractive);

    }
    @Test
    void activityLevel(){



    }

}