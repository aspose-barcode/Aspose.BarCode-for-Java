package com.aspose.barcode.examples.barcode_recognition.advanced_features;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.Code128DataPortion;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.examples.ApplyALicense;
import com.aspose.barcode.examples.Utils;

public class IdentifyTypeOfCode128 {
	public static void main(String[] args) throws Exception {
		ApplyALicense.applyALicense();

		// ExStart: IdentifyTypeOfCode128
		// The path to the resource directory.
		String dataDir = Utils.getDataDir(UsingManualHintsToOptimizeScan.class) + "BarcodeReader/advanced_features/";

		// Create an instance of BarCodeReader class
		// Set file path
		// Set the recognition type
		BarCodeReader reader = new BarCodeReader(dataDir + "1_1.png", DecodeType.CODE_128);

		// Perform read operation
        for (BarCodeResult result : reader.readBarCodes())
        {
            // Create an array of Code128DataPortion class
            // Call the GetCode128DataPortions method
            Code128DataPortion[] code128DataPortions = result.getExtended().getCode128().getCode128DataPortions();

            // Execute Loop for each Code128DataPortion instance
            for (Code128DataPortion code128DataPortion : code128DataPortions)
            {
            	// Display the subtype and data
    			System.out.println("Code128SubType: " + code128DataPortion.getCode128SubType());
    			System.out.println("Data:" + code128DataPortion.getData());
            }
        }
		// ExEnd: IdentifyTypeOfCode128
	}

}
