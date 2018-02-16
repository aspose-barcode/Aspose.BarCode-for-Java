package com.aspose.barcode.examples.barcode_recognition.advanced_features;

import java.io.File;
import java.io.FileFilter;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.examples.ApplyALicense;
import com.aspose.barcode.examples.Utils;

public class ReadMultipleMacropdf417BarcodeImages {

	public static void main(String[] args) throws Exception {
		ApplyALicense.applyALicense();
		
		// The path to the resource directory.
    	String dataDir = Utils.getDataDir(ReadMultipleMacropdf417BarcodeImages.class) + "BarcodeReader/advanced_features/";
    	
		String strFileID = "1";
		File dir = new File(dataDir);
		File[] files = dir.listFiles(new Filter(strFileID));
		for (int nCount = 1 ; nCount <= files.length ; nCount++) {
		    // We got list of all the files, now read barcodes
		    BarCodeReader reader = new BarCodeReader(files[nCount-1].getAbsolutePath(), com.aspose.barcode.barcoderecognition.DecodeType.MACRO_PDF_417);
		    if (reader.read() == true) {
		        System.out.println("File: " + files[nCount-1].getAbsolutePath() + " == FileID: " + reader.getMacroPdf417FileID() +
		            " == SegmentID: " + reader.getMacroPdf417SegmentID() +
		            " == CodeText: " + reader.getCodeText());
		    }
		    reader.close();
		}
	}
}

//Create new class for filtering the files list
class Filter implements FileFilter {
	String fileID = "";

	public Filter(String fileID) {
		this.fileID = fileID;
	}
	
	@Override
	public boolean accept(File file) {
		// File names are in the format of FileID_*.png
		return file.getName().endsWith("png") && file.getName().startsWith(fileID + "_");
	}
}	