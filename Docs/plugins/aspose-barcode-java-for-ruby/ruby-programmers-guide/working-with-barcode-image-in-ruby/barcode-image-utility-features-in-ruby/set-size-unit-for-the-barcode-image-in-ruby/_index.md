---
title: Set Size Unit for the BarCode Image in Ruby
type: docs
weight: 50
url: /java/set-size-unit-for-the-barcode-image-in-ruby/
---

## **Aspose.BarCode - Set Size Unit for the Barcode Image**
To Set Size Unit for the Barcode Image using **Aspose.Barcode Java for Ruby**, simply invoke **SetBarcodeImageUnitSize** module. Here you can see example code.

**Ruby Code**

{{< highlight ruby >}}

 data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'



\# Instantiate barcode object

symbology = Rjb::import('com.aspose.barcode.Symbology')

bb = Rjb::import('com.aspose.barcode.BarCodeBuilder').new("12345678", symbology.Code128)

\# Measurement is Milimeter

bb.setGraphicsUnit(Rjb::import('com.aspose.barcode.GraphicsUnit').Point)

\# Set the bar height to 3 points

bb.setBarHeight(3.0)

bb.save(data_dir + "SetBarcodeImageUnitSize.jpg")

\# Display Status.

puts "Set Barcode Image Unit Size, please check the output file."

{{< /highlight >}}
## **Download Running Code**
Download **Set Size Unit for the Barcode Image (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_Ruby/lib/asposebarcodejava/BarcodeImage/setbarcodeimageunitsize.rb)
