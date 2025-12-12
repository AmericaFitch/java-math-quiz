import java.util.Random;

public class MathQuiz {

	//arrays to hold the numbers in the equation
	private int[]first = new int[5];
	private int[] second = new int[5];
	private int[] answers = new int[5];
	
	private String[] operators = {" ", " + ", " - ", " * "};
	
	private int correctCount = 0;
	
	private int operation;
	
	
	
	//constructor
	public MathQuiz(int op) throws Exception {
		
		//ensures that the operation is valid since it
		//   is used as an index into an array - must be 1 - 3
		if (op < 1 || op > 3) {
			//it's bad
			throw new Exception("Operations must be 1, 2 or 3");
		}
		operation = op;
		
		Random ran = new Random();
		
		//Fill up the arrays. first for the first number in the equation,
		//    second for the second number in the equation and
		//    answer for what the answer should be. 
		
		for (int index = 0; index < first.length; index++) {
			first[index]= ran.nextInt(20) + 1;
			second[index] = ran.nextInt(20) + 1;
			if (operation == 1)
				answers[index] = first[index] + second[index];
			else if (operation == 2)
				answers[index] = first[index] - second[index];
			else 
				answers[index] = first[index] * second[index];
			
		}
	}
	
	//Method that returns the specified addition problem
	public String getQuestion(int qNumber) throws Exception {
		if (qNumber < 0 || qNumber > 4) {
			//question numbers need to be 0 - 4
			throw new Exception("Invalid question number");
		}
		else {
			return first[qNumber]+ operators[operation] + second[qNumber] + " = ";
		}
	}
	
	//provide the counter of correct answers
	public int getCorrectCount () {
		return correctCount;
	}
	
	//determine if the provided answer to the provided question is correct
	public void scoreIt(int qNumber, int answer) throws Exception {
		if (qNumber < 0 || qNumber > 4) {
			//question number must be 0 - 4
			throw new Exception("Invalid question number");
		}
		else {
			if (answer == answers[qNumber]) {
				correctCount++;
			}
		}
	}
}
