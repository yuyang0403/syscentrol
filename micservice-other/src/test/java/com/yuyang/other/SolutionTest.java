package com.yuyang.other;

import org.junit.Test;

/**
 * @author yuyang
 * @create 2018/10/19 14:25
 * @desc
 **/
public class SolutionTest {
    @Test
    public void test(){
        System.out.println(climbStairs(6));
        //aabbb
        /**
         * aabbb
         * ababb
         * abbba
         * babab
         * babba
         * bbaba
         * bbbaa
         * abbab
         */
        //System.out.println(howMatch(2,3));
    }
    /**
     * 70. 爬楼梯
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        int count=0;
        int temp=n;
        for(int i=temp;i>0;i--){
            //first出现个数
            int firstX=2*temp-n;
            //second出现个数
            int secondY=temp-firstX;
            if(firstX<0||secondY<0){
                break;
            }
            if(firstX==0||secondY==0){
                count++;
            }else{
                // n个a和m个b有多少种组合方式
                count+=(firstX+secondY-1)*(secondY-1);
            }
            temp--;
        }
        return count;
    }
    public int[] twoSum(int[] nums, int target) {
        int [] result=new int[2];
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i]+nums[j]==target){
                    result[0]=i;
                    result[1]=j;
                    return result;
                }
            }
        }
        return result;
    }
}
