import java.util.Scanner;

public class quizapp {
    private static String[] questions = {
        "What is the capital of India?",
        "Which planet is known as the Largest Planet?",
        "What is the largest mammal in the world?"
    };
    private static String[][] options = {
        {"Banglore", "Chennai", "NewDelhi", "Mumbai"},
        {"Venus", "Jupiter", "Mars", "Saturn"},
        {"Giraffe", "Elephant", "Blue Whale", "Lion"}
    };
    private static int[] correctAnswers = {3, 2, 3}; // Indices of correct options

    private static int score = 0;
    private static int currentQuestionIndex = 0;
    private static boolean timerExpired = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Quiz Application");
        System.out.println("You have 10 seconds to answer each question.");

        Thread timerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                    timerExpired = true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        timerThread.start();

        while (currentQuestionIndex < questions.length) {
            showNextQuestion();

            if (timerExpired) {
                System.out.println("Time's up! Moving to the next question.");
                currentQuestionIndex++;
                timerExpired = false;
            } else {
                int userAnswer = scanner.nextInt();
                checkAnswer(userAnswer);
            }
        }

        endQuiz();
    }

    private static void showNextQuestion() {
        System.out.println("\nQuestion " + (currentQuestionIndex + 1) + ": " + questions[currentQuestionIndex]);
        for (int i = 0; i < options[currentQuestionIndex].length; i++) {
            System.out.println((i + 1) + ". " + options[currentQuestionIndex][i]);
        }

        System.out.print("Enter the number of your answer: ");
    }

    private static void checkAnswer(int userAnswer) {
        if (userAnswer == correctAnswers[currentQuestionIndex]) {
            System.out.println("Correct!");
            score++;
        } else {
            System.out.println("Incorrect.");
        }
        currentQuestionIndex++;
    }

    private static void endQuiz() {
        System.out.println("\nQuiz completed!");
        System.out.println("Your final score is: " + score + " out of " + questions.length);

        int correctCount = score;
        int incorrectCount = questions.length - score;
        System.out.println("Correct Answers: " + correctCount);
        System.out.println("Incorrect Answers: " + incorrectCount);
    }
}