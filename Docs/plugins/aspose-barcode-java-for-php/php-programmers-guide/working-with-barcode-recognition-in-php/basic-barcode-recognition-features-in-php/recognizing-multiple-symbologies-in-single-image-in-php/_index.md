---
title: Recognizing Multiple Symbologies in Single Image in PHP
type: docs
weight: 20
url: /java/recognizing-multiple-symbologies-in-single-image-in-php/
---

## **Aspose.BarCode - Recognizing Multiple Symbologies in Single Image**
To Recognize Multiple Symbologies in Single Image using **Aspose.Barcode Java for PHP**, simply invoke **RecognizeMultipleSymbologies** module. Here you can see example code.

**PHPCode**

{{< highlight php >}}

 # initialize barcode reader

$img = $dataDir . "symbologies.png";

$barcode_reader_type = new BarCodeReadType();

$rd = new BarCodeReader($img, $barcode_reader_type->Code39Standard);//Code39Standard  AllSupportedTypes

\# read barcode

while(java_values($rd->read())){

\# print the code text, if barcode found

print "CodeText: " . (string)$rd->getCodeText().PHP_EOL;

\# print the symbology type

print "Type: " . (string)$rd->getReadType().PHP_EOL;

}

{{< /highlight >}}
## **Download Running Code**
Download **Recognizing Multiple Symbologies in Single Image (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose.BarCode-for-Java_for_PHP/src/aspose/barcode/WorkingWithBarcodeRecognition/BasicBarcodeRecognitionFeatures/RecognizeMultipleSymbologies.php)
- [CodePlex](https://asposebarcodejavaphp.codeplex.com/SourceControl/latest#src/aspose/barcode/WorkingWithBarcodeRecognition/BasicBarcodeRecognitionFeatures/RecognizeMultipleSymbologies.php)
