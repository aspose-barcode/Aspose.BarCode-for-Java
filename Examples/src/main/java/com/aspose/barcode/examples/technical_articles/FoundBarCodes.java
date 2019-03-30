package com.aspose.barcode.examples.technical_articles;
// ExStart: FoundBarCodes
public class FoundBarCodes {
	   String CodeText;
	   com.aspose.barcode.barcoderecognition.BarCodeRegion BarCodeRegion;
	   
	   public FoundBarCodes(String cText, com.aspose.barcode.barcoderecognition.BarCodeRegion region) {
		      this.CodeText = cText;
		      this.BarCodeRegion = region;
	   }
	   
	   public String getCodeText() {
	        return CodeText;
	   }
	   
	   public com.aspose.barcode.barcoderecognition.BarCodeRegion getRegion() {
	        return BarCodeRegion;
	   }
}
// ExEnd: FoundBarCodes