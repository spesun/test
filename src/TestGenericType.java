import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class TestGenericType {

	
	
	public static void main(String[] args) {
		
		/** extend **/
		//ERROR
		//List<? extends String> unknownList = new ArrayList<? extends String>(); // ArrayList�������ʺ�
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
        
        //�޷������ԭ������ڣ�ʵ�ʵ���ʱ�����list������java.util.Date��ĳ������Ĳ���������.
        //��Timestamp.  �ο�testUpperBound()����
        //list.add(date); //��仰�޷�����    
        
        list.add(null);//�����Ա��룬��Ϊnullû��������Ϣ    
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
        //���ܱ��룬�ο�testLowerBound()����
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
