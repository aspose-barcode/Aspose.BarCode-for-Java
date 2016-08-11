package com.aspose.barcode.examples;

import com.aspose.barcode.License;

public class ApplyALicense {
	
	public static void applyALicense() throws Exception {
		// The path to the resource directory.
		String dataDir = Utils.getDataDir(ApplyALicense.class) + "License/";
		String licFilePath = dataDir + "Aspose.Total.Java.lic";

		// Apply a license to avoid the evaluation watermark in the barcode image. 
		License licBarcode = new License();
		licBarcode.setLicense(licFilePath);
	}
}
