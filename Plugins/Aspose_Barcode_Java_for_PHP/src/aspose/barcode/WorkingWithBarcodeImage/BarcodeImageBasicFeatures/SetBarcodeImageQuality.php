<?php
namespace Aspose\Barcode\WorkingWithBarcodeImage\BarcodeImageBasicFeatures;

use com\aspose\barcode\BarCodeBuilder;
use com\aspose\barcode\ImageQualityMode as ImageQualityMode;
use com\aspose\barcode\Symbology as Symbology;

class SetBarcodeImageQuality{

    public static function run($dataDir=null){

        # Instantiate barcode object
        $bb = new BarCodeBuilder();

        # Set the code text of the barcode
        $bb->setCodeText("12345678");

        # Set the graphics drawing hint to be Anti Alias
        $imageQualityMode=new ImageQualityMode();
        $bb->setImageQuality($imageQualityMode->AntiAlias);

        # Set the symbology type to code39
        $symbology=new Symbology();
        $bb->setSymbologyType($symbology->Code39Extended);

        $bb->save($dataDir . "SetBarcodeImageQuality.jpg");

        # Display Status.
        print "Set Barcode Image Quality, please check the output file.".PHP_EOL;
    }
}