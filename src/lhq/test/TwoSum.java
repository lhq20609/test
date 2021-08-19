package lhq.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/*
给定一个整数数组 nums和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那两个整数，并返回它们的数组下标。

你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。

你可以按任意顺序返回答案。
 */
public class TwoSum {
    /**
     * 暴力解法
     * @param nums
     * @param target
     * @return
     */
    public static int[] two_sum1(int nums[],int target){
        for(int i=0;i< nums.length;i++){
            for (int j=1;j< nums.length;j++){
                if(nums[i]+nums[j]==target){
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }

    /**
     * 利用HashMap解法
     * @param nums
     * @param target
     * @return
     */
    public static int[] two_sum2(int nums[],int target){
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i=0;i<nums.length;i++){
            if(map.containsKey(target-nums[i]))
                return new int[]{map.get(target-nums[i]),i};
            map.put(nums[i],i );
        }
        return null;
    }
    public static void main(String[] args) {
        int nums[]={2,7,11,15};
        int target=17;
        System.out.println(Arrays.toString(two_sum2(nums,target)));
    }
}
