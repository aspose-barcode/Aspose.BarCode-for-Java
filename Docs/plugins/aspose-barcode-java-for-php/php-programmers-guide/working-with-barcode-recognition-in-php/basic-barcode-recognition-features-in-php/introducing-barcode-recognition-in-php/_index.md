---
title: Introducing Barcode Recognition in PHP
type: docs
weight: 10
url: /java/introducing-barcode-recognition-in-php/
---

## **Aspose.BarCode - Barcode Recognition**
To Recognize Barcode using **Aspose.Barcode Java for PHP**, simply invoke **RecognizeBarcode** module. Here you can see example code.

**PHPCode**

{{< highlight php >}}

 # initialize barcode reader

$img = $dataDir . "barcode.jpg";

$barcode_reader_type = new BarCodeReadType();

$rd = new BarCodeReader($img, $barcode_reader_type->Code39Standard);

\# read barcode

while (java_values($rd->read())) {

\# print the code text, if barcode found

print "CodeText: " . (string)$rd ->getCodeText().PHP_EOL;

\# print the symbology type

print "Type: " . (string)$rd ->getReadType().PHP_EOL;

break;

}

{{< /highlight >}}
## **Download Running Code**
Download **Barcode Recognition (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose.BarCode-for-Java_for_PHP/src/aspose/barcode/WorkingWithBarcodeRecognition/BasicBarcodeRecognitionFeatures/RecognizeBarcode.php)
- [CodePlex](https://asposebarcodejavaphp.codeplex.com/SourceControl/latest#src/aspose/barcode/WorkingWithBarcodeRecognition/BasicBarcodeRecognitionFeatures/RecognizeBarcode.php)
