package xiaohui;

import org.springframework.util.StringUtils;

/**
 * Created by Administrator on 2018/11/13.
 *
 * 给定一个整数，从这个数当中去掉n个数字，使得剩下的新数字形成的数字尽可能的小
 *
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
		if(StringUtils.isEmpty(s)) {
			return "";
		}
		if(n == 0) {
			return s;
		}
		StringBuffer newS = new StringBuffer("");
		for(int i=0;i<n;i++) {
			for(int j=0;j<s.length()-1;j++) {
				if(s.charAt(j) < s.charAt(j + 1)) {
					newS.append(s.charAt(j));
				}else {
					newS.append(s.substring(j + 1));
					return delete1(newS.toString(), n - 1);
				}
			}
		}
		if(s.equals(newS.toString())) {
			return delete1(s.substring(0, s.length() - 1), n - 1);
		}
		return newS.toString();
	}


	private static String delete2(String s, int n) {

		return null;
	}
}
