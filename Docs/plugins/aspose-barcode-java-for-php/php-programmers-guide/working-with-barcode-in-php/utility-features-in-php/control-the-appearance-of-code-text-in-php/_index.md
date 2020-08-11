---
title: Control the Appearance of Code Text in PHP
type: docs
weight: 10
url: /java/control-the-appearance-of-code-text-in-php/
---

## **Aspose.BarCode - Control the Appearance of Code Text**
To Control the Appearance of Code Text using **Aspose.Barcode Java for PHP**, call **set_appearance** method of **CodeText** module. Here you can see example code.

**PHP Code**

{{< highlight php >}}

 public static function set_appearance($dataDir=null){

$bb = new BarCodeBuilder();

\# Set up code text (data to be encoded)

$bb->setCodeText("1234567");

\# Set up code text color

$color=new Color();

$bb->setCodeTextColor($color->RED);

\# Set the location of the code text to above the barcode

$codeLocation=new CodeLocation();

$bb->setCodeLocation($codeLocation->Above);

#Increase the space between code text and barcode to 1 point

$bb->setCodeTextSpace(1.0);

\# Set the symbology type to Code128

$symbology=new Symbology();

$bb->setSymbologyType($symbology->Code128);

\# Save the image to your system and set its image format to Jpeg

$barCodeImageFormat=new BarCodeImageFormat();

$bb->save($dataDir . "barcode.jpg", $barCodeImageFormat->Jpeg);

\# Display Status

print "Barcode with custom appearance saved as JPEG image successfully.".PHP_EOL;

}

{{< /highlight >}}
## **Download Running Code**
Download **Control the Appearance of Code Text (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_PHP/src/aspose/barcode/WorkingWithBarcode/UtilityFeatures/CodeText.php)
- [CodePlex](https://asposebarcodejavaphp.codeplex.com/SourceControl/latest#src/aspose/barcode/WorkingWithBarcode/UtilityFeatures/CodeText.php)
