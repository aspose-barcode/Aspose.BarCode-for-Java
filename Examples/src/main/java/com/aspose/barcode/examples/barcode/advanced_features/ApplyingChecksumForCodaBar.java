package com.aspose.barcode.examples.barcode.advanced_features;

import java.io.IOException;

import com.aspose.barcode.CodabarChecksumMode;
import com.aspose.barcode.EnableChecksum;
import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.ChecksumValidation;
import com.aspose.barcode.examples.Utils;
import com.aspose.barcode.generation.BarCodeGenerator;

public class ApplyingChecksumForCodaBar 
{
	public static void main(String[] args) throws IOException 
    {

		// ExStart:ApplyingChecksumValidation

		// The path to the resource directory.
		String dataDir = Utils.getDataDir(ApplyingChecksumValidation.class) + "Barcode/AdvancedFeatures/";

        //Generation
        //Instantiate BarCodeGenerator object
        BarCodeGenerator generator = new BarCodeGenerator(com.aspose.barcode.EncodeTypes.CODABAR, "1234567890");
        
        //Set the EnableChecksum property to yes
        generator.getChecksum().setEnable(EnableChecksum.YES);
        
        //Set the CodabarChecksumMode
        generator.getCodabar().setChecksumMode(CodabarChecksumMode.MOD_10);
        
        //Save the image on the system
        generator.save("Codabar_Mod10.png");

        //Recognition
        //Initialize reader object
        BarCodeReader reader = new BarCodeReader("Codabar_Mod10.png", com.aspose.barcode.barcoderecognition.DecodeType.CODABAR);
        
        //Set ChecksumValidation property of the reader to On
        reader.setChecksumValidation(ChecksumValidation.ON);
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
