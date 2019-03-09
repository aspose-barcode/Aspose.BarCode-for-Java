package com.aspose.barcode.examples;

import java.io.InputStream;

import com.aspose.barcode.License;

public class ApplyLicenseFromStream {
	public static void ApplyLicenseFromStream() throws Exception {
		//ExStart: ApplyLicenseFromStream
		// Apply a license to avoid the evaluation WaterMark in the BarCode image. 
		License license = new License();
		license.setLicense(new java.io.FileInputStream("Aspose.BarCode.Java.lic"));
		//ExEnd: ApplyLicenseFromStream
	}
}
