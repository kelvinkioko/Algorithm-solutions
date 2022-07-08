package com.codility.codewar.solutions

import java.util.Stack

fun main(args: Array<String>) {
    isValidParentheses(s = "()")
    isValidParentheses(s = "()[]{}")
    isValidParentheses(s = "([{({[}[[]])}])")
    isValidParentheses(s = "([{}])")
}

fun isValidParentheses(s: String): Boolean {
    /**
     * Test cases
     * println("isValidParentheses 1 *${isValidParentheses(s = "()")}-")
     * println("isValidParentheses 2 *${isValidParentheses(s = "()[]{}")}-")
     * println("isValidParentheses 3 *${isValidParentheses(s = "([{({[}[[]])}])")}-")
     * println("isValidParentheses 4 *${isValidParentheses(s = "([{}])")}-")
     */
    val parenthesesMap: HashMap<Char, Char> = hashMapOf(')' to '(', '}' to '{', ']' to '[')
    val stack: Stack<Char> = Stack()

    if (s.length % 2 == 0) {
        first@for (char in s) {
            if (parenthesesMap.containsValue(char)) {
                stack.push(char)
            } else if (stack.isNotEmpty()) {
                if (stack.peek() != (parenthesesMap[char] ?: ""))
                    return false
                else
                    stack.pop()
            } else {
                return false
            }
        }
    } else {
        return false
    }

    return stack.isEmpty()
}
