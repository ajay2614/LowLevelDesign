package Patterns.InterpreterDesignPattern;

import java.util.HashMap;
import java.util.Map;

/**
 * Interpreter Design Pattern:
 * Defines a representation of grammar for expressions
 * and an interpreter to evaluate them. Useful for
 * evaluating arithmetic expressions, simple languages, etc.
 * eg sql or java
 */
interface Expression {
    int interpret(Context context);
}

// Represents a variable or literal number
class NumberExpression implements Expression {
    private final String var;

    public NumberExpression(String var) {
        this.var = var;
    }

    @Override
    public int interpret(Context context) {
        return context.get(var);
    }
}

// Represents addition of two expressions
class AddExpression implements Expression {
    private final Expression left;
    private final Expression right;

    public AddExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret(Context context) {
        return left.interpret(context) + right.interpret(context);
    }
}

// Represents multiplication of two expressions
class MultiplyExpression implements Expression {
    private final Expression left;
    private final Expression right;

    public MultiplyExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret(Context context) {
        return left.interpret(context) * right.interpret(context);
    }
}

// Stores variable values for interpretation
class Context {
    private final Map<String, Integer> varExpMap = new HashMap<>();

    public void put(String var, int num) {
        varExpMap.put(var, num);
    }

    public int get(String var) {
        if (!varExpMap.containsKey(var)) {
            throw new RuntimeException("Variable '" + var + "' not defined in context");
        }
        return varExpMap.get(var);
    }
}

// Demo / Client code
public class InterpreterDesignPatternImpl {
    public static void main(String[] args) {
        Context context = new Context();
        context.put("a", 5);
        context.put("b", 3);
        context.put("c", 2);

        // Expression: (a + b) * c
        Expression expression = new MultiplyExpression(
                new AddExpression(new NumberExpression("a"), new NumberExpression("b")),
                new NumberExpression("c")
        );

        int result = expression.interpret(context);
        System.out.println("(a + b) * c = " + result); // Output: 16
    }
}
