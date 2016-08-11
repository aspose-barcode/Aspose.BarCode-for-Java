package com.aspose.barcode.examples.TwoD_barcodes.basic_features;

import java.io.IOException;
import java.nio.charset.Charset;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.BarCodeImageFormat;
import com.aspose.barcode.DataMatrixEncodeMode;
import com.aspose.barcode.Symbology;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.examples.barcode.basic_features.SpecifySymbology;

public class CreateDatamatrixBarcode {

	public static void main(String[] args) throws IOException {
		createADataMatrixBarcode();
		encodeModeForDataMatrix();
		customEncodingModeForDataMatrix();
	}
	
	public static void createADataMatrixBarcode() {
		// The path to the resource directory.
    	String dataDir = Utils.getDataDir(SpecifySymbology.class) + "2DBarcode/BasicFeatures/";
    	
		BarCodeBuilder objBuilder = new BarCodeBuilder();
		objBuilder.setSymbologyType(Symbology.DataMatrix);
		objBuilder.setCodeText("1234567890");
		
		objBuilder.save(dataDir + "datamatrix.bmp");
	}
	
	public static void encodeModeForDataMatrix() {
		// The path to the resource directory.
    	String dataDir = Utils.getDataDir(SpecifySymbology.class) + "2DBarcode/BasicFeatures/";
    	
		BarCodeBuilder objBuilder = new BarCodeBuilder();
		objBuilder.setSymbologyType(Symbology.DataMatrix);
		objBuilder.setDataMatrixEncodeMode(DataMatrixEncodeMode.ASCII);
		objBuilder.setCodeText("This is the data to be encoded");
		
		objBuilder.save(dataDir + "encodeModeForDataMatrix.bmp");
	}
	
	public static void customEncodingModeForDataMatrix() throws IOException {
		// The path to the resource directory.
    	String dataDir = Utils.getDataDir(SpecifySymbology.class) + "2DBarcode/BasicFeatures/";
    	
		BarCodeBuilder objBuilder = new BarCodeBuilder();
		objBuilder.setSymbologyType(Symbology.DataMatrix);
		objBuilder.setDataMatrixEncodeMode(DataMatrixEncodeMode.Custom);
		objBuilder.setCodeTextEncoding(Charset.forName("UTF-8"));
		objBuilder.setCodeText("Г¶Г¤ГјГ©Г ГЁ");

		objBuilder.save(dataDir + "output_Utf8.bmp", BarCodeImageFormat.Bmp);
	}
}
