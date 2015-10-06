<?php
namespace Aspose\Barcode\WorkingWithBarcodeImage\BarcodeImageUtilityFeatures;

use com\aspose\barcode\Symbology as Symbology;
use com\aspose\barcode\BarCodeBuilder as BarCodeBuilder;
use com\aspose\barcode\GraphicsUnit as GraphicsUnit;

class SetBarcodeImageUnitSize{
    public static function run($dataDir=null){

        # Instantiate barcode object
        $symbology = new Symbology();
        $bb = new BarCodeBuilder("12345678", $symbology->Code128);

        # Measurement is Milimeter
        $graphicsUnit=new GraphicsUnit();
        $bb->setGraphicsUnit($graphicsUnit->Point);

        # Set the bar height to 3 points
        $bb->setBarHeight(3.0);

        $bb->save($dataDir . "SetBarcodeImageUnitSize.jpg");

        # Display Status.
        print "Set Barcode Image Unit Size, please check the output file.".PHP_EOL;
    }
}