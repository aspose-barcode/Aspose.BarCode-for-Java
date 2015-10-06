<?php

namespace Aspose\Barcode\WorkingWithBarcode\AdvanceBarcodeFeatures;

use com\aspose\barcode\BarCodeBuilder as BarCodeBuilder;
use com\aspose\barcode\Symbology as Symbology;

class ManageDimension{

    public static function run($dataDir=null){


        # Instantiate barcode object
        $bb = new BarCodeBuilder();

        # Set up code text (data to be encoded)
        $bb->setCodeText("1234567");

        # Set the symbology type to Code128
        $symbology=new Symbology();
        $bb->setSymbologyType($symbology->Code128);

        # Save the image to file
        $bb->save($dataDir . "barcode.jpg");

        # Set the x-dimension for the bars of the barcode
        $bb->setxDimension(0.5);
        # Save the image to file
        $bb->save($dataDir . "barcodeXDimensionChanged.jpg");


        # Instantiate barcode object
        $bb1 = new BarCodeBuilder();

        # Set the code text of the barcode
        $bb1->setCodeText("12345678");

        # Set the symbology type to code128
        $bb1->setSymbologyType($symbology->Pdf417);

        # Set the x-dimension for the bars of the barcode
        $bb1->setxDimension(0.5);

        # Save the image to file
        $bb1->save($dataDir . "barcodeYDimensionChanged.jpg");

        # Display Status.
        print "BarCodes with different dimensions have been created successfully.".PHP_EOL;

    }

}