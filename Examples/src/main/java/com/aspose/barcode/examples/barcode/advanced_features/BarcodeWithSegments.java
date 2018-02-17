package com.aspose.barcode.examples.barcode.advanced_features;

import com.aspose.barcode.BarCodeBuilder;
import com.aspose.barcode.examples.Utils;

public class BarcodeWithSegments 
{

	public static void main(String[] args) 
        {
		//Instantiate BarCodeBuilder object
                com.aspose.barcode.BarCodeBuilder builder = new com.aspose.barcode.BarCodeBuilder();

                //Set the Code text for the barcode
                builder.setCodeText("(01)98898765432106(3202)012345(15)991231"); 

                //Set the symbology type to Code128
                builder.setEncodeType(com.aspose.barcode.EncodeTypes.DATABAR_EXPANDED_STACKED);

                //Set the cloumn property to define segments per row
                builder.setColumns(6);

                //Save the image
                builder.save("6segmets.png");
	}

}
