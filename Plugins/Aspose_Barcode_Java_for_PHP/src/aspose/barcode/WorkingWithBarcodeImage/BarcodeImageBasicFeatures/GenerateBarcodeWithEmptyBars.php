<?php
namespace Aspose\Barcode\WorkingWithBarcodeImage\BarcodeImageBasicFeatures;

use com\aspose\barcode\BarCodeBuilder as BarCodeBuilder;
use com\aspose\barcode\Symbology as Symbology;

class GenerateBarcodeWithEmptyBars{

    public static function run($dataDir=null){

        # Instantiate barcode object
        $symbology = new Symbology();
        $bb = new BarCodeBuilder("TEXT", $symbology->Code128);

        # Set the FilledBars property to false
        $bb->setFilledBars(false);

        $bb->save($dataDir . "GenerateBarcodeWithEmptyBars.jpg");

        # Display Status.
        print "Generate Barcode With Empty Bars, please check the output file.".PHP_EOL;
    }

}