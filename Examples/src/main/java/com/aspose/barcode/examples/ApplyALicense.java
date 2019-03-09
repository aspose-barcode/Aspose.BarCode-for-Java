package com.aspose.barcode.examples;

import com.aspose.barcode.License;

public class ApplyALicense {
	
	public static void applyALicense() throws Exception {
		//ExStart: ApplyALicense
		// The path to the resource directory.
		String dataDir = Utils.getDataDir(ApplyALicense.class) + "License/";
		String licFilePath = "D:\\Aspose\\Licenses\\Aspose.Total.Java.lic";

		// Apply a license to avoid the evaluation WaterMark in the BarCode image. 
		License licBarcode = new License();
		licBarcode.setLicense(licFilePath);
		//ExEnd: ApplyALicense
	}
}
