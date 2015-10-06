<?php
namespace Aspose\Barcode\WorkingWith2DBarcodes\Utility2DBarcodeFeatures;

use com\aspose\barcode\BarCodeBuilder as BarCodeBuilder;
use com\aspose\barcode\Symbology as Symbology;


class GenerateMultipleMacroPdf417Barcodes{

    public static function run($dataDir=null){

        # Instantiate barcode object
        $builder = new BarCodeBuilder();

        $symbology=new Symbology();
        $builder->setSymbologyType($symbology->MacroPdf417);

        # Create array for storing multiple barcodes
        $i = 1;
        $size = 4;
        $list_code_text = array("code-1", "code-2", "code-3", "code-last");
        $str_file_id = 1;

        # Check the listbox for getting codetext and generating the barcodes
        for ($i = 1; $i<=$size; $i++){
        $builder->setCodeText($list_code_text[$i - 1]);
        # fileID should be same for all the generated barcodes
        $builder->setMacroPdf417FileID($str_file_id);

        # Assign segmentID in increasing order (1,2,3,....)
        $builder->setMacroPdf417SegmentID($i);

        # Save the barcode (fileid_segmentid.png)
        $builder->save($dataDir . "#{$i}.png");
        }
        # Display Status
        print "Saved Images Successfully.".PHP_EOL;

    }

}