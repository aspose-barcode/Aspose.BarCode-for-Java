---
title: Recognizing Specific Barcode Symbology in PHP
type: docs
weight: 30
url: /java/recognizing-specific-barcode-symbology-in-php/
---

## **Aspose.BarCode - Recognizing Specific Barcode Symbology**
To Recognize Specific Barcode Symbology using **Aspose.Barcode Java for PHP**, simply invoke **RecognizeSpecificSymbology** module. Here you can see example code.

**PHPCode**

{{< highlight php >}}

 # initialize barcode reader

$img = $dataDir . "barcode.jpg";

$barcode_reader_type = new BarCodeReadType();

$rd = new BarCodeReader($img, $barcode_reader_type->Code39Standard);

\# read barcode

while (java_values($rd->read())){

\# print the code text, if barcode found

print "CodeText: " . (string)$rd->getCodeText();

\# print the symbology type

print "Type: " . (string)$rd->getReadType().PHP_EOL;

}

{{< /highlight >}}
## **Download Running Code**
Download **Recognizing Specific Barcode Symbology (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose.BarCode-for-Java_for_PHP/src/aspose/barcode/WorkingWithBarcodeRecognition/BasicBarcodeRecognitionFeatures/RecognizeSpecificSymbology.php)
- [CodePlex](https://asposebarcodejavaphp.codeplex.com/SourceControl/latest#src/aspose/barcode/WorkingWithBarcodeRecognition/BasicBarcodeRecognitionFeatures/RecognizeSpecificSymbology.php)
