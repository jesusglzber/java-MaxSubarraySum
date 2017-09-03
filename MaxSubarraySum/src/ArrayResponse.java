

// Class to store a result for the maximum sub-array sum.
public class ArrayResponse {

	int lIdx = 0;
	int rIdx = 0;
	double tSum = 0.0;
	
	public ArrayResponse()
	{
		
	}
	
	public void setlIdx(int left)
	{
		this.lIdx = left;
	}
	
	public void setrIdx(int right)
	{
		this.rIdx = right;
	}
	
	public void setSum(double tsum)
	{
		this.tSum = tsum;
	}
	
	public int getlIdx()
	{
		return this.lIdx;
	}
	
	public int getrIdx()
	{
		return this.rIdx;
	}
	
	public double gettSum()
	{
		return this.tSum;
	}
}
