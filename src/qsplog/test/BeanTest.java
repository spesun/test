package qsplog.test;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

import qsplog.TotalBean;

public class BeanTest {
	
	public static void main(String[] args) throws IntrospectionException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		TotalBean b = new TotalBean();
		b.setAverageCost(11232132);
		
		BeanInfo info = Introspector.getBeanInfo(TotalBean.class, Object.class);
		
		PropertyDescriptor[] ps = info.getPropertyDescriptors();
		
		for (int i = 0; i < ps.length; i++) {
			System.out.println(ps[i].getName());
			//System.out.println(ps[i].getReadMethod().invoke(b));
		}
	}

}
