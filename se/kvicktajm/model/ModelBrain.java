package se.kvicktajm.model;

import java.util.*;
import java.io.*;

/**
 * A numeric calculator brain
 * @version 2016-04-07
 */
public class ModelBrain
{
	private Evaluation evaluate;
	private Stokenizer tokenizer;
	private Map<String, Double> variables;
	private static final boolean LOG = false;

	public ModelBrain() {
		this.variables = new TreeMap<String, Double>();

		// Those are found in menu of program (see RootLayout).
		variables.put("PI", Math.PI);
		variables.put("E", Math.E);
		variables.put("SOL", 299792458.);	// Speed of light (m/s)
		variables.put("DEEPTHOUGHT", 42.);	// Ultimate answer

		// Predefined answer variable
		variables.put("ans", new Double(0.));
	}

	private void log(String s){
		if (LOG) System.out.println(s);
	}

	public double evaluate(String expression) {
		this.tokenizer = new Stokenizer(expression);
		this.evaluate = new Evaluation(tokenizer, variables);
		double answer = Double.NaN;
		log("> " + expression);

		try {
			answer = parse();

		} catch (SyntaxException syntaxException) {
			System.out.println("> ***Syntax error" + syntaxException.getMessage());
			System.out.println("> *** The error occured at token ’" + tokenizer.getToken() + "’ just after token ’" + tokenizer.getPreviousToken() + "’");

		} catch (EvalException evalException) {
			System.out.println("> ***Evaluation error" + evalException.getMessage());
		}
		return answer;
	}

	private double parse() throws SyntaxException, EvalException {

//		String command = tokenizer.getToken();		// First token

		do {										// Skip empty lines  
			tokenizer.nextToken();
		} while (tokenizer.isEOL());

		double parsed = evaluate.assignment();		// the first element in an expression
		log(Double.toString(parsed));

		variables.put("ans", new Double(parsed));
		if (tokenizer.isEOL()) {
			throw new SyntaxException("Expected EOL");
		}
		return parsed;
	}
	
	/*
	____________________ cut here ____________________><8

	*/

	/**
	 * Takes the input to the calculator from a file.
	 * The method is intended mainly for debugging reason.
	 * It displays the input lines and results stepwise.
	 * Any syntax or evaluation error will terminate the reading from the file
	 * The commands (quit, file and vars) can not be used in a file
	 * @throws SyntaxException
	 * @throws EvaluationException
	 * @throws IOException
	 * @param fileName name of the file to be used as input
	 */
	@SuppressWarnings("unused")
	private void fileInput(String fileName) throws IOException {

		@SuppressWarnings("resource")		// Note!
		Scanner scan = new Scanner(System.in);

		if (!tokenizer.isWord()) {
			throw new SyntaxException("Expected filename. Found: " + tokenizer.getToken());   
		}
		File input = new File(fileName);
		if (!input.exists()) {
			throw new EvalException("The file '" + fileName + "' does not exist");
		}
		System.out.println();
		tokenizer.nextToken();
		Stokenizer strTokenizer = null;

		@SuppressWarnings("resource")		// Note!
		Scanner fsc = new Scanner(input);
		while (fsc.hasNextLine()) {
			String line = fsc.nextLine();
			String comment = "";
			int commentIndex = line.indexOf("%");
			if (commentIndex>=0) {
				comment = line.substring(commentIndex);
				line = line.substring(0,commentIndex);
			}
			line = line.trim();
			if (line.length()==0 && comment.length()>0) {
				System.out.println(comment);
			} else if (line.length()>0) {
				System.out.format("Input  : %-35s  %s", line, comment);
				scan.nextLine();
				strTokenizer = new Stokenizer(line);
				Evaluation evaluate = new Evaluation(strTokenizer, variables);
				strTokenizer.nextToken();

				String command = tokenizer.getToken();
				double value = evaluate.assignment();
				System.out.format("%-46s  %s","Result : ", value); 
				variables.put("ans", value);
				scan.nextLine();
			} 
		}
		System.out.println("* End of file input *");
	}
}
