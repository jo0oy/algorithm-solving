package programmers;

import java.util.*;

public class 올바른괄호 {

    boolean solution(String s) {
        char[] arr = s.toCharArray();
        Stack<Character> st = new Stack<>();

        boolean answer = true;

        for(char c : arr) {
            if(c == '(') { // 여는 괄호인 경우
                st.push(c);
            } else { // 닫는 괄호인 경우

                if(st.isEmpty()) { // 스택이 비었다. --> 짝인 여는 괄호가 존재하지 않는다.
                    answer = false;
                    break;
                }

                st.pop(); // 짝이 존재하는 경우, 스택에서 pop
            }
        }

        if(!st.isEmpty()) answer = false; // 모든 괄호를 순화했는데 스택이 비어있지 않다. --> 괄호의 짝이 맞지 않다.

        return answer;
    }
}
