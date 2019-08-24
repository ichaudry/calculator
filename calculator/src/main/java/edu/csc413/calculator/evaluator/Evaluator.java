package edu.csc413.calculator.evaluator;


import edu.csc413.calculator.operators.Operator;

import java.util.Objects;
import java.util.Stack;
import java.util.StringTokenizer;

public class Evaluator {
    private Stack<Operand> operandStack;
    private Stack<Operator> operatorStack;
    private StringTokenizer tokenizer;
    private static final String DELIMITERS = "()+-*^/ ";

    public Evaluator() {
        operandStack = new Stack<>();
        operatorStack = new Stack<>();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evaluator evaluator = (Evaluator) o;
        return Objects.equals(operandStack, evaluator.operandStack) &&
                Objects.equals(operatorStack, evaluator.operatorStack) &&
                Objects.equals(tokenizer, evaluator.tokenizer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operandStack, operatorStack, tokenizer);
    }

    public int eval(String expression) {
        String token;
        // The 3rd argument is true to indicate that the delimiters should be used
        // as tokens, too. But, we'll need to remember to filter out spaces.
        this.tokenizer = new StringTokenizer(expression, DELIMITERS, true);


        while (this.tokenizer.hasMoreTokens()) {
            //Label used for breaking from parenthesis loop
            start:

            // filter out spaces
            if (!(token = this.tokenizer.nextToken()).equals(" ")) {
                // check if token is an operand
                if (Operand.check(token)) {
                    operandStack.push(new Operand(token));
                } else {
                    if (!Operator.check(token)) {
                        System.out.println("*****invalid token******");
                        throw new RuntimeException("*****invalid token******");
                    }

                    //Instantiates a new operator from token
                    Operator newOperator = Operator.getOperator(token);

                    //This loop makes sure that operator priority is taken into account while evaluating expression
                    while (!operatorStack.empty() && operatorStack.peek().priority() >= newOperator.priority() && !(operatorStack.peek().priority() == 4)) {
                        Operator oldOpr = operatorStack.pop();
                        Operand op2 = operandStack.pop();
                        Operand op1 = operandStack.pop();
                        operandStack.push(oldOpr.execute(op1, op2));
                    }

                    //To evaluate parenthesis when open parenthesis is encountered
                    if (newOperator.priority() == 5) {
                        while (!(operatorStack.peek().priority() == 4)) {
                            Operator finalOpr = operatorStack.pop();
                            Operand op2 = operandStack.pop();
                            Operand op1 = operandStack.pop();
                            operandStack.push(finalOpr.execute(op1, op2));
                        }
                        operatorStack.pop();
                        //This breaks to the label start which is at the beginning of the while loop
                        break start;
                    }

                    //Push operator to operator stack
                    operatorStack.push(newOperator);
                }
            }
        }
        //This while loop does the final evaluations of whatever remains in stacks
        while (!operandStack.empty() && !operatorStack.empty()) {
            Operator finalOpr = operatorStack.pop();
            Operand op2 = operandStack.pop();
            Operand op1 = operandStack.pop();
            operandStack.push(finalOpr.execute(op1, op2));
        }

        //Return answer
        return operandStack.pop().getValue();

    }
}
