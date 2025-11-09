package arrayPrograms;

import java.util.Arrays;

public class RunnerForArray {
public static void main(String[] args) {
	int ar[]= {4,78,56,8,56,98,110,5};
	Arrays.sort(ar);
//	int big=getBig(ar);
//	System.out.println("Biggest Element: "+big);
//	int secondBig=getSecondBig(ar);
//	System.out.println("Second Biggest Element: "+secondBig);
//	int secondSmall=getSecondSamall(ar);
//	System.out.println(secondSmall);
//	int nthBig=getNthBig(ar,3);
//	System.out.println(nthBig);
//	int nthbBiggest=getNthBigBySort(ar,3);
//	System.out.println("Nth BIggest: "+nthbBiggest);
	int index=binarySearch(ar,5);
	System.out.println(index);
}

private static int binarySearch(int[] ar, int ele) {
	int low=0,high=ar.length-1;
	while(low<=high) {
		int mid=(low+high)/2;
		if(ar[mid]==ele) 
			return mid;
		if(ele>ar[mid]) 
			low=mid+1;
		else 
			high=mid-1;
	}
	return -1;
}

private static int getNthBigBySort(int[] ar,int n) {
//	Arrays.sort(ar);
	///usnig Bubble up sort
	for(int i=0;i<ar.length-1;i++) {
		for(int j=0;j<ar.length-1-i;j++) {
			if(ar[j]>ar[j+1]) {
				int temp=ar[j];
				ar[j]=ar[j+1];
				ar[j+1]=temp;
			}
		}
	}
	return ar[ar.length-n];
}

private static int getNthBig(int[] ar,int n) {
	for(int i=0;i<ar.length;i++) {
		int count=0;
		for(int j=0;j<ar.length;j++) {
			if(ar[j]>ar[i]) {
				count++;
			}
		}
		if(count==n-1) {
			return ar[i];
		}
	}
	return 0;
}

private static int getSecondSamall(int[] ar) {
	int fsmall=Integer.MAX_VALUE,ssmall=Integer.MAX_VALUE;
	for(int i=0;i<ar.length;i++) {
		if(ar[i]<fsmall) {
			ssmall=fsmall;
			fsmall=ar[i];
		}else if(ar[i]<ssmall && ar[i]!=fsmall) {
			ssmall=ar[i];
		}
	}
	return ssmall;
}

private static int getSecondBig(int[] ar) {
	int fbig=Integer.MIN_VALUE,sbig=Integer.MIN_VALUE;
	for(int i=1;i<ar.length;i++) {
		if(ar[i]>fbig) {
			sbig=fbig;
			fbig=ar[i];
		}else if(ar[i]>sbig && ar[i]!=fbig) {
			sbig=ar[i];
		}
	}
	return sbig;
}

private static int getBig(int[] ar) {
	int big=ar[0];
	for(int i=0;i<ar.length;i++) {
		if(ar[i]>big) {
			big=ar[i];
		}
	}
	return big;
}
}
