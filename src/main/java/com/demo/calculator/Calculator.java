package com.demo.calculator;

import com.demo.calculator.util.LiteralStack;
import com.demo.calculator.util.Literals;


/**
 * @author Rushikesh
 *
 */
public class Calculator {

	private LiteralStack operatorStack;
	private LiteralStack valueStack;
	private boolean error;

	public Calculator() {
		operatorStack = new LiteralStack();
		valueStack = new LiteralStack();
		error = false;
	}

	/**
	 * @param t
	 */
	private void processOperator(Literals t) {
		Literals A = null, B = null;
		if (valueStack.isEmpty()) {
			System.out.println("Expression error.");
			error = true;
			return;
		} else {
			B = valueStack.top();
			valueStack.pop();
		}
		if (valueStack.isEmpty()) {
			System.out.println("Expression error.");
			error = true;
			return;
		} else {
			A = valueStack.top();
			valueStack.pop();
		}
		Literals R = t.operate(A.getValue(), B.getValue());
		valueStack.push(R);
	}

	/**
	 * @param input
	 * @return
	 */
	public String processInput(String input) {
		try {
			String[] parts = input.split(" ");
			Literals[] literals = new Literals[parts.length];
			for (int n = 0; n < parts.length; n++) {
				literals[n] = new Literals(parts[n]);
			}

			for (int n = 0; n < literals.length; n++) {
				Literals nextLiteral = literals[n];
				if (nextLiteral.getType() == Literals.NUMBER) {
					valueStack.push(nextLiteral);
				} else if (nextLiteral.getType() == Literals.OPERATOR) {
					if (operatorStack.isEmpty() || nextLiteral.getPrecedence() > operatorStack.top().getPrecedence()) {
						operatorStack.push(nextLiteral);
					} else {
						while (!operatorStack.isEmpty()
								&& nextLiteral.getPrecedence() <= operatorStack.top().getPrecedence()) {
							Literals toProcess = operatorStack.top();
							operatorStack.pop();
							processOperator(toProcess);
						}
						operatorStack.push(nextLiteral);
					}
				} else if (nextLiteral.getType() == Literals.LEFT_PARENTHESIS) {
					operatorStack.push(nextLiteral);
				} else if (nextLiteral.getType() == Literals.RIGHT_PARENTHESIS) {
					while (!operatorStack.isEmpty() && operatorStack.top().getType() == Literals.OPERATOR) {
						Literals toProcess = operatorStack.top();
						operatorStack.pop();
						processOperator(toProcess);
					}
					if (!operatorStack.isEmpty() && operatorStack.top().getType() == Literals.LEFT_PARENTHESIS) {
						operatorStack.pop();
					} else {
						System.out.println("Error: unbalanced parenthesis.");
						error = true;
					}
				}

			}
			while (!operatorStack.isEmpty() && operatorStack.top().getType() == Literals.OPERATOR) {
				Literals toProcess = operatorStack.top();
				operatorStack.pop();
				processOperator(toProcess);
			}
			Literals result = new Literals();
			if (error == false) {
				result = valueStack.top();
				valueStack.pop();
				if (!operatorStack.isEmpty() || !valueStack.isEmpty()) {
					System.out.println("Invalid Expression");
					return "Invalid Expression";
				} else {
					System.out.println("The result is " + result.getValue());
					return Double.toString(result.getValue());
				}
			}
			return Double.toString(result.getValue());
		} catch (Exception e) {
			return "Invalid Expression";	
		}
	}

//	@SuppressWarnings("resource")
//	public static void main(String[] args) {
//		Scanner input = new Scanner(System.in);
//
//		// The original input
//		System.out.println("Enter an expression with space after each operand and operator : ");
//		String userInput = input.nextLine();
//
//		Calculator calc = new Calculator();
//		calc.processInput(userInput);
//	}

}
