
package edu.up.cs301.dudo;

public class Bid 
{
	//Dice value
	public int currentVal;
	//Dice frequency
	public int currentFreq;
	
	public int lastVal;
	public int lastFreq;
	
	public Bid()
	{
		currentVal = 0;
		currentFreq = 0;
		lastVal = 0;
		lastFreq = 0;
	}
	
	public void setBidVal(int n)
	{
		currentVal = n;
	}
	
	public void setBidFreq(int n)
	{
		currentFreq = n;
	}
	
	public void setLastBidVal(int n){
		lastVal = n;
	}
	public void setLastFreq(int n){
		lastFreq = n;
	}
}
