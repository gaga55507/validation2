package com.edencoding;

// Java program to use JFileChooser
// to select directory only
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;





import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
public class pdfs     {
	// Jlabel to show the files user selects
	
String J12;	
String J13;	
String NomAgent;

	// a default constructor


	public static void main(String combobox)
	{
				String J13 = Configuration.getConfigurationValue("RESULTATPDF");
				
				
				 System.out.println("Modified PDF created in >> "+ J13);
				 
				 File f = null;
			      File[] paths;
			      
			      try {  

					
			      
			         // create new file
			         f = new File(J13);
			         String J12 = Configuration.getConfigurationValue("RESULTATPDF");
					 SimpleTesting.createDirectoryIfNeeded(J12 + "/SIGNE");
					  J12 = (J12 + "/SIGNE");
			         // returns pathnames for files and directory
			         paths = f.listFiles();
			         System.out.println (f.listFiles());
			         // for each pathname in pathname array
			         for(File path:paths) {
			         if (!path.isDirectory()){

					 
			            // prints file and directory paths
			            System.out.println(path);
			            String ARC = path.getPath();
			            String ARC2 = path.getName();
			            String ARC3 = (J12);
			            String ARC5 = ARC2;
			            String ARC4 = ARC3 +"/" + ARC5;
			            LocalDate ARC6 = LocalDate.now();		
			            System.out.println("Before formatting: " + ARC6);
			            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			            String formattedDate = ARC6.format(myFormatObj);
			            
			            
			            
			            String inputFilePath = ARC ; // Existing file
			            String outputFilePath = ARC4; // New file

			            OutputStream fos = new FileOutputStream(new File(outputFilePath));

			            PdfReader pdfReader = new PdfReader(inputFilePath);
			            PdfStamper pdfStamper = new PdfStamper(pdfReader, fos);


			            // loop on all the PDF pages
			            // i is the pdfPageNumber
			            for (int i = 1; i <= 1; i++) {

			                //getOverContent() allows you to write content on TOP of existing pdf content.
			                //getUnderContent() allows you to write content on BELOW of existing pdf content.
			                   
			                   PdfContentByte pdfContentByte = pdfStamper.getUnderContent(i);

			                   // Add text in existing PDF
			                   pdfContentByte.beginText();
			                   pdfContentByte.setFontAndSize(BaseFont.createFont
			                                                     (BaseFont.TIMES_BOLD, //Font name
			                                                       BaseFont.CP1257, //Font encoding
			                                                      BaseFont.EMBEDDED //Font embedded
			                                                      )
			                                , 12); // set font and size
			                   pdfContentByte.setTextMatrix(680, 30); // set x and y co-ordinates
			                                              //0, 800 will write text on TOP LEFT of pdf page
			                                              //0, 0 will write text on BOTTOM LEFT of pdf page
			                   pdfContentByte.showText(combobox + " LE " + formattedDate); // add the text
			                   System.out.println("Text added in "+outputFilePath);
			                   
			                   pdfContentByte.endText();
			            }

			            pdfStamper.close(); //close pdfStamper

			            System.out.println("Modified PDF created in >> "+ outputFilePath);
			       
			            
			            
			            
			     
			            
					} else 
					{

					}
			           
			         }
			         
			      } catch(Exception e) {
			         // if any error occurs
			         e.printStackTrace();
			    
			      }
			      
			}
			  
		}
		
	


			

		
