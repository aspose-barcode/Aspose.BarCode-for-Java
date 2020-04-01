package com.aspose.barcode.examples.technical_articles;

import com.aspose.barcode.barcoderecognition.BarCodeRegionParameters;

// ExStart: FoundBarCodes
public class FoundBarCodes {
	String CodeText;
	BarCodeRegionParameters BarCodeRegion;

	public FoundBarCodes(String cText, BarCodeRegionParameters barCodeRegionParameters) {
		this.CodeText = cText;
		this.BarCodeRegion = barCodeRegionParameters;
	}

	public String getCodeText() {
		return CodeText;
	}

	public com.aspose.barcode.barcoderecognition.BarCodeRegionParameters getRegion() {
		return BarCodeRegion;
	}
}
// ExEnd: FoundBarCodes