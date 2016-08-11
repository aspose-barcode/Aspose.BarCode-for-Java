package com.aspose.barcode.examples.barcode.basic_features;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.CodeLocation;
import com.aspose.barcode.MarginsF;
import com.aspose.barcode.Symbology;
import com.aspose.barcode.examples.Utils;

public class CodeTextLocation {

	public static void main(String[] args) {
		
		// The path to the resource directory.
    	String dataDir = Utils.getDataDir(SpecifySymbology.class) + "Barcode/BasicFeatures/";
    	
		BarCodeBuilder builder = new BarCodeBuilder("GTIN:898978777776665655 " + "UID: 121212121212121212 " + "Batch:GH768 " + "Exp.Date:150923", Symbology.DataMatrix);
		builder.setCodeLocation(CodeLocation.Right);
		builder.setMargins(new MarginsF(0, 0, 0, 0));
		
		builder.save(dataDir + "codetextRight.png");
	}

}
