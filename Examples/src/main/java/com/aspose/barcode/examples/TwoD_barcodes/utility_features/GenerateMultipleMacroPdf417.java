package com.aspose.barcode.examples.TwoD_barcodes.utility_features;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.examples.Utils;

public class GenerateMultipleMacroPdf417 
{

	public static void main(String[] args) 
        {
		String dataDir = Utils.getDataDir(GenerateMultipleMacroPdf417.class) + "2DBarcode/UtilityFeatures/";
		
		BarCodeBuilder builder = new BarCodeBuilder();
                builder.setEncodeType(com.aspose.barcode.EncodeTypes.MACRO_PDF_417);

		// Create array for storing multiple barcodes
		int nSize = 4;
		String[] lstCodeText = new String[] {"code-1", "code-2", "code-3", "code-last"};
		String strFileID = "1";

		// Check the listbox for getting codetext and generating the barcodes
		for (int nCount = 1; nCount <= nSize; nCount++) {
			builder.setCodeText(lstCodeText[nCount - 1]);
			// fileID should be same for all the generated barcodes
			builder.setMacroPdf417FileID(Integer.parseInt(strFileID));
			// Assign segmentID in increasing order (1,2,3,....)
			builder.setMacroPdf417SegmentID(nCount);

			// Save the barcode (fileid_segmentid.png)
			builder.save(dataDir + strFileID + "_" + nCount + ".png");
		}
	}
}
