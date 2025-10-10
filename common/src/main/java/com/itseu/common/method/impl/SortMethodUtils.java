package com.itseu.common.method.impl;

public class SortMethodUtils {

    /*递归合并：合并两个有序(升序）数组*/
    public  static void merge(int[] from,int i,int iEnd,int j ,int jEnd,int[] to,int k){
        if(i>iEnd){
            System.arraycopy(from,j,to,k,jEnd-j+1);
            return;
        }
        if(j>jEnd){
            System.arraycopy(from,i,to,k,iEnd-i+1);
            return;
        }
        int val1 = from[i];
        int val2 = from[j];
        if(val1 < val2){
            to[k++] = val1;
            i++;
        }else {
            to[k++] = val2;
            j++;
        }
        merge(from,i,iEnd,j,jEnd,to,k);
    }
}
