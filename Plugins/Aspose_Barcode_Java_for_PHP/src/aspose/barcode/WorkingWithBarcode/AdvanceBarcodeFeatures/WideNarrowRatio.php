<?php
namespace Aspose\Barcode\WorkingWithBarcode\AdvanceBarcodeFeatures;

use com\aspose\barcode\BarCodeBuilder as BarCodeBuilder;
use com\aspose\barcode\Symbology as Symbology;


class WideNarrowRatio{

    public static function run($dataDir=null){

        # Instantiate barcode object
        $bb = new BarCodeBuilder();

        # Set the code text of the barcode
        $bb->setCodeText("12345678");

        # Set the symbology type to code39
        $symbology=new Symbology();
        $bb->setSymbologyType($symbology->Code39Extended);

        # Set the wide to narrow ratio for the barcode
        $bb->setWideNarrowRatio(3.0);
        # Save the image to file
        $bb->save($dataDir . "barcode_ratio_3.jpg");

        # Set the wide to narrow ratio for the barcode
        $bb->setWideNarrowRatio(5.0);
        # Save the image to file
        $bb->save($dataDir . "barcode_ratio_5.jpg");

        # Set the wide to narrow ratio for the barcode
        $bb->setWideNarrowRatio(7.0);
        # Save the image to file
        $bb->save($dataDir . "barcode_ratio_7.jpg");

        # Set the wide to narrow ratio for the barcode
        $bb->setWideNarrowRatio(9.0);
        # Save the image to file
        $bb->save($dataDir . "barcode_ratio_9.jpg");

        # Display Status.
        print "BarCodes with different wide narrow ratios have been created successfully.".PHP_EOL;
    }

}