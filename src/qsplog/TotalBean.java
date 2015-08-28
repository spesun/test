package qsplog;

public class TotalBean {

	public long getMaxCost() {
		return maxCost;
	}
	public void setMaxCost(long maxCost) {
		this.maxCost = maxCost;
	}
	public long getMinCost() {
		return minCost;
	}
	public void setMinCost(long minCost) {
		this.minCost = minCost;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public long getAverageCost() {
		return averageCost;
	}
	public void setAverageCost(long averageCost) {
		this.averageCost = averageCost;
	}
	public String getOutFileName() {
		return outFileName;
	}
	public void setOutFileName(String outFileName) {
		this.outFileName = outFileName;
	}
	long maxCost;
	long minCost;
	long count;
	long averageCost;
	String outFileName;
	
}
