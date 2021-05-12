import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        // Arguments should exist
        if (args.length == 0) {
            System.err.println("No Arguments!!! Please type in Command Arguments!");
        }

        FileReader input1 = new FileReader(args[0]);
        FileReader input2 = new FileReader(args[1]);

        try {

            JSONParser jsonParser = new JSONParser();

            Object obj1 = jsonParser.parse(input1);
            Object obj2 = jsonParser.parse(input2);

            JSONObject jsonObject1 = (JSONObject) obj1;
            JSONObject jsonObject2 = (JSONObject) obj2;

            // The first file is an array of JSON objects
            JSONArray data = (JSONArray) jsonObject1.get("data");

            // The second contains a JSON object with these properties
            Boolean useExperienceMultiplier = (Boolean) jsonObject2.get("useExperienceMultiplier");
            Long topPerformersThreshold = (Long) jsonObject2.get("topPerformersThreshold");
            Long periodLimit = (Long) jsonObject2.get("periodLimit");

            // Check log if all works as supposed to
            // UNCOMMENT to test
            /* System.out.println("Top performer's Threshold: " + topPerformersThreshold);
            System.out.println("Experience Multiplier: " + useExperienceMultiplier);
            System.out.println("Period Limit: " + periodLimit);
            */

            // We need 2 array lists to store the values for the CSV file
            List<String> jsonListNames = new ArrayList<>();
            List<Double> jsonListScore = new ArrayList<>();


            // This loop is the main driver of the program
            for (int i = 0; i < data.size(); i++) {
                // variables for score
                Double score;

                JSONObject theData = (JSONObject) data.get(i);

                // First JSON file is here
                // The formula to get the score when there is experience
                String name = (String) (theData.get("name"));
                Long totalSales = (Long) (theData.get("totalSales"));
                Long salesPeriod = (Long) (theData.get("salesPeriod"));
                Double experienceMultiplier = (Double) (theData.get("experienceMultiplier"));

                // use XP multiplier to form "score" in this block
                if (!useExperienceMultiplier && (salesPeriod <= periodLimit)) {
                    score = (totalSales / salesPeriod) * experienceMultiplier;
                } else score = Double.valueOf(totalSales / salesPeriod);


                jsonListNames.add(name);
                jsonListScore.add(score);

                // Second check
                // UNCOMMENT to test
                // System.out.println(jsonListNames.get(i) + ", " + score);
            }

            // Generating the CSV file
            PrintWriter pw = new PrintWriter(new FileWriter("generated-results.csv"));
            StringBuilder stringBuilder = new StringBuilder();
            // Header of the CSV file
            stringBuilder.append("Name");
            stringBuilder.append(", ");
            stringBuilder.append("Score");
            stringBuilder.append("\n");

            // This loop stores all of the names + the score respectively
            for (int i = 0; i < jsonListNames.size(); i++) {
                stringBuilder.append(jsonListNames.get(i));
                stringBuilder.append(", ");
                stringBuilder.append(jsonListScore.get(i));
                stringBuilder.append("\n");
            }

            pw.write(stringBuilder.toString());
            pw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}