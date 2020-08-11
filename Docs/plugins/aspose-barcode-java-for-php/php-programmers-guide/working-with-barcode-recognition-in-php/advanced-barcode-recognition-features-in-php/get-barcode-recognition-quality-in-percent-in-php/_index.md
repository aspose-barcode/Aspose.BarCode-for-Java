---
title: Get BarCode Recognition Quality in Percent in PHP
type: docs
weight: 20
url: /java/get-barcode-recognition-quality-in-percent-in-php/
---

## **Aspose.BarCode - Get BarCode Recognition Quality in Percent**
To Get BarCode Recognition Quality in Percent using **Aspose.Barcode Java for PHP**, simply invoke **GetBarcodeRecognitionQuality** module. Here you can see example code.

**PHPCode**

{{< highlight php >}}

 $img = $dataDir . "barcode.jpg";

\# initialize barcode reader

$barcode_reader_type = new BarCodeReadType();

$reader = new BarCodeReader($img, $barcode_reader_type->Code39Standard);

\# Call read method

while (java_values($reader->read())){

print "Barcode CodeText: " . (string)$reader->getCodeText(). " Barcode Type: ".(string)$reader->getReadType().PHP_EOL;

$percent = $reader->getRecognitionQuality();

print "Barcode Quality Percentage: ".(string)$percent.PHP_EOL;

}

\# Close reader

$reader->close();

{{< /highlight >}}
## **Download Running Code**
Download **Get BarCode Recognition Quality in Percent (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_PHP/src/aspose/barcode/WorkingWithBarcodeRecognition/AdvancedBarcodeRecognitionFeatures/GetBarcodeRecognitionQuality.php)
- [CodePlex](https://asposebarcodejavaphp.codeplex.com/SourceControl/latest#src/aspose/barcode/WorkingWithBarcodeRecognition/AdvancedBarcodeRecognitionFeatures/GetBarcodeRecognitionQuality.php)
