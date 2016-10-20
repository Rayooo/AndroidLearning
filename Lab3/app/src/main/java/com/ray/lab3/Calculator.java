package com.ray.lab3;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by Ray on 2016/10/20.
 * copy from https://gist.github.com/EvilBone/d9a955828b4043c10e282fe30a3529ea
 */
public class Calculator {
    private Stack<String> operandStack = new Stack<String>();//逆波兰输出栈 s2
    private Stack<String> operatorStack = new Stack<String>();//临时操作符栈 s1
    private Stack<Double> resultStack = new Stack<>();//计算结果 s3
    private String expression;//算数表达式
    private double result = 0d;//计算结果
    private String resultString = "";
    private Map<String, Integer> priorityMap = new HashMap<String, Integer>();//用于存储操作符优先级的Map

    //初始化优先级约定(可根据计算的复杂程度扩展)
    public Calculator() {
        priorityMap.put("+", 1);
        priorityMap.put("-", 1);
        priorityMap.put("×", 2);
        priorityMap.put("÷", 2);
        priorityMap.put("#", 0);
        priorityMap.put("(", 0);
        priorityMap.put(")", 0);
        operatorStack.push("#");
    }

    public int getPriority(String op)//得到一个操作符的优先级
    {
        return priorityMap.get(op);
    }

    public boolean highPriority(String op)//比较操作符的优先级
    {
        int opPri = getPriority(op);//当前操作符的优先级
        String s = operatorStack.get(operatorStack.size() - 1);
        int priority = getPriority(s);
        return opPri > priority;
    }

    public double cal() {
        for (String s : operandStack) {
            if (s.matches("^[0-9]+.?[0-9]*$")) {
                resultStack.push(Double.parseDouble(s));
            } else {
                popOperand(s);
            }
        }
        return result;

    }

    public void popOperand(String operator) {
        double d1 = 0;
        double d2 = 0;
        if (resultStack.size() > 0) {
            d1 = resultStack.pop();

        }
        if (resultStack.size() > 0) {
            d2 = resultStack.pop();
        }
        System.out.println(d2 + operator + d1);
        if (operator.equals("+"))
            result = d2 + d1;
        if (operator.equals("-"))
            result = d2 - d1;
        if (operator.equals("×"))
            result = d2 * d1;
        if (operator.equals("÷"))
            if (d1 != 0)
                result = d2 / d1;
            else
                resultString = "NaN";
        if (!resultString.equals("NaN"))
            resultString = String.valueOf(result);
        resultStack.push(result);

    }

    public void expToIpn() {
        int index = 0;
        int end = 0;
        for (int i = 0; i < expression.length(); i++) {
            String temps = String.valueOf(expression.charAt(i));
            if (temps.matches("[0-9.]"))//检查是否是数字
            {
                end++;
            } else if (!temps.equals("!")) {//未结束,不是数字
                if (index < end) {
                    String tempOperand = expression.substring(index, end);//得到操作数
                    operandStack.push(tempOperand);//压入s2
                }
                String op = expression.substring(end, ++end);//获取操作符
                if (op.equals("(")) {//等于“(”压入s1
                    operatorStack.push(op);
                } else if (op.equals(")")) {//等于")"将直到的“（”符号,出s1,压入s2
                    while (true) {
                        String tmop = operatorStack.pop();
                        if (tmop.equals("(")) {
                            break;
                        } else {
                            operandStack.push(tmop);
                        }
                    }
                } else {//既不等于“（”,也不等于“)”的
                    String temop = operatorStack.get(operatorStack.size() - 1);
                    if (temop.equals("(")) {//s1栈顶是“（”直接入栈s1.
                        operatorStack.push(op);
                    } else {//不是的判断优先级,优先级高的直接入栈s1
                        if (highPriority(op)) {
                            operatorStack.push(op);
                        } else {//低的
                            while (true) {
                                String tmop = operatorStack.pop();
                                operandStack.push(tmop);
                                if (highPriority(op) || tmop.equals("(")) {
                                    operatorStack.push(op);
                                    break;
                                }
                            }
                        }
                    }
                }
                index = end;
            } else {
                if (index < end) {
                    String tempOperand = expression.substring(index, end);//得到操作数
                    operandStack.push(tempOperand);
                }
            }

        }

        while (operatorStack.size() > 1) {
            String tmop = operatorStack.pop();
            operandStack.push(tmop);
        }
        System.out.println(operandStack);
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public double getResult() {
        return result;
    }

    public String getResultString() {
        return resultString;
    }

/*
    public static void main(String[] args) {
        String express = "0-36÷36!";
        Calculator calculator = new Calculator();
        calculator.setExpression(express);
        calculator.expToIpn();
        calculator.cal();
        String re = calculator.getResultString();
        System.out.print("Result is " + re);
    }
*/
}
