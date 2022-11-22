package Stack;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


public class Applications {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '[' || c == '{' || c == '(') {
                stack.push(c);
            } else {
                if (stack.empty()) {
                    return false;
                }
                char top = stack.pop();
                if ((c == ']' && top != '[' )|| (c == '}' && top != '{') || (c == ')' && top != '(')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public int evalRPN(String[] tokens) {
        Set<String> operators = new HashSet<>(List.of("+", "-", "*", "/"));

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            String s = tokens[i];
            if (!operators.contains(s)) {
                stack.push(Integer.parseInt(s));
            } else {
                int right = stack.pop(), left = stack.pop();
                int res = switch(s) {
                    case "+" -> left + right;
                    case "-" -> left - right;
                    case "*" -> left * right;
                    case "/" -> left / right;
                    default -> throw new UnsupportedOperationException();
                };
                stack.push(res);
            }
        }
        return stack.pop();
    }

    @Test
    public void testIsValid() {
        String s1 = "()";
        assertTrue(isValid(s1));
        String s2 = "()[]{}";
        assertTrue(isValid(s2));
        String s3 = "(]";
        assertFalse(isValid(s3));
    }

    @Test
    public void testEvalRPN() {
        String[] rpn1 = {"2","1","+","3","*"};
        assertEquals(9, evalRPN(rpn1));
        String[] rpn2 = {"4","13","5","/","+"};
        assertEquals(6, evalRPN(rpn2));
        String[] rpn3 = {"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
        assertEquals(22, evalRPN(rpn3));
    }
}
