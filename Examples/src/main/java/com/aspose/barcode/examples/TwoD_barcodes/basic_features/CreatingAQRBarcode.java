package com.aspose.barcode.examples.TwoD_barcodes.basic_features;

import java.io.IOException;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.BarCodeImageFormat;
import com.aspose.barcode.CodeLocation;
import com.aspose.barcode.QRErrorLevel;
import com.aspose.barcode.Symbology;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.examples.barcode.basic_features.SpecifySymbology;

public class CreatingAQRBarcode {

	public static void main(String[] args) throws IOException {
		// The path to the resource directory.
		String dataDir = Utils.getDataDir(SpecifySymbology.class) + "2DBarcode/BasicFeatures/";
				
		createAQRBarcode(dataDir);
		errorCorrection(dataDir);
		rotation(dataDir);
	}

	public static void createAQRBarcode(String dataDir) throws IOException {
		
		BarCodeBuilder b = new BarCodeBuilder();
		b.setSymbologyType(Symbology.QR);
		b.setCodeText("1234567890");

		b.save(dataDir + "QRBarcode.bmp", BarCodeImageFormat.Bmp);
	}
	
	public static void errorCorrection(String dataDir) throws IOException {
				
		BarCodeBuilder b = new BarCodeBuilder();
		b.setSymbologyType(Symbology.QR);
		b.setQRErrorLevel(QRErrorLevel.LevelH);
		b.setCodeText("1234567890");
		
		b.save(dataDir + "errorCorrectionQRBarcode.bmp", BarCodeImageFormat.Bmp);
	}
	
	public static void rotation(String dataDir) throws IOException {
				
		BarCodeBuilder b = new BarCodeBuilder();
		b.setSymbologyType(Symbology.QR);
		b.setCodeText("1234567890");

		//Hide code text
		b.setCodeLocation(CodeLocation.None);
		b.setRotationAngleF(90);
		
		b.save(dataDir + "rotation_qr.bmp", BarCodeImageFormat.Bmp);
	}
}
