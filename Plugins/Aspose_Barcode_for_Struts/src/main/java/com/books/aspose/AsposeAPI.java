package com.books.aspose;

import javax.servlet.ServletOutputStream;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.BarCodeImageFormat;
import com.aspose.barcode.CodeLocation;

/*
 * @author: Adeel Ilyas
 * Company: Aspose Pty Ltd.
 *
 */
public class AsposeAPI {
	public static void createAsposeBarCode(String billAmount,
			ServletOutputStream out, String symbology) {

		BarCodeBuilder bb = new BarCodeBuilder();

		// Set up code text (data to be encoded)
		bb.setCodeText(billAmount);

		// Set up code text color
		bb.setCodeTextColor(java.awt.Color.RED);

		// Set the location of the code text to above the barcode
		bb.setCodeLocation(CodeLocation.Above);

		// Increase the space between code text and barcode to 1 point
		bb.setCodeTextSpace(1.0f);

		// Set the symbology type
		bb.setSymbologyType(Long.valueOf(symbology));

		bb.save(out, BarCodeImageFormat.Png);

	}

}
