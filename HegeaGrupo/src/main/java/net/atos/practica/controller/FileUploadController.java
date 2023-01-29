package net.atos.practica.controller;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;




import org.springframework.stereotype.Component;



import java.io.File;

import java.io.InputStream;
import java.nio.file.Files;
 


import javax.servlet.http.Part;



@ManagedBean
@Component
public class FileUploadController extends java.lang.Object{
	
		 private String fileName;
		 private Part uploadedFile;
		 private String folder = "c:\\";
		 
		 public Part getUploadedFile() {
		 return uploadedFile;
		 }
		 
		 public void setUploadedFile(Part uploadedFile) {
		 this.uploadedFile = uploadedFile;
		 }
		 @PostConstruct
			public void OnInit() throws FileNotFoundException, Exception {
				
				
			
			}
		 
		 
		 public void saveFile() throws FileNotFoundException, Exception{
		 
		 try (InputStream input = uploadedFile.getInputStream()) {
		 fileName = uploadedFile.getSubmittedFileName();
		         Files.copy(input, new File(folder, fileName).toPath());
		     }
		     catch (IOException e) {
		         e.printStackTrace();
		     }
		 excel();
		 }
	
	 
	public void excel() throws FileNotFoundException, Exception {
		
		Workbook workbook = WorkbookFactory.create(new FileInputStream("C:\\Desktop\\hegeaRetocado\\hegeaRetocado3\\src\\main\\webapp\\pages\\admin\\upload\\carga_parcial.xlsx"));
	// Getting the Sheet at index zero
    Sheet sheet = workbook.getSheetAt(0);

    // Create a DataFormatter to format and get each cell's value as String
    DataFormatter dataFormatter = new DataFormatter();

    // 1. You can obtain a rowIterator and columnIterator and iterate over them
    System.out.println("\n\nIterating over Rows and Columns using Iterator\n");
    Iterator<org.apache.poi.ss.usermodel.Row> rowIterator = sheet.rowIterator();
    while (rowIterator.hasNext()) {
        org.apache.poi.ss.usermodel.Row row = rowIterator.next();

        // Now let's iterate over the columns of the current row
        Iterator<org.apache.poi.ss.usermodel.Cell> cellIterator = row.cellIterator();

        while (cellIterator.hasNext()) {
            org.apache.poi.ss.usermodel.Cell cell = cellIterator.next();
            String cellValue = dataFormatter.formatCellValue(cell);
            System.out.print(cellValue + "\t");
        }
        System.out.println();
    }
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
