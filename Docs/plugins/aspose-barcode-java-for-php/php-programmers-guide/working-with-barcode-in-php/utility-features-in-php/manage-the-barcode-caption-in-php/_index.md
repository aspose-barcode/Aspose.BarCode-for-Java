---
title: Manage the Barcode Caption in PHP
type: docs
weight: 20
url: /java/manage-the-barcode-caption-in-php/
---

## **Aspose.BarCode - Manage the Barcode Caption**
To Manage the Barcode Caption using **Aspose.Barcode Java for PHP**, simply invoke **ManageCaption** module. Here you can see example code.

**PHPCode**

{{< highlight php >}}

 # Instantiate barcode object

$bb = new BarCodeBuilder();

\# Set up code text (data to be encoded)

$bb->setCodeText("1234567");

\# Set the symbology type to Code128

$symbology=new Symbology();

$bb->setSymbologyType($symbology->Code128);

$caption = new Caption();

$alignment=new Alignment();

$caption->setText("Captions");

$caption->setTextAlign($alignment->Middle);

$bb->setCaptionAbove(caption);

$bb->setCaptionBelow(caption);

$bb->getCaptionAbove()->setTextAlign($alignment->Left);

$bb->getCaptionAbove()->setText("Aspose");

$bb->getCaptionAbove()->setVisible(true);

#bb.getCaptionAbove().setFont(new java.awt.Font("Pristina", java.awt.Font.PLAIN, 14));

#bb.getCaptionAbove().setForeColor(java.awt.Color.RED);

$bb->getCaptionBelow()->setTextAlign($alignment->Right);

$bb->getCaptionBelow()->setText("Aspose.BarCode Caption Below");

$bb->getCaptionBelow()->setVisible(true);

#bb.getCaptionBelow().setFont(new java.awt.Font("Pristina", Font.PLAIN, 14));

#bb.getCaptionBelow().setForeColor(java.awt.Color.RED);

\# Save the image to your system and set its image format to Jpeg

$barCodeImageFormat=new BarCodeImageFormat();

$bb->save($dataDir . "barcode.jpg", $barCodeImageFormat->Jpeg);

\# Display Status

print "Barcode with Captions saved successfully.".PHP_EOL;

{{< /highlight >}}
## **Download Running Code**
Download **Manage the Barcode Caption (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_PHP/src/aspose/barcode/WorkingWithBarcode/UtilityFeatures/ManageCaption.php)
- [CodePlex](https://asposebarcodejavaphp.codeplex.com/SourceControl/latest#src/aspose/barcode/WorkingWithBarcode/UtilityFeatures/ManageCaption.php)
