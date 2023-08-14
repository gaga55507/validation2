package com.edencoding;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
public class E1221 {
    static String J20;
    public static void main(String[] args) throws InvalidPasswordException, IOException {
     PdfReader pdfReader = new PdfReader("E1201.pdf");
     starte1201(pdfReader);
   
 }
 public static String[][] starte1201(PdfReader pdfReader) {
 
   int page = pdfReader.getNumberOfPages();
 
   String[][] tests= new String[page][3];
   for (int x=1;x <=page;x++) {
    String[] test =extractText(pdfReader, x);
    String pj = FindPJ(test);
    tests[x-1][0]=Integer.toString(x);
    tests[x-1][1]= pj;
    String tot= FindTotal(test);
    tests[x-1][2]=tot;
   
   }
      
   
 for (String[] tes : tests) {
  if (!tes[2].isBlank()|| !tes[2].isEmpty() ) {
    System.out.println("la page " + tes[0]+" est un total " + tes[1]);
  }
 }
 return tests;
 }
 
      
 
 private static String FindTotal(String[] test ) {
 
 
   String[] filename=null;
   String allMatches2 = "";
   List<Integer> nump =new ArrayList<Integer>();
   int a=0;
   for (String lines : test) {
           Matcher m = Pattern.compile(".*CUMUL GENERAL.*")
           .matcher(lines);
           while (m.find()) {
           allMatches2 = m.group();
           nump.add(a);
       } 
       a++; 
   }
  
 
 
   return allMatches2;
 
 }
 
 private static String FindPJ(String[] test ) {
 
 
   String[] filename=null;
   String allMatches2 = "";
   List<Integer> nump =new ArrayList<Integer>();
   int a=0;
   for (String lines : test) {
           Matcher m = Pattern.compile("N[°O][ P][PJ][J ]...........")
           .matcher(lines);
           while (m.find()) {
           allMatches2 = m.group();
           nump.add(a);
       } 
       a++; 
   }
  
 
   String[] testss = allMatches2.split("=");
  allMatches2= testss[1].replace(" ", "");
    return allMatches2;
 
 }
 
 
 private static String[] extractText( PdfReader reader,int page ) {
 
     
   StringBuilder output = new StringBuilder();
   try {
     
       output.append( PdfTextExtractor.getTextFromPage( reader, page ) );
 
     
   } catch( Exception e ) {
     System.err.println( "PDFParser.extractText(): " + e.getMessage() );
   }
   String[] output2 = output.toString().split("\\r?\\n");
 
   return output2;
 }
 
 
 
}
