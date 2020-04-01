package com.aspose.barcode.examples.barcode_recognition.advanced_features;

import java.io.File;
import java.io.FileFilter;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.examples.ApplyALicense;
import com.aspose.barcode.examples.Utils;

//ExStart: ReadMultipleMacropdf417BarcodeImages
public class ReadMultipleMacropdf417BarcodeImages {

	public static void main(String[] args) throws Exception {
		ApplyALicense.applyALicense();

		// The path to the resource directory.
		String dataDir = Utils.getDataDir(ReadMultipleMacropdf417BarcodeImages.class)
				+ "BarcodeReader/advanced_features/";

		String strFileID = "1";
		File dir = new File(dataDir);
		File[] files = dir.listFiles(new Filter(strFileID));
		for (int nCount = 1; nCount <= files.length; nCount++) {
			// We got list of all the files, now read barcodes
			BarCodeReader reader = new BarCodeReader(files[nCount - 1].getAbsolutePath(), DecodeType.MACRO_PDF_417);
			
			for (BarCodeResult result : reader.readBarCodes())
            {
				System.out.println("File: " + files[nCount - 1].getAbsolutePath() 
                    + " == FileID: " + result.getExtended().getPdf417().getMacroPdf417FileID()
                    + " == SegmentID: " + result.getExtended().getPdf417().getMacroPdf417SegmentID()
                    + "  == CodeText: " + result.getCodeText());
            }
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
//ExEnd: ReadMultipleMacropdf417BarcodeImages