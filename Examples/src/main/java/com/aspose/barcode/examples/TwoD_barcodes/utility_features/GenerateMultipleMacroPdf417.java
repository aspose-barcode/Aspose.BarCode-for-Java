package com.aspose.barcode.examples.TwoD_barcodes.utility_features;

import java.io.IOException;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarCodeGenerator;

public class GenerateMultipleMacroPdf417 
{

	public static void main(String[] args) throws IOException 
        {
		String dataDir = Utils.getDataDir(GenerateMultipleMacroPdf417.class) + "2DBarcode/UtilityFeatures/";
		
		BarCodeGenerator generator = new BarCodeGenerator(com.aspose.barcode.EncodeTypes.MACRO_PDF_417);

		// Create array for storing multiple barcodes
		int nSize = 4;
		String[] lstCodeText = new String[] {"code-1", "code-2", "code-3", "code-last"};
		String strFileID = "1";

		// Check the listbox for getting CodeText and generating the Barcodes
		for (int nCount = 1; nCount <= nSize; nCount++) {
			generator.setCodeText(lstCodeText[nCount - 1]);
			
			// fileID should be same for all the generated barcodes
			generator.getPdf417().setMacroFileID(Integer.parseInt(strFileID));
			
			// Assign segmentID in increasing order (1,2,3,....)
			generator.getPdf417().setMacroSegmentID(nCount);

			// Save the barcode (fileid_segmentid.png)
			generator.save(dataDir + strFileID + "_" + nCount + ".png");
		}
	}
}
