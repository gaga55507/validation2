package com.edencoding;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;

import com.edencoding.controllers.MainViewController;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import javafx.application.Platform;
import javafx.scene.control.ProgressBar;

public class E1224 {
    static String J20;
    public static void main(String[] args) throws InvalidPasswordException, IOException {
     PdfReader pdfReader = new PdfReader("E1204.pdf");
    int page = pdfReader.getNumberOfPages();
 
    String[][] E1204= new String[page][3];
    for (int x=1;x <=page;x++) {
     String[] test =extractText(pdfReader, x);
     String pj = FindPJ(test);
     E1204[x-1][0]=Integer.toString(x);
     E1204[x-1][1]= pj;
     String PC= FindPC(test);
     E1204[x-1][2]= PC;
    
    }
      
 for (String[] tes : E1204) {

     System.out.println("la page " + tes[0]+"  " + tes[1] + " " + tes[2]);
   
 }



 PdfReader pdfReader1 = new PdfReader("E1201.pdf");

 String [][] E1201 = E1221.starte1201(pdfReader1);
 
 PDFMergerUtility pdfmerger = new PDFMergerUtility();
 File filepdf = new File ("E1204.pdf");
 PDDocument document = PDDocument.load(filepdf);
 File filepdfE1221 = new File ("E1201.pdf");
 PDDocument documentE1221 = PDDocument.load(filepdfE1221);

 List <String> array = new ArrayList<String>();
 for (String[] li : E1204) {
    array.add(li[1]);
     
 }
 array = removeDuplicates(array);
int x=1;
 for (String inst : array) {
    PDDocument documents = new PDDocument();
    List<PDPage> e1204 =e1204(E1204, inst, document);
    Collections.reverse(e1204);
    List<PDPage> e1201 =e1201(E1201, inst, documentE1221);
for (PDPage pdfe1204 : e1204) {
    documents.importPage(pdfe1204);
}

for (PDPage pdfe1201 : e1201) {
    documents.importPage(pdfe1201);
}
String pcval="";
for (String [] lin : E1204) {
  if (lin[1].equals(inst)) {
  pcval= lin[2];
    
  }   
}
MainViewController mainViewController = MainViewController.getInstance();
com.edencoding.E1204.LabelTotal(array.size(),mainViewController);

if (pcval.equals("")) {
  File f= new File(Configuration.getConfigurationValue("RESULTATPDF")+ "/"+inst +".pdf" );
  FileOutputStream fOut = new FileOutputStream(f);  
  Thread updateProgressBarThread = new Thread(() -> {
    for (double progress = 0.1; progress <= 1.0; progress += 0.1) {
        try {
            Thread.sleep(100); // attend 100 millisecondes avant de mettre à jour la barre de progression
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        updateProgressBar(progress,mainViewController);
    }
  });
  updateProgressBarThread.start();
  documents.save(fOut);
}
if (!pcval.equals("")) {
  File f= new File(Configuration.getConfigurationValue("RESULTATPDF")+ "/"+pcval +".pdf" );
  FileOutputStream fOut = new FileOutputStream(f);  
  Thread updateProgressBarThread = new Thread(() -> {
    for (double progress = 0.1; progress <= 1.0; progress += 0.1) {
        try {
            Thread.sleep(100); // attend 100 millisecondes avant de mettre à jour la barre de progression
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        updateProgressBar(progress,mainViewController);
    }
  });
  updateProgressBarThread.start();

  documents.save(fOut);
}

documents.close();
 }
 


      
 }
 public static void updateProgressBar(double progress, MainViewController mainViewController) {

  Platform.runLater(() -> {
    ProgressBar progressBar = mainViewController.getProgressBar();
    double currentProgress = progressBar.getProgress();
    if (progress > currentProgress) {
        progressBar.setProgress(progress);
    }
});
    
}
 private static List<PDPage> e1204(String [][] E1204,String inst2,PDDocument document) {

    List<PDPage> pdf = new ArrayList<PDPage>();

for (String[] line : E1204) {

if (line[1].equals(inst2)&& !line[2].isEmpty()) {

     PDPage pdfs = document.getPage(Integer.parseInt(line[0])-1); 
    pdf.add(pdfs);
}

if (line[1].equals(inst2)&& line[2].isEmpty()) {

  PDPage pdfs = document.getPage(Integer.parseInt(line[0])-1); 
 pdf.add(pdfs);
}

}

    return pdf;
 
     
  
  }



 private static List<PDPage> e1201(String [][] E1201,String inst2,PDDocument documentE1221) {

    List<PDPage> pdf = new ArrayList<PDPage>();

for (String[] line : E1201) {

if (line[1].equals(inst2)&& !line[2].isEmpty()) {

     PDPage pdfs = documentE1221.getPage(Integer.parseInt(line[0])-1); 
    pdf.add(pdfs);
}

    
}

    return pdf;
 
     
  
  }

public static  List<String> removeDuplicates(List<String> list)
    {
  
        // Create a new LinkedHashSet
        Set<String> set = new LinkedHashSet<>();
  
        // Add the elements to set
        set.addAll(list);
  
        // Clear the list
        list.clear();
  
        // add the elements of set
        // with no duplicates to the list
        list.addAll(set);
     
        // return the list
        return list;
    }
 
 private static String FindPJ(String[] test ) {
 
 
   String[] filename=null;
   String allMatches2 = "";
   List<Integer> nump =new ArrayList<Integer>();
   int a=0;
   for (String lines : test) {
           Matcher m = Pattern.compile("N[°O][ P][PJ][J ]............")
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

 private static String FindPC(String[] test ) {
 
 
    String[] filename=null;
    String allMatches2 = "";
    List<Integer> nump =new ArrayList<Integer>();
    int a=0;
    for (String lines : test) {
            Matcher m = Pattern.compile("N[°O][ P][PC][C ]............")
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
