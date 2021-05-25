package com.example.medicalapp.users.pacienti;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class PrelucrareDate {
    /* Determina daca utilizatorul este activ/sedentar */
    static String ActivityLevel(float average_steps) {
        String result = "";
        if (average_steps < 5000) {
            result += "Sedentary";
        }
        if (average_steps >= 5000 && average_steps < 7500) {
            result += "Partially active";
        }
        if (average_steps >= 7500 && average_steps < 10000) {
            result += "A little more active";
        }
        if (average_steps >= 10000) {
            result += "Active";
        }
        return result;
    }

    public static float nrSteps(float average_steps) throws IOException {
        PrelucrareDate obj = new PrelucrareDate();
        BufferedReader csvReaderSteps = new BufferedReader(new FileReader("/data/data/com.example.medicalapp/files/pedometer.csv"));

        String rowSteps;
        int nr_days_steps = 0, omit = 2;

        // parsare fisier care contine date despre numarul de steps
        while ((rowSteps = csvReaderSteps.readLine()) != null) {
            String[] data = rowSteps.split(",");

            if (omit == 0) {
                float steps;
                try {
                    steps = Float.parseFloat(data[0]);
                } catch (NumberFormatException nfe) {
                    continue;
                }
                average_steps += steps;
                nr_days_steps += 1;
            } else {
                omit--;
            }

        }
        average_steps = average_steps / (float) nr_days_steps;

        return average_steps;

    }

    public static int nrDaysSteps(int nr_days_steps) throws IOException {
        PrelucrareDate obj = new PrelucrareDate();
        BufferedReader csvReaderSteps = new BufferedReader(new FileReader("/data/data/com.example.medicalapp/files/pedometer.csv"));

        String rowSteps;
        int omit = 2;

        while ((rowSteps = csvReaderSteps.readLine()) != null) {
            String[] data = rowSteps.split(",");

            if (omit == 0) {
                float steps;
                try {
                    steps = Float.parseFloat(data[0]);
                } catch (NumberFormatException nfe) {
                    continue;
                }

                nr_days_steps += 1;
            } else {
                omit--;
            }

        }
        return nr_days_steps;

    }

    // parsare fisier care contine date despre bataile inimii
    // omitm primele 2 linii
    public static float heartBeats(float average_pulsee) throws IOException {
        PrelucrareDate obj = new PrelucrareDate();
        BufferedReader csvReaderHRate = new BufferedReader(new FileReader("/data/data/com.example.medicalapp/files/heart_rate.csv"));

        int omit = 2;
        String rowHRate;

        int nr_days_pulse = 0;

        Vector<Date> dates = new Vector<Date>(); // stocam momentele in care are pulse abnormal

        while ((rowHRate = csvReaderHRate.readLine()) != null) {
            String[] data = rowHRate.split(",");
            if (omit == 0) {

                float pulse;
                try {
                    pulse = Float.parseFloat(data[data.length - 1]);
                } catch (NumberFormatException nfe) {
                    continue;
                }

                if (pulse < 60 || pulse > 100) {
                    Date moment = new Date(data[3], pulse);
                    dates.add(moment);
                }
                average_pulsee += pulse;
                nr_days_pulse += 1;
            } else {
                omit--;
            }
        }

        average_pulsee = average_pulsee / (float) nr_days_pulse;
        return average_pulsee;
    }

    public static int nrDaysPulse(int nr_days_pulse) throws IOException {
        PrelucrareDate obj = new PrelucrareDate();
        BufferedReader csvReaderHRate = new BufferedReader(new FileReader("/data/data/com.example.medicalapp/files/heart_rate.csv"));

        int omit = 2;
        String rowHRate;


        Vector<Date> dates = new Vector<Date>(); // stocam momentele in care are pulse abnormal

        while ((rowHRate = csvReaderHRate.readLine()) != null) {
            String[] data = rowHRate.split(",");
            if (omit == 0) {

                float pulse;
                try {
                    pulse = Float.parseFloat(data[data.length - 1]);
                } catch (NumberFormatException nfe) {
                    continue;
                }

                if (pulse < 60 || pulse > 100) {
                    Date moment = new Date(data[3], pulse);
                    dates.add(moment);
                }
                nr_days_pulse += 1;
            } else {
                omit--;
            }
        }
        return nr_days_pulse;
    }

    // parsare fisier care contine date despre caloriile consumate
    // omitm primele 2 linii
    public static float calories(float average_calories) throws IOException {
        PrelucrareDate obj = new PrelucrareDate();
        BufferedReader csvReaderCalories = new BufferedReader(new FileReader("/data/data/com.example.medicalapp/files/calories.csv"));

        int omit = 2;
        String rowCalories;

        int nr_days_calories = 0;
        while ((rowCalories = csvReaderCalories.readLine()) != null) {
            String[] data = rowCalories.split(",");
            if (omit == 0) {
                float rest_calorie, active_calorie;
                try {
                    rest_calorie = Float.parseFloat(data[4]);
                    active_calorie = Float.parseFloat(data[7]);
                } catch (NumberFormatException nfe) {
                    continue;
                }
                average_calories += rest_calorie;
                average_calories += active_calorie;
                nr_days_calories += 1;
            } else {
                omit--;
            }
        }

        average_calories = average_calories / (float) nr_days_calories;

        return average_calories;

    }

    public static int nrDaysCalories(int nr_days_calories) throws IOException {
        PrelucrareDate obj = new PrelucrareDate();
        BufferedReader csvReaderCalories = new BufferedReader(new FileReader("/data/data/com.example.medicalapp/files/calories.csv"));

        int omit = 2;
        String rowCalories;


        while ((rowCalories = csvReaderCalories.readLine()) != null) {
            String[] data = rowCalories.split(",");
            if (omit == 0) {
                float rest_calorie, active_calorie;
                try {
                    rest_calorie = Float.parseFloat(data[4]);
                    active_calorie = Float.parseFloat(data[7]);
                } catch (NumberFormatException nfe) {
                    continue;
                }
                nr_days_calories += 1;
            } else {
                omit--;
            }
        }
        return nr_days_calories;
    }

    public static float abnormal(float nr) throws IOException {
        PrelucrareDate obj = new PrelucrareDate();
        BufferedReader csvReaderHRate = new BufferedReader(new FileReader("/data/data/com.example.medicalapp/files/heart_rate.csv"));

        int omit = 2;
        String rowHRate;

        Vector<Date> dates = new Vector<Date>(); // stocam momentele in care are pulse abnormal
        Vector<Date> dates_total = new Vector<Date>();

        while ((rowHRate = csvReaderHRate.readLine()) != null) {
            String[] data = rowHRate.split(",");
            if (omit == 0) {

                float pulse;
                try {
                    pulse = Float.parseFloat(data[data.length - 1]);
                } catch (NumberFormatException nfe) {
                    continue;
                }

                if (pulse < 60 || pulse > 100) {
                    Date moment = new Date(data[3], pulse);
                    dates.add(moment);
                }

                Date moment2 = new Date(data[3], pulse);
                dates_total.add(moment2);

            } else {
                omit--;
            }
        }
        System.out.println("The moments when the pulse registered abnormal values: ");
        for (int i = 0; i < dates.size(); i++) {
            System.out.println(dates.get(i));
        }
        //for(int j = 0; j < dates_total.size(); j++){

        float y = dates_total.size();
        float x = dates.size();
        nr = x / y * 100;
        return nr;


    }
}
