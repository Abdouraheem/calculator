package com.apple.calculator.service;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import org.springframework.stereotype.Service;

import com.apple.calculator.domain.Expression;
import com.apple.calculator.domain.Result;
import com.apple.calculator.exception.InvalidExpressionException;

@Service
public class ComputationService {

	public Result compute(Expression expression) {
				
		Result result = new Result();
		result.setResult(calculate(expression.getExpression()));		
		return result;
	}
	
	private double calculate(String expression) {
  
        expression = expression.replaceAll("\\s+", "");
    	if(!validate(expression)) {
    		throw new InvalidExpressionException("Invalid Arithmetic expression.");
    	}
        Queue<Character> q = new LinkedList<>();
        for (char c : expression.toCharArray()) {
            q.offer(c);
        }
        q.offer('+');
        return calculate(q);
    }
	
	private boolean validate(String expression) {
		
		int counter1 = 0;
		int counter2 = 0;
		
		if (expression == null || expression.isEmpty()) {
            return false;
        }
		
		for(char c: expression.toCharArray()) {
			if(Character.isLetter(c)) {
				return false;
			}			
			if(!Character.isDigit(c) && !"()-+/*".contains(c+"")) {
				return false;
			}		
			if(c == '(') {
				counter1++;
			}
			if(c == ')') {
				counter2++;
			}
		}
		
		if(counter1 != counter2) {
			return false;
		}
		
		return true;
	}
    
    private double calculate(Queue<Character> queue) {
        char sign = '+';
        double number = 0.0;
        Stack<Double> stack = new Stack<>();
        while (!queue.isEmpty()) {
            char ch = queue.poll();
            if (ch == ' ') {
                continue;
            }
            if (Character.isDigit(ch)) {
                number = 10 * number + ch - '0';
            } else if (ch == '(') {
                number = calculate(queue);
            } else {
                if (sign == '+') {
                    stack.push(number);
                } else if (sign == '-') {
                    stack.push(-number);
                } else if (sign == '*') {
                    stack.push(stack.pop() * number);
                } else if (sign == '/') {
                    stack.push(stack.pop() / number);
                }
                number = 0.0;
                sign = ch;
                if (ch == ')') {
                    break;
                }
            }
        }
        double sum = 0.0;
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }
        return Math.floor(sum * 100) / 100;
    }
	
}
