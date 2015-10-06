<?php
namespace Aspose\Barcode\WorkingWith2DBarcodes\Basic2DBarcodeFeatures;

use com\aspose\barcode\BarCodeBuilder as BarCodeBuilder;
use com\aspose\barcode\Symbology as Symbology;

class CreatingPdf417Barcode{

    public static function run($dataDir=null){

        # Instantiate barcode object
        $builder = new BarCodeBuilder();

        $symbology=new Symbology();
        $builder->setSymbologyType($symbology->Pdf417);

        $builder->setCodeText("12345");

        # Save the image to your system and set its image format to Jpeg
        $builder->save($dataDir . "CreatingPdf417Barcode.jpg");

        # Display Status
        print "Created Pdf417 Barcode Successfully.".PHP_EOL;

    }


}