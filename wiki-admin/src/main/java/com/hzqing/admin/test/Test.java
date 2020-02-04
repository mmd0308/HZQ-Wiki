package com.hzqing.admin.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hzqing
 * @date 2020-02-04 12:53
 */
public class Test {

    public static void main(String[] args) {
        int [] arr  = {3,20,26,7,2,70,100,24,53,88,74,103};
        int k = 3;
        System.out.println("k=" + findKth(arr,k));

    }
    public static int findKth(int[] arr,int k){
        // 用来存储比k小的奇数
        List<Integer> res = new ArrayList<>(k);
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]%2 == 1) {//奇数
                if(max == 0){
                    max = arr[i];
                    res.add(max);
                }
                if (res.size() <= k){
                    res.add(arr[i]);
                    max = max > arr[i] ? max : arr[i];
                }
            }
        }
        return max;
    }
}
