---
title: Rotate BarCode Image in Ruby
type: docs
weight: 40
url: /java/rotate-barcode-image-in-ruby/
---

## **Aspose.BarCode - Rotate Barcode Image**
To Rotate Barcode Image using **Aspose.Barcode Java for Ruby**, simply invoke **RotateImage** module. Here you can see example code.

**Ruby Code**

{{< highlight ruby >}}

 data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'



\# Instantiate barcode object

bb = Rjb::import('com.aspose.barcode.BarCodeBuilder').new

\# Set the code text of the barcode

bb.setCodeText("12345678")

\# Roate clockwise for 180 degree (upside down)

bb.setRotationAngleF(180)

\# Set the symbology type to code39

bb.setSymbologyType(Rjb::import('com.aspose.barcode.Symbology').Code39Extended)

bb.save(data_dir + "Rotate.jpg")

\# Display Status.

puts "Done with image rotation, please check the output file."

{{< /highlight >}}
## **Download Running Code**
Download **Rotate Barcode Image (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_Ruby/lib/asposebarcodejava/BarcodeImage/rotateimage.rb)
