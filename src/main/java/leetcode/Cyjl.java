package leetcode;

import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class Cyjl {


	public static void main(String[] args) {
		List dictionary =Lists.newArrayList("hot", "dot", "dog", "lot", "log", "cog" );
		String begin = "hit";
		String end = "cog";
		List<String> transformList = transform(dictionary, begin, end);
		transformList.stream().forEach(s -> System.out.print(s+" --> "));
	}

	private static List<String> transform(List<String> dictionary, String begin, String end) {
		List<String> transList = Lists.newArrayList();
		if(!dictionary.contains(end)) {
			System.out.println("无转换．．");
			return transList;
		}
		List<String> canTrans = Lists.newArrayList();
		List<String> newDic = Lists.newArrayList();
		dictionary.stream().forEach(s ->{
			if(diff(s, begin) <= 1) {
				canTrans.add(s);
			}else {
				newDic.add(s);
			}
		});
		List<String> endCanTrans = Lists.newArrayList();
		dictionary.stream().forEach(s ->{
			if(diff(s, end) == 1) {
				endCanTrans.add(s);
			}
		});

		if(CollectionUtils.isEmpty(canTrans)) {
			System.out.println("无转换．1．");
			return transList;
		}
		if(canTrans.size() == 1) {
			transList.add(canTrans.get(0));
			transform(newDic, canTrans.get(0), end);
		}else {

		}

		System.out.println(canTrans);
		return transList;
	}

	private static int diff(String s1, String s2) {
		if(null == s1 || null == s2) {
			return s2.length();
		}

		int diff = 0;
		for (int i = 0; i < s1.length(); i++) {
			if(s1.charAt(i) != s2.charAt(i)) {
				diff++;
			}
		}
		System.out.println(s1+" - "+ s2+" diff==" + diff);
		return diff;
	}
}
