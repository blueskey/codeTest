package xiaohui;

import org.springframework.util.StringUtils;

/**
 * Created by Administrator on 2018/11/13. <p> 给定一个整数，从这个数当中去掉n个数字，使得剩下的新数字形成的数字尽可能的小
 */
public class DeleteNum {
	public static void main(String[] args) {
		System.out.println(delete1("541270936", 1));
		System.out.println(delete1("3549", 1));
		System.out.println(delete1("1593212", 3));
		System.out.println(delete1("30200", 1));
		System.out.println(delete1("12345", 1));
	}

	private static String delete1(String s, int n) {
		if(StringUtils.isEmpty(s))
			return "";
		if(n == 0)
			return s;
		StringBuffer newS = new StringBuffer("");
		for (int i = 0; i < n; i++)
			for (int j = 0; j < s.length() - 1; j++)
				if(s.charAt(j) < s.charAt(j + 1))
					newS.append(s.charAt(j));
				else {
					newS.append(s.substring(j + 1));
					return delete1(newS.toString(), n - 1);
				}
		if(s.equals(newS.toString()))
			return delete1(s.substring(0, s.length() - 1), n - 1);
		return newS.toString();
	}

	private static String delete2(String s, int n) {/*新整数的最终长度 = 原整数长度 - k*/
		int newLength = s.length() - n;/*创建一个栈，用于接收所有的数字*/
		char[] stack = new char[s.length()];
		int top = 0;
		for (int i = 0; i < s.length(); ++i) {/*遍历当前数字*/
			char c = s.charAt(i);/*当栈顶数字大于遍历到的当前数字，栈顶数字出栈（相当于删除数字）*/
			while (top > 0 && stack[top - 1] > c && n > 0) {
				top -= 1;
				n -= 1;
			}/*遍历到的当前数字入栈*/
			stack[top++] = c;
		}/* 找到栈中第一个非零数字的位置，以此构建新的整数字符串*/
		int offset = 0;
		while (offset < newLength && stack[offset] == '0')
			offset++;
		return offset == newLength ? "0" : new String(stack, offset, newLength - offset);
	}
}
