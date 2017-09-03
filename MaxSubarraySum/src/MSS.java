import java.util.Arrays;


// Class for the Minimum Sub-array Sum Algorithm
public class MSS {
	
	// Constructor
	public MSS()
	{
	}
	
	// Function to compute the cross sum of the array from the L to the R index
	// This function is used in the version that finds only the maximum sum
	// without the indexes of the array.
	public double crossSum(int[] L, int[] R)
	{
		double lSum = -999999.0;
		double rSum = -999999.0;
		double tSum = 0.0;

		for(int i = L.length - 1; i>=0; i--)
		{
			tSum = tSum + L[i];
			if(tSum > lSum)
			{
				lSum = tSum;
			}
		}
		
		tSum = 0.0;
		rSum = R[0];
		for(int i = 0; i < R.length; i++)
		{
			tSum = tSum + R[i];
			if(tSum > rSum)
			{
				rSum = tSum;
			}
		}
		
		double crossSum = lSum + rSum;
		return crossSum;
	}
	
	
	// Function to find the cross sum and the indexes in the array
	public ArrayResponse crossSumwIdx(int[] A, int low, int mid, int high)
	{
		double lSum = -999999.0;
		double rSum = -999999.0;
		ArrayResponse r = new ArrayResponse();
		int leftIdx = -1;
		int rightIdx = -1;
		
		double tSum = 0.0;
		
		for(int i = mid; i>=0; i--)
		{
			tSum = tSum + A[i];
			if(tSum > lSum)
			{
				lSum = tSum;
				leftIdx = i;
			}
		}
		
		tSum = 0.0;

		for(int i = mid+1; i < high; i++)
		{
			tSum = tSum + A[i];
			if(tSum > rSum)
			{
				rSum = tSum;
				rightIdx = i;
			}
		}
		
		r.tSum = lSum + rSum;
		r.lIdx = leftIdx;
		r.rIdx = rightIdx;

		return r;
	}
	
	// Recursive function to find the maximum sub-array sum, without indexes
	public double getMSS(int[] A)
	{
		if(A.length == 1)
		{
			return A[0];
		}
		int mid = Math.floorDiv(A.length, 2);
		int[] lA = Arrays.copyOfRange(A, 0, mid);
		int[] rA = Arrays.copyOfRange(A, mid, A.length);
		double lSum = getMSS(lA);
		double rSum = getMSS(rA);
		double cSum = crossSum(lA, rA);
		if(lSum > rSum && lSum > cSum)
		{
			return lSum;
		}
		else if(rSum > lSum && rSum > cSum)
		{
			return rSum;
		}
		else
		{
			return cSum;
		}
	}
	
	
	// Recursive function to find the maximum sub-array sum with indexes
	public ArrayResponse getMSSwIdx(int[] A, int low, int high)
	{
		ArrayResponse r = new ArrayResponse();

		if(low == high)
		{
			r.lIdx = low;
			r.rIdx = high;
			r.tSum = A[low];
			return r;
		}
		int mid = Math.floorDiv(low+high, 2);

		ArrayResponse lr = getMSSwIdx(A, low, mid);
		ArrayResponse rr = getMSSwIdx(A, mid+1, high);
		ArrayResponse cr = crossSumwIdx(A, low, mid, high);

		if(lr.tSum > rr.tSum && lr.tSum > cr.tSum)
		{
			r.tSum = lr.tSum;
			r.lIdx = lr.lIdx;
			r.rIdx = lr.rIdx;
			return r;
		}
		else if(rr.tSum > lr.tSum && rr.tSum > cr.tSum)
		{
			r.tSum = rr.tSum;
			r.lIdx = rr.lIdx;
			r.rIdx = rr.rIdx;
			return r;
		}
		else
		{
			r.tSum = cr.tSum;
			r.lIdx = cr.lIdx;
			r.rIdx = cr.rIdx;
			return r;
		}
	}
	
	// Main function calling the two versions of maximum subarray.
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nA = {3, -2, 1, -4, 3, -2, -3, 5, 3, 1, 9, 1, -2, 1};
		MSS mySum = new MSS();
		double maxSum = mySum.getMSS(nA);
		System.out.println("Maximum sum: " + maxSum);
		MSS mySum2 = new MSS();
		ArrayResponse ar = mySum2.getMSSwIdx(nA, 0, nA.length - 1);
		System.out.println("Sum: " + ar.tSum + " left Idx: " + ar.lIdx + " right Idx: " + ar.rIdx);
	}

}
