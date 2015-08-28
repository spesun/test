package my.util;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

	
	public static  List<File> getFiles(File inFile, FileFilter filter) {

		List<File> files = new ArrayList<File>();
		if (inFile.isDirectory()) {
			File[] tmp = inFile.listFiles(filter);
			
			for (int i = 0; i < tmp.length; i++) {
				if (tmp[i].isDirectory()) {
					files.addAll(getFiles(tmp[i], filter));
				} else {

					files.add(tmp[i]);
				}
			}
			

		} else {
			files.add(inFile);
		}

		return files;
	}
	
	public static List<File> getFiles(File inFile) {

		return getFiles(inFile, new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
				
				return true;
			}
		});
	}
	
	public static  List<File> getDirectorys(File inFile) {

		List<File> files = new ArrayList<File>();
		if (inFile.isDirectory()) {
			files.add(inFile);
			
			File[] tmp = inFile.listFiles();
			
			for (int i = 0; i < tmp.length; i++) {
				if (tmp[i].isDirectory()) {
					files.addAll(getDirectorys(tmp[i]));
				} 
			}

		} 

		return files;
	}
	
}
