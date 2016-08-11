package com.aspose.barcode.examples.TwoD_barcodes.basic_features;

import java.io.IOException;
import java.nio.charset.Charset;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.BarCodeImageFormat;
import com.aspose.barcode.DataMatrixEncodeMode;
import com.aspose.barcode.Symbology;
import com.aspose.barcode.examples.Utils;

public class CreateDatamatrixBarcode {

	public static void main(String[] args) throws IOException {
		// The path to the resource directory.
    	String dataDir = Utils.getDataDir(CreateDatamatrixBarcode.class) + "2DBarcode/BasicFeatures/";
    	
		createADataMatrixBarcode(dataDir);
		encodeModeForDataMatrix(dataDir);
		customEncodingModeForDataMatrix(dataDir);
	}
	
	public static void createADataMatrixBarcode(String dataDir) {
    	
		BarCodeBuilder objBuilder = new BarCodeBuilder();
		objBuilder.setSymbologyType(Symbology.DataMatrix);
		objBuilder.setCodeText("1234567890");
		
		objBuilder.save(dataDir + "datamatrix.bmp");
	}
	
	public static void encodeModeForDataMatrix(String dataDir) {
    	
		BarCodeBuilder objBuilder = new BarCodeBuilder();
		objBuilder.setSymbologyType(Symbology.DataMatrix);
		objBuilder.setDataMatrixEncodeMode(DataMatrixEncodeMode.ASCII);
		objBuilder.setCodeText("This is the data to be encoded");
		
		objBuilder.save(dataDir + "encodeModeForDataMatrix.bmp");
	}
	
	public static void customEncodingModeForDataMatrix(String dataDir) throws IOException {
    	
		BarCodeBuilder objBuilder = new BarCodeBuilder();
		objBuilder.setSymbologyType(Symbology.DataMatrix);
		objBuilder.setDataMatrixEncodeMode(DataMatrixEncodeMode.Custom);
		objBuilder.setCodeTextEncoding(Charset.forName("UTF-8"));
		objBuilder.setCodeText("Г¶Г¤ГјГ©Г ГЁ");

		objBuilder.save(dataDir + "output_Utf8.bmp", BarCodeImageFormat.Bmp);
	}
}
