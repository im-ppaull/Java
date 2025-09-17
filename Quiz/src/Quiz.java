import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Quiz {
    protected int score;
    protected char grade;
    protected int length;

    public Quiz() {
        this.score = 0;

        File quizFile = new File("Quiz/src/quiz.json");
        StringBuilder jsonContent = new StringBuilder();

        try (Scanner reader = new Scanner(quizFile)) {
            while (reader.hasNextLine()) {
                jsonContent.append(reader.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occured.");
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Question>>() {}.getType();
        List<Question> quiz = gson.fromJson(jsonContent.toString(), listType);

        this.length = quiz.size();

        Collections.shuffle(quiz);

        for (Question question : quiz) {
            int id = 1;
            int choice;

            System.out.println(question.question);
            System.out.println();

            // Shuffle answers
            Collections.shuffle(question.answers);

            for (Answer answer : question.answers) {
                System.out.println(id + ") " + answer.answer);
                id++;
            }

            System.out.println();
            System.out.print("Your choice: ");
            choice = scanner.nextInt();

            // Check answers
            if (choice < 1 || choice > 4) {
                System.out.println("Invalid answer!");
                continue;
            }

            Answer answer = question.answers.get(choice - 1);
            if (answer.correct) {
                System.out.println("Correct!");
                score++;
            }
            else {
                System.out.printf("Incorrect! (%s is correct)", question.getCorrectAnswer());
                System.out.println();
            }

            System.out.println();
        }

        this.grade = grade();

        scanner.close();
    }

    private char grade() {
        if (score < 0 || score > length)
            return 'F';

        double percentage = (double) score / length * 100;

        if (percentage >= 90)
            return 'A';
        else if (percentage >= 80)
            return 'B';
        else if (percentage >= 70)
            return 'C';
        else if (percentage >= 60)
            return 'D';
        else
            return 'F';
    }
}