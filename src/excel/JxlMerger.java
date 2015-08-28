package excel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.WritableSheet;

public class JxlMerger extends Merger{
	public void process() throws  Exception {
		//one excel
		InputStream is = new FileInputStream(appd);
	    Workbook rwb = Workbook.getWorkbook(is);
	    Sheet appd_sheet = rwb.getSheet(0);
	    int app_rows = appd_sheet.getRows();
	    
	    //del excel
	    OutputStream os = new FileOutputStream(origin);
	    jxl.write.WritableWorkbook wwb = Workbook.createWorkbook(os);
	    WritableSheet org_sheet =  wwb.getSheet(0);

	    for (int j = 0; j < org_sheet.getRows(); j++) {
	    	String org_ct = org_sheet.getCell(j, origin_key_col).getContents();
	    	
	    	for (int k = 0; k < app_rows; k++) {
	    		String app_ct = appd_sheet.getCell(k, appd_key_col).getContents();
	    		
	    		if (org_ct.equals(app_ct)) {
	    			org_sheet.removeRow(j);
	    			//TODO
	    			//j--;
	    			break;
		    	}
			}
	    	
		}
	    
	    // 写入 Excel 对象
	    wwb.write();
	    // 关闭可写入的 Excel 对象
	    wwb.close();
	    // 关闭只读的 Excel 对象
	    os.close();
	    
	    rwb.close();
	    is.close();
	    
	}
}
