<?php
namespace Aspose\Barcode\WorkingWithBarcode\UtilityFeatures;

use com\aspose\barcode\BarCodeBuilder as BarCodeBuilder;
use com\aspose\barcode\CodeLocation as CodeLocation;
use com\aspose\barcode\Symbology as Symbology;
use com\aspose\barcode\BarCodeImageFormat as BarCodeImageFormat;

use java\awt\Color as Color;
class CodeText{

    public static function run($dataDir=null){

        # Set appearance of the code text
        CodeText::set_appearance($dataDir);

        # Set code text for Barcode
        CodeText::set_codetext($dataDir);

    }

public static function set_appearance($dataDir=null){

        $bb = new BarCodeBuilder();

        # Set up code text (data to be encoded)
        $bb->setCodeText("1234567");

        # Set up code text color
        $color=new Color();
        $bb->setCodeTextColor($color->RED);

        # Set the location of the code text to above the barcode
        $codeLocation=new CodeLocation();
        $bb->setCodeLocation($codeLocation->Above);

        #Increase the space between code text and barcode to 1 point
        $bb->setCodeTextSpace(1.0);

        # Set the symbology type to Code128
        $symbology=new Symbology();
        $bb->setSymbologyType($symbology->Code128);

        # Save the image to your system and set its image format to Jpeg
        $barCodeImageFormat=new BarCodeImageFormat();
        $bb->save($dataDir . "barcode.jpg", $barCodeImageFormat->Jpeg);

        # Display Status
        print "Barcode with custom appearance saved as JPEG image successfully.".PHP_EOL;

    }

public static function set_codetext($dataDir=null){

        # Instantiate barcode object
        $bb = new BarCodeBuilder();

        # Set the code text for the barcode
        $bb->setCodeText("Aspose-123");

        # Set the symbology type to Code128
        $symbology=new Symbology();
        $bb->setSymbologyType($symbology->Code128);

        # Set the width of the bars to 0.5 millimeter
        $bb->setxDimension(0.5);

        # save the barcode image to file
        $bb->save($dataDir . "codetext.out.jpg");

        # Print message
        print "Barcode image generated successfully.".PHP_EOL;

    }

}