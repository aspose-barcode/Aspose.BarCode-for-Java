---
title: Getting Barcode Region Information from the Image in PHP
type: docs
weight: 30
url: /java/getting-barcode-region-information-from-the-image-in-php/
---

## **Aspose.BarCode - Getting Barcode Region Information from the Image**
To Get Barcode Region Information from the Image using **Aspose.Barcode Java for PHP**, simply invoke **GetBarcodeRegionInfo** module. Here you can see example code.

**PHPCode**

{{< highlight php >}}

 # initialize barcode reader

$img = $dataDir . "barcode.jpg";

$barcode_reader_type = new BarCodeReadType();

$reader = new BarCodeReader($img, $barcode_reader_type->Code39Standard);

\# Try to recognize all possible barcodes in the image

while (java_values($reader -> read())) {

\# Get the region information

$region = $reader->getRegion();

if ($region != null){

\# Display x and y coordinates of barcode detected

$point = $region->getPoints();

print "Top left coordinates: X = " . (string)$point[0] -> x . ", Y = " . (string)$point[0] -> y.PHP_EOL;

print "Bottom left coordinates: X = " . (string)$point[1] -> x . ", Y = " . (string)$point[1]->y.PHP_EOL;

print "Bottom right coordinates: X = " . (string)$point[2] -> x . ", Y = " . (string)$point[2]->y.PHP_EOL;

print "Top right coordinates: X = " . (string)$point[3] -> x . ", Y = " . (string)$point[3]->y.PHP_EOL;

}

print "Codetext: " . (string)$reader->getCodeText().PHP_EOL;

}

\# Close reader

$reader->close();

{{< /highlight >}}
## **Download Running Code**
Download **Getting Barcode Region Information from the Image (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_PHP/src/aspose/barcode/WorkingWithBarcodeRecognition/AdvancedBarcodeRecognitionFeatures/GetBarcodeRegionInfo.php)
- [CodePlex](https://asposebarcodejavaphp.codeplex.com/SourceControl/latest#src/aspose/barcode/WorkingWithBarcodeRecognition/AdvancedBarcodeRecognitionFeatures/GetBarcodeRegionInfo.php)
