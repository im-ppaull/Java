import java.util.List;

public class Question {
    protected String question;
    protected List<Answer> answers;

    public Question(String question, List<Answer> answers) {
        this.question = question;
        this.answers = answers;
    }

    public String getCorrectAnswer() {
        return answers.stream()
                    .filter(a -> a.correct)
                    .map(a -> a.answer)
                    .findFirst()
                    .orElse("No correct answer.");
    }
}

class Answer {
    protected String answer;
    protected boolean correct;

    public Answer(String answer, boolean correct) {
        this.answer = answer;
        this.correct = correct;
    }
}