---
title: Control BarCode Image Quality in Ruby
type: docs
weight: 20
url: /java/control-barcode-image-quality-in-ruby/
---

## **Aspose.BarCode - Control Barcode Image Quality**
To Control Barcode Image Quality using **Aspose.Barcode Java for Ruby**, simply invoke **SetBarcodeImageQuality** module. Here you can see example code.

**Ruby Code**

{{< highlight ruby >}}

 data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'



\# Instantiate barcode object

bb = Rjb::import('com.aspose.barcode.BarCodeBuilder').new

\# Set the code text of the barcode

bb.setCodeText("12345678")

\# Set the graphics drawing hint to be Anti Alias

bb.setImageQuality(Rjb::import('com.aspose.barcode.ImageQualityMode').AntiAlias)

\# Set the symbology type to code39

bb.setSymbologyType(Rjb::import('com.aspose.barcode.Symbology').Code39Extended)

bb.save(data_dir + "SetBarcodeImageQuality.jpg")

\# Display Status.

puts "Set Barcode Image Quality, please check the output file."

{{< /highlight >}}
## **Download Running Code**
Download **Control Barcode Image Quality (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_Ruby/lib/asposebarcodejava/BarcodeImage/setbarcodeimagequality.rb)
