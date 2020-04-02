package com.aspose.barcode.examples;

import com.aspose.barcode.License;

public class ApplyALicense {
	
	public static void applyALicense() throws Exception {
		//ExStart: ApplyALicense
		// The path to the resource directory.
		String dataDir = Utils.getDataDir(ApplyALicense.class) + "License/";

		// Apply a license to avoid the evaluation WaterMark in the BarCode image. 
		License license = new License();
		license.setLicense("Aspose.Total.Product.Family.lic");
		//ExEnd: ApplyALicense
	}
}
