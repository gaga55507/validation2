
package com.edencoding;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class SimpleTesting {

    

	
	static File folder9 = new File(Configuration.getConfigurationValue("RESULTATPDF"));
	static File folder10 = new File(Configuration.getConfigurationValue("ARCHIVE"));
	
    public static void main (){
       String pathsARCH = Configuration.getConfigurationValue("ARCHIVE");
       
       SimpleDateFormat formater = null;
       Date aujourdhui = new Date();
       formater = new SimpleDateFormat("dd-MM-yyyy");
       String dateauj = formater.format(aujourdhui);
       SimpleTesting.createDirectoryIfNeeded(pathsARCH +"/" +dateauj);
       String fichdest = pathsARCH +"/" + dateauj +"/";
       String RESULTPDF = Configuration.getConfigurationValue("RESULTATPDF");
	   File folder = new File(RESULTPDF);
	  
	   findAllFilesInFolder(folder,fichdest);

	
    }

    public static void createDirectoryIfNeeded(String directoryName)
{
File theDir = new File(directoryName);  
if (!theDir.exists())
    theDir.mkdirs();
}



	public static void findAllFilesInFolder(File folder,String fichdest) {
		for (File file : folder.listFiles()) {
			if (!file.isDirectory()) {
				System.out.println(file.getName());
				File source = new File(file.getAbsolutePath()); 
				File destination = new File(fichdest+file.getName()); 
				boolean resultat = source.renameTo(destination);

			} else {
				String fichedest2 = fichdest + "SIGNE";
				String fichedest3 =fichedest2 +"/";
				createDirectoryIfNeeded(fichedest2);
				findAllFilesInFolder(file,fichedest3);
			}
		}
	}
	public static void supressfile (File folder) {
		for (File file : folder.listFiles()) {
			if (!file.isDirectory()) {
				System.out.println(file.getName());
				file.delete();

			} else {
				supressfile(file);
			}
		}
	}

	public static void INITIALISATION ( ) {

	   createDirectoryIfNeeded(folder9.getAbsolutePath());
	   createDirectoryIfNeeded(folder10.getAbsolutePath());
		
	}





}