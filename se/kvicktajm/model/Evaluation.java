package se.kvicktajm.model;

import java.util.Map;
import java.util.TreeSet;

/**
 * Calculator brain:<br>
 * Backtracking recursive parser which evaluate a stokenized expression.<p>
 * {@code} evaluation = new Evaluation(tokenizer, variables)<br>
 * {@code} try: evaluation.evaluate() -> Double -catch EvalExpression, -catch SyntaxExpressopm
 * @version 2016-04-07
 */
public class Evaluation
{
	private Stokenizer tokenizer;
	private Map<String, Double> variables;
	private TreeSet<String> functions;

	/**
	 * Constructor of class {@code Evaluation}<br>
	 * Note: If you add functions you must also implement them in Evaluation.function()
	 * @param tokenizer : {@code Stokenizer}
	 * @param variables : {@code Map <String, Double>}
	 */
	public Evaluation(Stokenizer tokenizer, Map<String,Double> variables) {
		this.tokenizer = tokenizer;
		this.variables = variables;
		functions = new TreeSet<String>();  
		functions.add("sin");
		functions.add("cos");
		functions.add("tan");
		functions.add("exp");
		functions.add("log");
		functions.add("ln");
		functions.add("sqrt");
		functions.add("cbrt");
		functions.add("sqpow");
		functions.add("cbpow");
		functions.add("Rand");
		functions.add("EE");
	}

	/**
	 * Assignment of new variables.
	 * Top of the recursive backtracking parser.
	 * @return result as a {@code double} value
 	 * @throws SyntaxException if variable not given
	 */
	public double assignment() {
		double result = expression();
		while (this.tokenizer.getChar() == '=') {
			this.tokenizer.nextToken();
			if (this.tokenizer.isWord()){
				variables.put(this.tokenizer.getToken(), result);
				this.tokenizer.nextToken();
			} else throw new SyntaxException ("Expected variable after ’='");
		}
		return result;
	}

	/**
	 * Addition and subtraction
	 * @return sum
	 */
	public double expression() {
		double sum = term();
		while ((tokenizer.getChar() == '+') || (tokenizer.getChar() == '-')){
			if (tokenizer.getChar() == '+') {
				tokenizer.nextToken();
				sum += term();
			} else if (tokenizer.getChar() == '-'){
				tokenizer.nextToken();
				sum -= term();
			}
		}
		return sum;
	}

	/**
	 * Multiplication and division
	 * @return prod is the product
	 * @throws EvaluationException for division by zero
	 */
	public double term() {
		double prod = factor();
		while ((tokenizer.getChar() == '*') || (tokenizer.getChar() == '/')){
			if (tokenizer.getChar() == '*') {
				this.tokenizer.nextToken();
				prod *= factor();
			} else if (tokenizer.getChar() == '/') {
				this.tokenizer.nextToken();
				double next = factor();
				if (next != 0) {
					prod /= next;
				} else throw new EvalException ("Division by zero.");
			}
		}
		return prod;
	}

	public double factor() {
			return primary();
	}
	
	// Sub-methods
	
	/**
	 * Compute the functions listed in a TreeSet
	 * @param str is a string represented in the TreeSet
	 * @return computed value as {@code double}
	 * @throws SyntaxException
	 */
	private double function(String str){
		this.tokenizer.nextToken();
		double value = primary();
		switch (str) {
			case "cos": return Math.cos(Math.toRadians(value));
			case "sin": return Math.sin(Math.toRadians(value));
			case "tan": return Math.tan(Math.toRadians(value));
//			case "cos": return Math.cos(value);
//			case "sin": return Math.sin(value);
			case "exp": return Math.exp(value);
			case "log": return Math.log10(value);
			case "ln": return Math.log(value);
			case "sqrt": return Math.sqrt(value);
			case "cbrt": return Math.cbrt(value);
			case "sqpow": return Math.pow(value,2);
			case "cbpow": return Math.pow(value,3);
			case "Rand": return Math.random()*value;
			case "EE": return Math.pow(10,value);
			default: throw new SyntaxException(str + " is a function not implemented in funcion()");
		}
	}
	
	/**
	 * Sub-routine to return variables (stored in a map)
	 * @param str is the variable to search for
	 * @return <b>double</b> stored in the variable
	 * @throws EvaluationException if variable not found
	 */
	private double variable(String str){
		if (variables.containsKey(str)){
			return variables.get(str);
		} else throw new EvalException ("Variable " + str + " not assigned.");
	}
	
	/**
	 * The deep descent
	 * @return next value in the tokenizer
	 * @throws SyntaxException
	 */
	public double primary() {
		double result = Double.NaN;

		if (tokenizer.getChar() == '('){						// Pharantesis
			tokenizer.nextToken();
			result = assignment();
			if (tokenizer.getChar() != ')')
				throw new SyntaxException ("Expected ’)’");
			tokenizer.nextToken();
		
		} else if (tokenizer.getChar() == '-'){					// Negative numbers
			tokenizer.nextToken();
			result = -1 * primary();
		
		} else if (tokenizer.isNumber()){						// The number
			result = tokenizer.getNumber();
			tokenizer.nextToken();
		
		} else if (functions.contains(tokenizer.getToken())){	// Functions (sin, cos, log...)
			result = function(tokenizer.getToken());
		
		} else if (tokenizer.isWord()){							// Variables
			result = variable(tokenizer.getWord());
			tokenizer.nextToken();
		
		} else throw new SyntaxException("Expected number, word or ’(’");

		return result;
	}
}