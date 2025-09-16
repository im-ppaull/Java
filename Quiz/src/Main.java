public class Main {
    public static void main(String[] args) throws Exception {
        Quiz quiz = new Quiz();
        System.out.printf("Your score: %s / %s (grade: %s)", quiz.score, quiz.length, quiz.grade);
    }
}
