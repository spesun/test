package excel;



public abstract class Merger {
	String origin = "D:/1.xlsx";
	String appd = "D:/2.xlsx";
	
	int origin_key_col =0;
	int appd_key_col =0;
	
	public abstract void process() throws  Exception;
	
}
