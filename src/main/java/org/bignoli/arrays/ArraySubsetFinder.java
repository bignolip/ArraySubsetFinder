package org.bignoli.arrays;
import java.util.Arrays;


public class ArraySubsetFinder {
	private static int getLeftMostIndexOfValueInAscendingSortedArray(int[] a, int target)
	{
		int index = Arrays.binarySearch(a, target);
		
		if (index >= -1)
		{
			return index;
		}
		
		for (int i = index-1 ; i >= 0 ; i--)
		{
			if (a[i] == target)
			{
				continue;
			}
			else
			{
				return i+1;
			}
		}
		
		return index;
	}
	
	private static int[] getSubsetOfArrayWithTargetSum_Inner(int[] a, int target)
	{
		int[] result = null;
		
		int indexOfTarget = ArraySubsetFinder.getLeftMostIndexOfValueInAscendingSortedArray(a, target);
		
		if (indexOfTarget >= 0)
		{
			result = new int[1];
			
			result[0] = a[indexOfTarget];
			
			return result;
		}
		
		if (indexOfTarget == -1)
		{
			return result;
		}
		
		int correctedIndex = Math.min((indexOfTarget * -1) - 1, a.length);
		
		int[] sub = Arrays.copyOfRange(a, 0, correctedIndex);
		
		while (sub.length > 0)
		{
			int largestPossibleComponent = sub[sub.length-1];
			
			int difference = target - largestPossibleComponent;
			
			int indexOfDifference = ArraySubsetFinder.getLeftMostIndexOfValueInAscendingSortedArray(sub, difference);
			
			if (indexOfDifference >= 0)
			{
				result = new int[2];
				
				result[0] = sub[indexOfDifference];
				result[1] = largestPossibleComponent;
				
				return result;
			}
			else if (indexOfDifference == -1)
			{
				continue;
			}
			else
			{
				int correctedDifferenceIndex = Math.min((indexOfDifference * -1) - 1, sub.length-1);
				
				int[] recResult = ArraySubsetFinder.getSubsetOfArrayWithTargetSum_Inner(Arrays.copyOfRange(sub, 0, correctedDifferenceIndex), difference);
					
				if (recResult != null)
				{
					int recResultLen = recResult.length;
					
					result = new int[recResultLen+1];
					
					System.arraycopy(recResult, 0, result, 0, recResultLen);
					
					result[recResultLen] = largestPossibleComponent;
					
					return result;
				}
			}
			
			correctedIndex--;
			
			sub = Arrays.copyOfRange(a, 0, correctedIndex);
		}
		
		return result;
	}
	
	public static int arraySum(int[] a)
	{
		int sum = 0;
		
		for (int i = 0 ; i < a.length ; i++)
		{
			sum += a[i];
		}
		
		return sum;
	}
	
	private static int[] getSubsetOfArrayWithTargetSum(int[] a, int target)
	{
		int a_len = a.length;
		
		if (a_len == 0)
		{
			return null;
		}
		
		int a_sum = ArraySubsetFinder.arraySum(a);
		
		if (target > a_sum)
		{
			return null;
		}
		
		int[] a_copy = new int[a_len];
		
		System.arraycopy(a, 0, a_copy, 0, a_len);
		
		if (target == a_sum)
		{
			return a_copy;
		}

		Arrays.sort(a_copy);
		
		return ArraySubsetFinder.getSubsetOfArrayWithTargetSum_Inner(a_copy, target);
	}
	
	public static void main(String[] args)
	{
		int[] a = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
		
		System.out.println(Arrays.toString(ArraySubsetFinder.getSubsetOfArrayWithTargetSum(a, 47)));
		
		System.out.println(Arrays.toString(ArraySubsetFinder.getSubsetOfArrayWithTargetSum(a, 0)));
		
		System.out.println(Arrays.toString(ArraySubsetFinder.getSubsetOfArrayWithTargetSum(a, 20)));
		
		System.out.println(Arrays.toString(ArraySubsetFinder.getSubsetOfArrayWithTargetSum(a, 21)));
		
		System.out.println(Arrays.toString(ArraySubsetFinder.getSubsetOfArrayWithTargetSum(a, 41)));
		
		System.out.println(Arrays.toString(ArraySubsetFinder.getSubsetOfArrayWithTargetSum(a, 39)));
		
		System.out.println(Arrays.toString(ArraySubsetFinder.getSubsetOfArrayWithTargetSum(a, -1)));
		
		System.out.println(Arrays.toString(ArraySubsetFinder.getSubsetOfArrayWithTargetSum(a, 1000)));
		
		int[] b = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 20};
		
		System.out.println(Arrays.toString(ArraySubsetFinder.getSubsetOfArrayWithTargetSum(b, 47)));
		
		System.out.println(Arrays.toString(ArraySubsetFinder.getSubsetOfArrayWithTargetSum(b, 0)));
		
		System.out.println(Arrays.toString(ArraySubsetFinder.getSubsetOfArrayWithTargetSum(b, 20)));
		
		System.out.println(Arrays.toString(ArraySubsetFinder.getSubsetOfArrayWithTargetSum(b, 21)));
		
		System.out.println(Arrays.toString(ArraySubsetFinder.getSubsetOfArrayWithTargetSum(b, 41)));
		
		System.out.println(Arrays.toString(ArraySubsetFinder.getSubsetOfArrayWithTargetSum(a, 39)));
		
		System.out.println(Arrays.toString(ArraySubsetFinder.getSubsetOfArrayWithTargetSum(b, -1)));
		
		System.out.println(Arrays.toString(ArraySubsetFinder.getSubsetOfArrayWithTargetSum(b, 1000)));
	}
}
