<?php
namespace Aspose\Barcode\WorkingWithBarcode\AdvanceBarcodeFeatures;
use com\aspose\barcode\BarCodeBuilder as BarCodeBuilder;
use com\aspose\barcode\Symbology as Symbology;

class SetBarsHeight{

    public static function run($dataDir=null){


        $bb = new BarCodeBuilder();

        # Set up code text (data to be encoded)
        $bb->setCodeText("1234567");

        # Set the symbology type to Code128
        $symbology=new Symbology();
        $bb->setSymbologyType($symbology->Code128);

        # Set the bar height to be 3 milimeter
        $bb->setBarHeight(3.0);
        $bb->save($dataDir . "barcode3.jpg");

        # Set the bar height to be 7 milimeter
        $bb->setBarHeight(7.0);
        $bb->save($dataDir . "barcode7.jpg");

        # Set the bar height to be 11 milimeter
        $bb->setBarHeight(11.0);
        $bb->save($dataDir . "barcode11.jpg");

        # Display Status.
        print "Barcode with different heights has been created successfully.".PHP_EOL;

    }


}