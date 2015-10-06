<?php
namespace Aspose\Barcode\WorkingWithBarcodeImage\BarcodeImageBasicFeatures;
use com\aspose\barcode\BarCodeBuilder as BarCodeBuilder;

use java\awt\Color as Color;

class SetBarcodeImageColor{

    public static function run($dataDir=null){

        # Instantiate barcode object
        $bb = new BarCodeBuilder();

        $color = new Color();

        # Set the background color of the barcode
        $bb->setBackColor($color->YELLOW);

        # Set the foreground color of the barcode
        $bb->setForeColor($color->BLUE);

        # Set border's color
        $bb->setBorderColor($color->RED);

        # Set the code text's color
        $bb->setCodeTextColor($color->RED);

        # Caption's color
        $bb->getCaptionAbove()->setForeColor($color->darkGray);
        $bb->getCaptionBelow()->setForeColor($color->CYAN);

        $bb->save($dataDir . "color.jpg");

        # Display Status.
        print "Applied color to barcode image, please check the output file.".PHP_EOL;
    }

}