package com.aspose.barcode.examples.barcode.advanced_features;

import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.ChecksumValidation;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.examples.Utils;

public class ApplyingChecksumValidation 
{
	public static void main(String[] args) 
        {

		// ExStart:ApplyingChecksumValidation

		// The path to the resource directory.
		String dataDir = Utils.getDataDir(ApplyingChecksumValidation.class) + "Barcode/AdvancedFeatures/";

                //Generation
                //Instantiate BarCodeBuilder object
                com.aspose.barcode.BarCodeBuilder builder = new com.aspose.barcode.BarCodeBuilder();
        
                //Set the Code text for the barcode
                builder.setCodeText("1234567890");
        
                //Set the symbology type to CodaBar
                builder.setEncodeType(com.aspose.barcode.EncodeTypes.CODABAR);
        
                //Set the EnableChecksum property to yes
                builder.setEnableChecksum(com.aspose.barcode.EnableChecksum.Yes);
        
                //Set the CodabarChecksumMode
                builder.setCodabarChecksumMode(com.aspose.barcode.CodabarChecksumMode.Mod10);
        
                //Save the image on the system
                builder.save("Codabar_Mod10.png");

                //Recognition
                //Initialize reader object
                BarCodeReader reader = new BarCodeReader("Codabar_Mod10.png", com.aspose.barcode.barcoderecognition.DecodeType.CODABAR);
        
                //Set ChecksumValidation property of the reader to On
                reader.setChecksumValidation(com.aspose.barcode.barcoderecognition.ChecksumValidation.On);
                while (reader.read())
                {
                     //Get code text
                     System.out.println(" codetext: " + reader.getCodeText());
            
                     //Get type of barcode
                     System.out.println(" type: " + reader.getCodeType());
            
                    //Get checksum value
                    System.out.println(" Checksum: " + reader.getCheckSum());
                }		

		// ExEnd:ApplyingChecksumValidation
	}
}
