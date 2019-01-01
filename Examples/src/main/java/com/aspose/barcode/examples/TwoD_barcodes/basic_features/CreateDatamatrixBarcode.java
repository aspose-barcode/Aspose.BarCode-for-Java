package com.aspose.barcode.examples.TwoD_barcodes.basic_features;

import java.io.IOException;
import java.nio.charset.Charset;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.BarCodeImageFormat;
import com.aspose.barcode.DataMatrixEncodeMode;
import com.aspose.barcode.examples.Utils;

public class CreateDatamatrixBarcode {

	public static void main(String[] args) throws IOException {
		// The path to the resource directory.
    	String dataDir = Utils.getDataDir(CreateDatamatrixBarcode.class) + "2DBarcode/BasicFeatures/";
    	
		createADataMatrixBarcode(dataDir);
		encodeModeForDataMatrix(dataDir);
		customEncodingModeForDataMatrix(dataDir);
		createDataMatrixBarcodeWithC40Encoding(dataDir);
	}
	
	public static void createADataMatrixBarcode(String dataDir) throws IOException {
    	
		BarCodeBuilder objBuilder = new BarCodeBuilder();
		objBuilder.setEncodeType(com.aspose.barcode.EncodeTypes.DATA_MATRIX);
		objBuilder.setCodeText("1234567890");
		
		objBuilder.save(dataDir + "datamatrix.bmp");
                
                objBuilder.save(dataDir + "datamatrix.emf", BarCodeImageFormat.EMF);
	}
	
	public static void encodeModeForDataMatrix(String dataDir) {
    	
		BarCodeBuilder objBuilder = new BarCodeBuilder();
		objBuilder.setEncodeType(com.aspose.barcode.EncodeTypes.DATA_MATRIX);
		objBuilder.setDataMatrixEncodeMode(DataMatrixEncodeMode.ASCII);
		objBuilder.setCodeText("This is the data to be encoded");
		
		objBuilder.save(dataDir + "encodeModeForDataMatrix.bmp");
	}
	
	public static void customEncodingModeForDataMatrix(String dataDir) throws IOException {
    	
		BarCodeBuilder objBuilder = new BarCodeBuilder();
		objBuilder.setEncodeType(com.aspose.barcode.EncodeTypes.DATA_MATRIX);
		objBuilder.setDataMatrixEncodeMode(DataMatrixEncodeMode.CUSTOM);
		objBuilder.setCodeTextEncoding(Charset.forName("UTF-8"));
		objBuilder.setCodeText("Г¶Г¤ГјГ©Г Г�?");

		objBuilder.save(dataDir + "output_Utf8.bmp", BarCodeImageFormat.BMP);
	}


	public static void createDataMatrixBarcodeWithC40Encoding(String dataDir) {
    	
	    // Create an instance of BarCodeBuilder class
            // Set codetext value and EncodeType
            com.aspose.barcode.BarCodeBuilder buidler = new com.aspose.barcode.BarCodeBuilder("ABCDEF123456", com.aspose.barcode.EncodeTypes.DATA_MATRIX);

            // Set the DataMatrix encoding mode to C40
            buidler.setDataMatrixEncodeMode(com.aspose.barcode.DataMatrixEncodeMode.C40);

            // Save the barcode image
            buidler.save("dataMatrixC40.png");
	}
}
