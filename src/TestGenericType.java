import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class TestGenericType {

	
	
	public static void main(String[] args) {
		
		/** extend **/
		//ERROR
		//List<? extends String> unknownList = new ArrayList<? extends String>(); // ArrayList不能有问号
		List<? extends String> unknownList = new ArrayList<String>();
		
		//ERROR
		//unknownList.add("ss");
		//unknownList.add(new Object());
		unknownList.add(null);
		unknownList.contains("ss");
		
		
		//ERROR
		//List<Number> numbers = new ArrayList<Integer>();
		
		testUpperBound();
		testLowerBound();
		
	}
	
    public static void upperBound(List<? extends Date> list, Date date)    
    {    
        Date now = list.get(0);    
        System.out.println("now==>" + now);    
        
        //无法编译的原因就在于，实际调用时传入的list可能是java.util.Date的某个子类的参数化类型.
        //如Timestamp.  参考testUpperBound()方法
        //list.add(date); //这句话无法编译    
        
        list.add(null);//这句可以编译，因为null没有类型信息    
    }  
    
    
    public static void testUpperBound()    
    {    
        List<Timestamp> list = new ArrayList<Timestamp>();    
        Timestamp date = new Timestamp(System.currentTimeMillis());    
        list.add(date);
        upperBound(list,date);    
    }  
    
    
    public static  void lowerBound(List<? super Timestamp> list)    
    {    
        Timestamp now = new Timestamp(System.currentTimeMillis());    
        list.add(now);    
        //不能编译，参考testLowerBound()方法
        //Timestamp time = list.get(0);     
        Object time = list.get(0);     
        System.out.println(time.getClass());
         
    }  
    
    public static void testLowerBound()    
    {    
        List<Date> list = new ArrayList<Date>();    
        list.add(new Date());    
        lowerBound(list);    
    }  
}
