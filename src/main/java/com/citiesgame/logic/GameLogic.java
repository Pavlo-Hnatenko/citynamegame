package com.citiesgame.logic;

import com.citiesgame.exception.IncorrectCityException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class GameLogic {

    private static final String START_CITY = "Kharkiv";
    private static final String[] CITIES_VALUES = new String[]{
            "Ankara", "Budapest", "Canberra", "Dhaka", "Edinburgh", "Freetown",
            "Gdansk", "Honiara", "Islamabad", "Juba", "Kyiv", "London",
            "Montevideo", "Niamey", "Oslo", "Podgorica", "Quito", "Roseau",
            "Santiago", "Thimphu", "Ulaanbaatar", "Vatican", "Warsaw", "Xining",
            "Yerevan", "Zanzibar"
    };
    private static final Set<String> CITIES =
            new HashSet<>(Arrays.asList(CITIES_VALUES));

    public static void startGame() {

        System.out.println("Let's start from the city: "
                + START_CITY + System.lineSeparator() + "Your turn!");

        while (true) {
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(System.in));
            String inputCity;

            try {
                inputCity = reader.readLine();

                if (isSuitableCity(START_CITY, inputCity)) {
                    System.out.println(inputCity + " is a good answer!");
                    systemAnswerCity(inputCity);
                    return;
                } else {
                    throw new IncorrectCityException("The city " + inputCity
                            + " that you entered is incorrect answer!" +
                            " Please, enter the correct city.");
                }

            } catch (IncorrectCityException | IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static boolean isSuitableCity(String previousCity,
                                          String currentCity) {

        return currentCity.regionMatches(true, 0, previousCity,
                previousCity.length() - 1, 1)
                && !previousCity.equalsIgnoreCase(currentCity);
    }

    private static void systemAnswerCity(String inputCity) {
        for (String city : CITIES) {
            if (isSuitableCity(inputCity, city)) {
                System.out.println("And my answer is... "
                        + System.lineSeparator() + city
                        + ". Thank you for playing!");
                return;
            }
        }
        System.out.println("Sorry, I don't have any suitable city in my " +
                "memory for the answer. You a winner!");
    }
}
