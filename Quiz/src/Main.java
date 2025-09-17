import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to quiz game!");
        System.out.println("1) Play");
        System.out.println("2) Add question");
        System.out.println("3) Remove question");

        System.out.print("I want to: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                Quiz quiz = new Quiz();
                System.out.printf("Your score: %s / %s (grade: %s)", quiz.score, quiz.length, quiz.grade);
            case 2:
                addQuestion();
            case 3:
                removeQuestion();
        }

        System.out.println();
        scanner.close();
    }

    private static void addQuestion() {
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Question> questions = new ArrayList<>();

        try (FileReader reader = new FileReader("Quiz/src/quiz.json")) {
            Type questionListType = new TypeToken<List<Question>>() {}.getType();
            questions = gson.fromJson(reader, questionListType);
            if (questions == null) {
                questions = new ArrayList<>();
            }
        } catch (FileNotFoundException e) {
            // File does not exists
            questions = new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Error while reading quiz.json: " + e.getMessage());
        }

        System.out.print("Question: ");
        String questionText = scanner.nextLine();

        List<Answer> answers = new ArrayList<>();
        System.out.println("Answers:");
        boolean correctSet = false;

        for (int i = 0; i < 4; ++i) {
            System.out.printf("%s. Answer: ", i + 1);
            String answerText = scanner.nextLine();

            boolean correct = false;

            if (!correctSet) {
                System.out.printf("Correct (true | false): ");
                correct = Boolean.parseBoolean(scanner.nextLine());
                correctSet = correct;
            }

            Answer answer = new Answer(answerText, correct);
            answers.add(answer);
        }

        Question question = new Question(questionText, answers);
        questions.add(question);

        try (FileWriter writer = new FileWriter("Quiz/src/quiz.json")) {
            gson.toJson(questions, writer);
            System.out.println("Question successfully added to quiz.json.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }

    private static void removeQuestion() {
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Question> questions = new ArrayList<>();

        try (FileReader reader = new FileReader("Quiz/src/quiz.json")) {
            Type questionListType = new TypeToken<List<Question>>() {}.getType();
            questions = gson.fromJson(reader, questionListType);
            if (questions == null) {
                questions = new ArrayList<>();
            }
        } catch (FileNotFoundException e) {
            // File does not exists
            questions = new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Error while reading quiz.json: " + e.getMessage());
        }

        if (questions.size() < 1) {
            System.out.println("No questions in the quiz!");
            return;
        }

        System.out.printf("Questions: [%s questions]\n", questions.size());

        // Variable for order
        int id = 1;

        for (Question question : questions) {
            System.out.printf("%s) %s - [%s]", id, question.question, question.getCorrectAnswer());

            id++;
            System.out.println();
        }

        int choice;

        System.out.println();
        System.out.print("Which question you want to remove? (type 0 to get back): ");
        choice = scanner.nextInt() - 1;

        if (choice <= 0)
            return;

        questions.remove(choice);

        try (FileWriter writer = new FileWriter("Quiz/src/quiz.json")) {
            gson.toJson(questions, writer);
            System.out.println("Question successfully removed from quiz.json.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }
}
