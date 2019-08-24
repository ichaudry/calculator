package edu.csc413.calculator.operators;



import edu.csc413.calculator.evaluator.Operand;

import java.util.HashMap;
import java.util.Map;

public abstract class Operator {
    //Hash map that creates new objects of operators as an expression is evaluated
    private static Map<String,Operator> operators=new HashMap<>();
    //Static code block used to put necessary operator keys and values in the hash map
    static{
        operators.put("+",new AddOperator());
        operators.put("-",new SubtractOperator());
        operators.put("*",new MultiplyOperator());
        operators.put("/",new DivideOperator());
        operators.put("^",new PowerOperator());
        operators.put("(",new OpenParanthesisOperator());
        operators.put(")", new CloseParathesisOperator());
    }

    public abstract int priority();
    //public  abstract Operand execute(Operand op1, Operand op2 );
    public Operand execute (Operand op1, Operand op2){
        return null;
    }

    /**
     * determines if a given token is a valid operator.
     * please do your best to avoid static checks
     * for example token.equals("+") and so on.
     * Think about what happens if we add more operators.
     */
    public static boolean check( String token ) {
        return operators.containsKey(token);
    }



    public static Operator getOperator(String token){
        if(check(token)){
            return operators.get(token);
        }
        return null;
    }
}
