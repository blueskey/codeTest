package leetcode;

import com.google.common.collect.Lists;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 集合所有子集方法
 * 集合全排列方法
 */
public class ListCombine {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        System.out.println("请输入一串整数并在输入时用英文逗号隔开：");
        String inputString = new Scanner(System.in).next().toString();
        if (inputString != null && !inputString.isEmpty()) {
            String[] strArray = inputString.split(",");
            for (String str : strArray) {
                list.add(Integer.parseInt(str));
            }
            ArrayList<ArrayList<Integer>> allsubsets = getSubsets(list);
            for(ArrayList<Integer> subList : allsubsets) {
                System.out.println(subList);
            }
        }

        for(int i = 1;i<=list.size();i++){
            combine(list,0,3,i,new HashSet());
        }
    }
    public static ArrayList<ArrayList<Integer>> getSubsets(ArrayList<Integer> subList) {
        ArrayList<ArrayList<Integer>> allsubsets = new ArrayList<ArrayList<Integer>>();
        int max = 1 << subList.size();
        for(int loop = 0; loop < max; loop++) {
            int index = 0;
            int temp = loop;
            ArrayList<Integer> currentCharList = new ArrayList<Integer>();
            while(temp > 0) {
                if((temp & 1) > 0) {
                    currentCharList.add(subList.get(index));
                }
                temp>>=1;
                index++;
            }    allsubsets.add(currentCharList);   }
        return allsubsets;
    }

    private static void combine(List list, int start, int end, int length,
                                HashSet hashSet) {

        if(length ==0){
            System.out.println(hashSet.toString());
            return;
        }


        for(int i = start ;i<=end - length + 1;i++){

            hashSet.add(list.get(i));
            combine(list, i+1, end, length-1, hashSet);
            hashSet.remove((list.get(i)));
        }

    }


    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (nums.length == 0)
            return null;
        Arrays.sort(nums);
        getResult(result,nums,new ArrayList<Integer>(),0,new int[nums.length]);
        return result;
    }

    public static void getResult(List<List<Integer>> result,int[] nums,List<Integer> ans,int num,int[] pos){
        if( num == nums.length){
            result.add(new ArrayList<Integer>(ans));
            return ;
        }
        for( int i = 0 ; i<nums.length;i++){
            if( pos[i] == 0 ){
                ans.add(nums[i]);
                pos[i] = 1;
                getResult(result,nums,ans,num+1,pos);
                pos[i] = 0;
                ans.remove(num);
                while(i<nums.length-1 && nums[i] == nums[i+1]){//在这里判断之后的数字是否一样，如果一样，就直接跳过。
                    i++;
                }
            }
        }
    }

    private List<List<Integer>> combineList(List<List<Integer>> result,Integer[] res, int k, int len) {
        if (k == len) {
            List<Integer> subList = new ArrayList<Integer>();
            for (int i = 0; i < res.length; i++) {
                subList.add(res[i]);
            }
            result.add(subList);
        } else {
            for (int i = k; i <= len; i++) {
                Integer temp = res[k];
                res[k] = res[i];
                res[i] = temp;
                combineList(result,res, k + 1, len);
                Integer temp1 = res[k];
                res[k] = res[i];
                res[i] = temp1;
            }
        }
        return result;
    }

    /**
     * 获取集合全排列的所有组合
     * @param result
     * @param element
     * @param resources
     * @param n
     * @return
     */
    private List<List<Integer>> combineList(List<List<Integer>> result,List<Integer> element,List<Integer> resources,int n){
        if(n==resources.size()){
            result.add(element);
            return result;
        }
        int size = element.size();
        for(int i = 0; i <= size; i++){
            List<Integer> temp = Lists.newArrayList(element);
            temp.add(i,resources.get(n));
            combineList(result, temp, resources, n+1);
        }
        return result;
    }


    /**
     * 所有子集(元素有序)
     * @param skuRuleVOs
     * @return
     */
    private List<List<Integer>>  fetchAllSubList(List<Integer> skuRuleVOs) {
        List<List<Integer>> list = Lists.newArrayList();
        int max = 1 << skuRuleVOs.size();
        for(int loop = 0; loop < max; loop++) {
            int index = 0;
            int temp = loop;
            List<Integer> currentCharList = Lists.newArrayList();
            while(temp > 0) {
                if((temp & 1) > 0) {
                    currentCharList.add(skuRuleVOs.get(index));
                }
                temp>>=1;
                index++;
            }
            list.add(currentCharList);
        }
        return list.stream().filter(e -> e.size() >= 1).collect(Collectors.toList());
    }

}
