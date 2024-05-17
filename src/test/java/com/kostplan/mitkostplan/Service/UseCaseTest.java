package com.kostplan.mitkostplan.Service;

import com.kostplan.mitkostplan.Entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UseCaseTest {

    @Test
    void calculateBMRMale() {

        double bmr = (10*70) + (6.25*175) - (5 * 26) + 5;
        System.out.println(bmr);

        double bmrquiet = bmr*1.2-500;
        System.out.println(bmrquiet);
        double bmractive = bmr*1.7+300;
        System.out.println(bmractive);

    }

    @Test
    void calculateBMRFemale() {

        double bmr = (10*70) + (6.25*175) - (5 * 26) + 161;
        System.out.println(bmr);

        double bmrquiet = bmr*1.2-500;
        System.out.println(bmrquiet);
        double bmractive = bmr*1.7+300;
        System.out.println(bmractive);

    }
}
