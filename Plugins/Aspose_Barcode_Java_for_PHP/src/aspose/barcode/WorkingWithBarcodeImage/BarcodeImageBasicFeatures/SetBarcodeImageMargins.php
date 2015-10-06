<?php
namespace Aspose\Barcode\WorkingWithBarcodeImage\BarcodeImageBasicFeatures;

use com\aspose\barcode\Symbology as Symbology;
use com\aspose\barcode\BarCodeBuilder as BarCodeBuilder;

class SetBarcodeImageMargins{

    public static function run($dataDir=null){

        # Instantiate barcode object
        $symbology = new Symbology();
        $bb = new BarCodeBuilder("12345678", $symbology->Code128);

        # sets the top margin
        $bb->getMargins()->setTop(4);

        # sets the bottom margin
        $bb->getMargins()->setBottom(5);

        # sets the left margin
        $bb->getMargins()->setLeft(2);

        # sets the right margin
        $bb->getMargins()->setRight(3);

        $bb->save($dataDir . "SetBarcodeImageMargins.jpg");

        # Display Status.
        print "Set Barcode Image Margins, please check the output file.".PHP_EOL;
    }
}