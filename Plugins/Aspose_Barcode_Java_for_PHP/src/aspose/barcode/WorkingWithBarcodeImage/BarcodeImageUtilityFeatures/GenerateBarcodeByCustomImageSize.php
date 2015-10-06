<?php
namespace Aspose\Barcode\WorkingWithBarcodeImage\BarcodeImageUtilityFeatures;

use com\aspose\barcode\Symbology as Symbology;
use com\aspose\barcode\BarCodeBuilder as BarCodeBuilder;

class GenerateBarcodeByCustomImageSize{

    public static function run($dataDir=null){

        # Instantiate barcode object
        $symbology = new Symbology();
        $builder = new BarCodeBuilder("1234567890", $symbology->Code39Standard);

        # Set auto size false
        $builder->setAutoSize(false);

        # Set height
        $builder->setImageHeight(50);

        # Set width
        $builder->setImageWidth(120);

        $builder->save($dataDir . "GenerateBarcodeByCustomImageSize.jpg");

        # Display Status.
        print "Generate Barcode By Custom Image Size, please check the output file.".PHP_EOL;
    }
}