package lab;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScoreManager {
    private static final String FILE_NAME = "scores.csv";

    public static void saveScore(String name, int score) {
        if (name == null || name.isBlank()) {
            name = "Player";
        }

        try (FileWriter fw = new FileWriter(FILE_NAME, true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            bw.write(name + "," + score);
            bw.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Score> loadScores() throws ScoreException {
        List<Score> scores = new ArrayList<>();

        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return scores;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    scores.add(new Score(parts[0], Integer.parseInt(parts[1])));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return scores;
    }
}
