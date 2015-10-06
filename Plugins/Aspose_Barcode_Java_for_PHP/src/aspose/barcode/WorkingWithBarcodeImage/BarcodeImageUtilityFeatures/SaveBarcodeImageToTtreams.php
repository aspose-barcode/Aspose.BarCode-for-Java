<?php
namespace Aspose\Barcode\WorkingWithBarcodeImage\BarcodeImageUtilityFeatures;

use com\aspose\barcode\BarCodeBuilder as BarCodeBuilder;
use com\aspose\barcode\Symbology as Symbology;
use com\aspose\barcode\BarCodeImageFormat as BarCodeImageFormat;

use java\io\ByteArrayOutputStream as ByteArrayOutputStream;
class SaveBarcodeImageToTtreams{

    public static function run($dataDir=null){

        # Instantiate barcode object
        $builder = new BarCodeBuilder();

        # Set symbology of the barcode
        $symbology=new Symbology();
        $builder->setSymbologyType($symbology->Code128);

        # Set code text
        $builder->setCodeText("123456");

        # Initialize ByteArrayOutputStream object
        $out_stream = new ByteArrayOutputStream();

        # Save barcode image
        $barCodeImageFormat=new BarCodeImageFormat();
        $builder->save($out_stream, $barCodeImageFormat->Jpeg);

        # Display Status.
        print "Save Barcode Image to Streams, please check the output file.".PHP_EOL;
    }

}