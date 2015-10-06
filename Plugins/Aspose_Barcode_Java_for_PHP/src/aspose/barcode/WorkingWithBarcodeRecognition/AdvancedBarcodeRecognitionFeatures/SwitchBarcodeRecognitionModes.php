<?php
namespace Aspose\Barcode\WorkingWithBarcodeRecognition\AdvancedBarcodeRecognitionFeatures;

use com\aspose\barcoderecognition\BarCodeReadType as BarCodeReadType;
use com\aspose\barcoderecognition\BarCodeReader as BarCodeReader;
use com\aspose\barcoderecognition\RecognitionMode as RecognitionMode;
use com\aspose\barcoderecognition\ManualHint as ManualHint;

class SwitchBarcodeRecognitionModes{

    public static function run($dataDir=null){

        $img = $dataDir . "barcode.jpg";

        # initialize barcode reader
        $barcode_reader_type = new BarCodeReadType();
        $reader = new BarCodeReader($img, $barcode_reader_type->Code39Standard);

        # Set recognition mode
        $recognitionMode=new RecognitionMode();
        $reader->setRecognitionMode($recognitionMode->ManualHints);

        # Set manual hints
        $manualHint=new ManualHint();
        $reader->setManualHints($manualHint->InvertImage);
        $reader->setManualHints($manualHint->IncorrectBarcodes);

        # Call read method
        while (java_values($reader->read())) {
            print "Barcode CodeText: " . (string)$reader->getCodeText().PHP_EOL;
        }

        # Close reader
        $reader->close();
    }
}