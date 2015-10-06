<?php
namespace Aspose\Barcode\WorkingWithBarcodeImage\BarcodeImageBasicFeatures;

use com\aspose\barcode\BarCodeBuilder as BarCodeBuilder;
use com\aspose\barcode\MarginsF as MarginsF;
use java\awt\Color as Color;

class SetBarcodeImageBorders{

    public static function run($dataDir=null){

        # Instantiate barcode object
        $bb = new BarCodeBuilder();

        $margins = new MarginsF();
        # 6 milimeter space from left, right, top and bottom side of border
        $margins->setLeft(2);
        $margins->setRight(2);
        $margins->setTop(2);
        $margins->setBottom(2);

        # Set border's width to be 0.5 milimeter
        $bb->setBorderWidth(0.5);

        # Border will be visible
        $bb->setBorderVisible(true);

        # Set the border's color to red
        $color=new Color();
        $bb->setBorderColor($color->RED);

        # Set margins
        $bb->setMargins($margins);

        $bb->save($dataDir . "border.jpg");

        # Display Status.
        print "Set border margins, please check the output file.".PHP_EOL;
    }
}