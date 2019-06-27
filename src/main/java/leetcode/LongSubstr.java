package leetcode;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class LongSubstr {

	public int lengthOfLongestSubstring(String s) {
		if(StringUtils.isBlank(s)) {
			return 0;
		}
		int n = s.length(), ans = 0;
		Map<Character, Integer> map = new HashMap<>();
		for (int j = 0, i = 0; j < n; j++) {
			if (map.containsKey(s.charAt(j))) {
				i = Math.max(map.get(s.charAt(j)), i);
			}
			ans = Math.max(ans, j - i + 1);
			map.put(s.charAt(j), j + 1);
		}
		return ans;
	}
}
