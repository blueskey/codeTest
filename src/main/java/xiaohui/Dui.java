package xiaohui;

import java.util.Stack;

/**
 * Created by Administrator on 2018/11/16.
 */
public class Dui {

	public static void main(String[] args) {
		int[] nums = { 1, 3, 2, 6, 5, 7, 8, 9, 10, 0 };

		System.out.println(toRevert(".abc..de..b..de."));
		System.out.println(toRevert("ab..bc...cd."));


	}

	public static void buidDui(int[] num) {

	}

	/**
	 * 假设给定一个由字母和小数点组成的字符串，把字符串按块翻转，其中连续的小数点为一块，连续的字母为一块。例如 'ab..bc...cd.' 翻转后为 '.cd...bc..ab'。
	 * @param s
	 * @return
	 */
	public static String toRevert(String s) {
		StringBuffer a = new StringBuffer();
		Stack<Character> b = new Stack<Character>();
		for (int i = s.length() - 1; i >= 0; i--) {
			char c=s.charAt(i);
			if(c == '.') {
				while (!b.isEmpty()) {
					a.append(b.pop());
				}
				a.append(c);
			}else {
				b.push(c);
			}
		}
		while (!b.isEmpty()) {
			a.append(b.pop());
		}
		return a.toString();
	}


}
