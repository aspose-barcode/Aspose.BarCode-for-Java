<?php
namespace Aspose\Barcode\WorkingWithBarcodeImage\BarcodeImageBasicFeatures;

use com\aspose\barcode\BarCodeBuilder as BarCodeBuilder;
use com\aspose\barcode\Symbology as Symbology;

class RotateImage{

    public static function run($dataDir=null){

        # Instantiate barcode object
        $bb = new BarCodeBuilder();

        # Set the code text of the barcode
        $bb->setCodeText("12345678");

        # Roate clockwise for 180 degree (upside down)
        $bb->setRotationAngleF(180);

        # Set the symbology type to code39
        $symbology=new Symbology();
        $bb->setSymbologyType($symbology->Code39Extended);
        $bb->save($dataDir . "Rotate.jpg");

        # Display Status.
        print "Done with image rotation, please check the output file.".PHP_EOL;
    }
}