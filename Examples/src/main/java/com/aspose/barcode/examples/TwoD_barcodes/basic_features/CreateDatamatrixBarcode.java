package com.aspose.barcode.examples.TwoD_barcodes.basic_features;

import java.io.IOException;
import java.nio.charset.Charset;

import com.aspose.barcode.BarCodeImageFormat;
import com.aspose.barcode.DataMatrixEncodeMode;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarcodeGenerator;

public class CreateDatamatrixBarcode {

	public static void main(String[] args) throws IOException {
		// The path to the resource directory.
    	String dataDir = Utils.getDataDir(CreateDatamatrixBarcode.class) + "2DBarcode/BasicFeatures/";
    	
		createADataMatrixBarcode(dataDir);
		encodeModeForDataMatrix(dataDir);
		customEncodingModeForDataMatrix(dataDir);
		createDataMatrixBarcodeWithC40Encoding(dataDir);
		passCodeTextAndSymbologyTypeInConstructor(dataDir);
	}
	
	public static void createADataMatrixBarcode(String dataDir) throws IOException {
    	//ExStart: createADataMatrixBarcode
		BarcodeGenerator generator = new BarcodeGenerator(com.aspose.barcode.EncodeTypes.DATA_MATRIX, "1234567890");
		
		generator.save(dataDir + "datamatrix.bmp");
		generator.save(dataDir + "datamatrix.emf", BarCodeImageFormat.EMF);
		//ExEnd: createADataMatrixBarcode
	}
	
	public static void encodeModeForDataMatrix(String dataDir) throws IOException {
    	//ExStart: encodeModeForDataMatrix
		BarcodeGenerator generator = new BarcodeGenerator(com.aspose.barcode.EncodeTypes.DATA_MATRIX, "This is the data to be encoded");
		generator.getParameters().getBarcode().getDataMatrix().setDataMatrixEncodeMode(DataMatrixEncodeMode.ASCII);
		
		generator.save(dataDir + "encodeModeForDataMatrix.bmp");
		//ExEnd: encodeModeForDataMatrix
	}
	
	public static void customEncodingModeForDataMatrix(String dataDir) throws IOException {
    	//ExStart: customEncodingModeForDataMatrix
		BarcodeGenerator generator = new BarcodeGenerator(com.aspose.barcode.EncodeTypes.DATA_MATRIX, "Г¶Г¤ГјГ©Г Г�?");
		generator.getParameters().getBarcode().getDataMatrix().setDataMatrixEncodeMode(DataMatrixEncodeMode.CUSTOM);
		
		generator.getParameters().getBarcode().getDataMatrix().setCodeTextEncoding(Charset.forName("UTF-8"));

		generator.save(dataDir + "output_Utf8.bmp", BarCodeImageFormat.BMP);
		//ExEnd: customEncodingModeForDataMatrix
	}


	public static void createDataMatrixBarcodeWithC40Encoding(String dataDir) throws IOException {
    	//ExStart: createDataMatrixBarcodeWithC40Encoding
	    // Create an instance of BarCodeGenerator class
        // Set codetext value and EncodeType
		
		BarcodeGenerator generator = new BarcodeGenerator(com.aspose.barcode.EncodeTypes.DATA_MATRIX, "ABCDEF123456");

        // Set the DataMatrix encoding mode to C40
		generator.getParameters().getBarcode().getDataMatrix().setDataMatrixEncodeMode(DataMatrixEncodeMode.C40);

        // Save the barcode image
		generator.save(dataDir + "dataMatrixC40.png");
		//ExEnd: createDataMatrixBarcodeWithC40Encoding
	}
	
	public static void passCodeTextAndSymbologyTypeInConstructor(String dataDir) throws IOException{
		//ExStart: passCodeTextAndSymbologyTypeInConstructor
		// For complete examples and data files, please go to https://github.com/aspose-barcode/Aspose.BarCode-for-Java
		BarcodeGenerator generator = new BarcodeGenerator(com.aspose.barcode.EncodeTypes.DATA_MATRIX, "1234567890");
		generator.save(dataDir + "temp.png", com.aspose.barcode.BarCodeImageFormat.PNG);
		//ExEnd: passCodeTextAndSymbologyTypeInConstructor
	}
}
