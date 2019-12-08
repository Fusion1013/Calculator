package calc;

import java.util.*;

import static java.lang.Double.NaN;
import static java.lang.Math.pow;


/*
 *   A calculator for rather simple arithmetic expressions
 *
 *   This is not the program, it's a class declaration (with methods) in it's
 *   own file (which must be named Calculator.java)
 *
 *   NOTE:
 *   - No negative numbers implemented
 */
public class Calculator {

    // Here are the only allowed instance variables!
    // Error messages (more on static later)
    final static String MISSING_OPERAND = "Missing or bad operand";
    final static String DIV_BY_ZERO = "Division with 0";
    final static String MISSING_OPERATOR = "Missing operator or parenthesis";
    final static String OP_NOT_FOUND = "Operator not found";

    // Definition of operators
    final static String OPERATORS = "+-*/^";

    // Method used in REPL
    double eval(String expr) {
        if (expr.length() == 0) {
            return NaN;
        }
        List<String> tokens = tokenize(expr);
        List<String> postfix = infix2Postfix(tokens);
        return evalPostfix(postfix);
    }

    // ------  Evaluate RPN expression -------------------

    double evalPostfix(List<String> postfix) {
        // TODO
        return 0;
    }

    double applyOperator(String op, double d1, double d2) {
        switch (op) {
            case "+":
                return d1 + d2;
            case "-":
                return d2 - d1;
            case "*":
                return d1 * d2;
            case "/":
                if (d1 == 0) {
                    throw new IllegalArgumentException(DIV_BY_ZERO);
                }
                return d2 / d1;
            case "^":
                return pow(d2, d1);
        }
        throw new RuntimeException(OP_NOT_FOUND);
    }

    // ------- Infix 2 Postfix ------------------------

    List<String> infix2Postfix(List<String> infix) {
        // Data holders
        Stack<String> op = new Stack<>();
        List<String> output = new ArrayList<>();

        // Loops through infix
        for (int i = 0; i < infix.size(); i++){
            String current = infix.get(i);

            // If it's a number;
            // Add token to output
            if (Character.isDigit(current.charAt(0))){
                output.add(infix.get(i));
            }
            else if(isOperator(current)){
                if ()
            }
            // Else push to op
            else{
                op.push(current);
            }
        }

        // Pop op to output
        while(!op.empty()) {
            output.add(op.pop());
        }

        // TEMP: PRINT OUTPUT
        System.out.println(String.join(" ", output));

        return output;
    }

    boolean isOperator(String op) {
        return "+-*/^".contains(op);
    }

    int getPrecedence(String op) {
        if ("+-".contains(op)) {
            return 2;
        } else if ("*/".contains(op)) {
            return 3;
        } else if ("^".contains(op)) {
            return 4;
        } else {
            throw new RuntimeException(OP_NOT_FOUND);
        }
    }

    Assoc getAssociativity(String op) {
        if ("+-*/".contains(op)) {
            return Assoc.LEFT;
        } else if ("^".contains(op)) {
            return Assoc.RIGHT;
        } else {
            throw new RuntimeException(OP_NOT_FOUND);
        }
    }

    enum Assoc {
        LEFT,
        RIGHT
    }

    // ---------- Tokenize -----------------------

    // List String (not char) because numbers (with many chars)
    List<String> tokenize(String expr) {
        List<String> tokens = new ArrayList<>();

        String saved = "";
        // Loop through expr
        for (int i = 0; i < expr.length(); i++) {
            // If the character is a digit, check if the next is too
            if (Character.isDigit(expr.charAt(i))) {
                saved += expr.charAt(i);
                if (i + 1 < expr.length()) {
                    // If the next char is not a digit, add saved to tokens
                    if (!Character.isDigit(expr.charAt(i + 1))) {
                        tokens.add(saved);
                        saved = "";
                    }
                }
                // If the next char does not exist, add saved to tokens
                else{
                    tokens.add(saved);
                    saved = "";
                }
            }
            // If it's not a digit and not a space, add it to tokens
            else if (expr.charAt(i) != ' '){
                tokens.add(String.valueOf(expr.charAt(i)));
            }
        }

        if (false) {
            for (String s : tokens) {
                System.out.println(s);
            }
        }

        return tokens;
    }

}
