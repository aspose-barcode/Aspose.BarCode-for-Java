<?php
namespace Aspose\Barcode\WorkingWithBarcodeImage\BarcodeImageUtilityFeatures;

use com\aspose\barcode\BarCodeBuilder as BarCodeBuilder;
use com\aspose\barcode\Symbology as Symbology;
use com\aspose\barcode\Resolution as Resolution;
use com\aspose\barcode\ResolutionMode as ResolutionMode;

class CustomizeBarcodeImageResolution{

    public static function run($dataDir=null){

        # Instantiate barcode object
        $bb = new BarCodeBuilder();

        # Set the Code text for the barcode
        $bb->setCodeText("1234567");

        # Set the symbology type to Code128
        $symbology=new Symbology();
        $bb->setSymbologyType($symbology->Code128);

        # Create an instance of resolution
        $resolutionMode=new ResolutionMode();
        $bb->setResolution(new Resolution(200,400,$resolutionMode->Graphics));

        $bb->save($dataDir . "CustomizeBarcodeImageResolution.jpg");

        # Display Status.
        print "Customized Barcode Image Resolution, please check the output file.".PHP_EOL;
    }
}