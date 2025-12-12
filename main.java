import java.io.*;
import java.util.*;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            
          System.out.println("What operation do you want to perform?");
         System.out.println(" 1 = Addition");
         System.out.println(" 2 = Subtraction");
          System.out.println(" 3 = Multiplication");
           int operation = scanner.nextInt();  
          MathQuiz quiz = new MathQuiz(operation);  

          int correctAnswers = 0;
          int totalQuestions = 5;
           
           for (int i = 0; i < totalQuestions; i++) {
                try {
                   
                 System.out.println(quiz.getQuestion(i));  
                int userAnswer = scanner.nextInt();      
                quiz.scoreIt(i, userAnswer);             
                } catch (InputMismatchException e) {
                 System.out.println("Invalid input. Please input a valid number.");
                scanner.nextLine(); 
                 i--; 
                } catch (Exception e) {
                 System.out.println("Some error occurred while processing the question: " + e.getMessage());
                  i--;
                }
            }
           correctAnswers = quiz.getCorrectCount();  
           System.out.println("You got " + correctAnswers + " questions correct");

           
            if (correctAnswers > totalQuestions / 2) {
                System.out.println("Better than 50%");
            }else {
                System.out.println("Less than 50%");
            }
           
            displayResults(correctAnswers);
            writeResultsToFile(correctAnswers);

        } catch (Exception e) {
            System.out.println("A general error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
    public static void displayResults(int correctAnswers) {
        System.out.println("You got " + correctAnswers + " questions correct.");
        if (correctAnswers == 5) {
            System.out.println("Excellent you just got 100%");
        } else if (correctAnswers >= 3) {
            System.out.println("Good job but you'll do better next time.");
        } else {
            System.out.println("Better luck next time.");
        }
    }
    public static void writeResultsToFile(int correctAnswers) {
        File file = new File("quiz_results.txt");  

        try {
            System.out.println("File should be created at: " + file.getAbsolutePath());
            if (!file.exists()) {
               boolean fileCreated = file.createNewFile();
               if (fileCreated) {
              System.out.println("File created at: " + file.getAbsolutePath()); 
                } else {
                    System.out.println("File already exists.");
                }
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
             writer.write("Date: 12/17");  
             writer.newLine();
             writer.write("Quiz Results: ");
             writer.newLine();
             writer.write("Correct Answers: " + correctAnswers + " out of 5");             
             writer.newLine();

              if (correctAnswers > 5 / 2) {
                   writer.write("Better than 50% Great Job.");
                } 
               else {
                    writer.write("Less than 50% You'll do better next time.");
                }
              writer.newLine();
              writer.write("-------------------------------");
              writer.newLine();
               System.out.println("Results written to " + file.getAbsolutePath()); 
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}