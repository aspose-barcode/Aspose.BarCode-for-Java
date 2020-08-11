---
title: Switch Barcode Recognition Modes According to the Requirement in PHP
type: docs
weight: 60
url: /java/switch-barcode-recognition-modes-according-to-the-requirement-in-php/
---

## **Aspose.BarCode - Switch Barcode Recognition Modes According to the Requirement**
To Switch Barcode Recognition Modes According to the Requirement using **Aspose.Barcode Java for PHP**, simply invoke **SwitchBarcodeRecognitionModes** module. Here you can see example code.

**PHPCode**

{{< highlight php >}}

 $img = $dataDir . "barcode.jpg";

\# initialize barcode reader

$barcode_reader_type = new BarCodeReadType();

$reader = new BarCodeReader($img, $barcode_reader_type->Code39Standard);

\# Set recognition mode

$recognitionMode=new RecognitionMode();

$reader->setRecognitionMode($recognitionMode->ManualHints);

\# Set manual hints

$manualHint=new ManualHint();

$reader->setManualHints($manualHint->InvertImage);

$reader->setManualHints($manualHint->IncorrectBarcodes);

\# Call read method

while (java_values($reader->read())) {

print "Barcode CodeText: " . (string)$reader->getCodeText().PHP_EOL;

}

\# Close reader

$reader->close();

{{< /highlight >}}
## **Download Running Code**
Download **Switch Barcode Recognition Modes According to the Requirement (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_PHP/src/aspose/barcode/WorkingWithBarcodeRecognition/AdvancedBarcodeRecognitionFeatures/SwitchBarcodeRecognitionModes.php)
- [CodePlex](https://asposebarcodejavaphp.codeplex.com/SourceControl/latest#src/aspose/barcode/WorkingWithBarcodeRecognition/AdvancedBarcodeRecognitionFeatures/SwitchBarcodeRecognitionModes.php)
