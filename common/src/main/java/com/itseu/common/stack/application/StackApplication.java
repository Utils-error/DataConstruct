package com.itseu.common.stack.application;

import ch.qos.logback.core.util.StringUtil;
import com.itseu.common.stack.Stack;
import com.itseu.common.stack.impl.LinkedListStack;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*栈相关的算法题*/
public class StackApplication {

    /*判断一个字符串中的括号是否左右合法匹配*/
    public static boolean isValidBraces(String str) {
        LinkedListStack<Character> stack = new LinkedListStack<>(Integer.MAX_VALUE);
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            // 1. 如果是左括号，我就将右括号入栈
            if (map.containsKey(ch)) {
                stack.push(map.get(ch));
            }
            // 2. 如果是右括号，我就比较其与栈顶值是否相同，不同，则不匹配
            if (map.containsValue(ch)) {
                if (stack.isEmpty() || ch != stack.pop()) {
                    return false;
                }
            }
        }
        // 这里特别注意，要判空
        return stack.isEmpty();
    }

    /*后缀表达式计算*/
    public static int evalRPN(String[] tokens) {
        Stack<String> stack = new LinkedListStack<>(Integer.MAX_VALUE);

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            // 是数字压栈
            if (token.matches("\\d+")) {
                stack.push(token);
            } else {
                Integer num2 = Integer.parseInt(stack.pop());
                Integer num1 = Integer.parseInt(stack.pop());
                // 遇到运算符则计算
                switch (token) {
                    case "+":
                        stack.push(String.valueOf(num1 + num2));
                        break;
                    case "-":
                        stack.push(String.valueOf(num1 - num2));
                        break;
                    case "*":
                        stack.push(String.valueOf(num1 * num2));
                        break;
                    case "/":
                        stack.push(String.valueOf(num1 / num2));
                        break;
                    default:
                        break;
                }
            }
        }
        return Integer.parseInt(stack.pop());
    }

    /*中缀转后缀表达式计算*/
    public static int infixToSuffix(String[] tokens) {
        Stack<String> stack = new LinkedListStack<>(Integer.MAX_VALUE);
        Map<String, Integer> map = new HashMap<>();
        map.put("*", 1);
        map.put("/", 1);
        map.put("+", 2);
        map.put("-", 2);
        map.put("(", 3);
        int brakectNum = 0;
        String[] result = new String[tokens.length];
        int index = 0;
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            // 如果是数字则保留
            if (token.matches("\\d+")) {
                result[index++] = token;
            } else {
                // 如果是运算符则入栈，但是需要进行比较，如果与栈顶运算符相比，
                // 如果栈顶优先级更高，则要先计算栈中运算则出栈
                // 否则，则继续入栈
                if (")".equals(token)) {
                    while (!"(".equals(stack.peek())) {
                        result[index++] = stack.pop();
                    }
                    stack.pop();
                    brakectNum+=2;
                } else {
                    if (!stack.isEmpty() && map.get(stack.peek()) <= map.get(token)) {
                        result[index++] = stack.pop();
                    }
                    stack.push(token);
                }
            }
        }
        while (!stack.isEmpty()) {
            result[index++] = stack.pop();
        }
        String[] newResult = new String[result.length-brakectNum];
        System.arraycopy(result, 0, newResult, 0, newResult.length);
        return evalRPN(newResult);
    }




    public static void main(String[] args) {
        // String str = "{()}}";
        // String[] tokens ={"4","13","5","/","+"};
        String[] tokens = {"(", "4", "+", "5","*","1", ")", "*", "10"};
        System.out.println(infixToSuffix(tokens));
    }

}
