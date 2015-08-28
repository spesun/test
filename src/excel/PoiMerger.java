package excel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class PoiMerger extends Merger {

	@Override
	public void process() throws Exception {
		//one 
		InputStream inp = new FileInputStream(
				super.appd);// 获得模版文件的文件系统
		Workbook wb = WorkbookFactory.create(inp);// 取得相应工作簿
		
		Sheet appd_sheet = wb.getSheetAt(0); // 取得相应工作表
		int appd_rownum = appd_sheet.getLastRowNum()+1;
		System.out.println(" appd rownum is " + appd_rownum);
		
		//another
		InputStream inp1 = new FileInputStream(
				super.origin);// 获得模版文件的文件系统
		Workbook wb1 = WorkbookFactory.create(inp1);// 取得相应工作簿
		Sheet org_sheet = wb1.getSheetAt(0); // 取得相应工作表
		System.out.println(" origin rownum is " + (org_sheet.getLastRowNum()+1));
		
		for (int j = 0; j < (org_sheet.getLastRowNum()+1); j++) {
			Row r = org_sheet.getRow(j);
			Cell c = r.getCell(super.origin_key_col);
	    	String org_ct = getCellVal(c);

	    	for (int k = 0; k < appd_rownum; k++) {
	    		String app_ct = getCellVal(appd_sheet.getRow(k).getCell(super.appd_key_col));
	    		
	    		if (org_ct.equals(app_ct)) {
	    			org_sheet.removeRow(r);
	    			
	    			//rm empty row
	    			org_sheet.shiftRows(j+1, org_sheet.getLastRowNum(), -1);
	    			System.out.println(" del row is " + (j+1));
	    			
	    			j--;
	    			break;
		    	}
	    		
			}
	    	
		}
		
		
		
		inp.close();
		inp1.close();
		
		FileOutputStream fileOut = new FileOutputStream(origin);
		//wb1.setPrintArea(arg0, arg1);
		wb1.write(fileOut);
		fileOut.close();
	}
	
	private String getCellVal(Cell cell) {

		Object o = "";
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			 o = cell.getRichStringCellValue().getString();
			break;
		case Cell.CELL_TYPE_NUMERIC:

			o = cell.getNumericCellValue();
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			o = cell.getBooleanCellValue();
			break;
		case Cell.CELL_TYPE_FORMULA:
			o = cell.getCellFormula();
			break;
		default:
			System.err.println(cell);
		}

		return String.valueOf(o);
	}
	
	public static void main(String[] args) throws Exception {
		new PoiMerger().process();
	}

}
