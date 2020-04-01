package com.aspose.barcode.examples.GenerateBarcode;

import java.io.IOException;

import com.aspose.barcode.generation.CodeLocation;
import com.aspose.barcode.EncodeTypes;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.AutoSizeMode;
import com.aspose.barcode.generation.BarcodeGenerator;

public class GenerateCode16KBarCode {

	public static void main(String[] args) throws IOException {
		String dataDir = Utils.getDataDir(GroupingPropertiesByBarcodeType.class) + "GenerateBarcode/";

		// ExStart: GenerateCode16KBarCode
		BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.CODE_16_K);
		generator.getParameters().setAutoSizeMode(AutoSizeMode.NEAREST);
		generator.getParameters().getImageWidth().setPixels(100);
		generator.getParameters().getBarcode().getCodeTextParameters().setLocation(CodeLocation.NONE);
		generator.save(dataDir + "Code16K_Nearest.png");
		// ExEnd: GenerateCode16KBarCode
	}

}
