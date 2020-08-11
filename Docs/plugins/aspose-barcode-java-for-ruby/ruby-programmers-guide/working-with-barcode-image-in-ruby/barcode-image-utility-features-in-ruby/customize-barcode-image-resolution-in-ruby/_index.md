---
title: Customize BarCode Image Resolution in Ruby
type: docs
weight: 10
url: /java/customize-barcode-image-resolution-in-ruby/
---

## **Aspose.BarCode - Customize Barcode Image Resolution**
To Customize Barcode Image Resolution using **Aspose.Barcode Java for Ruby**, simply invoke **CustomizeBarcodeImageResolution** module. Here you can see example code.

**Ruby Code**

{{< highlight ruby >}}

 data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'



\# Instantiate barcode object

bb = Rjb::import('com.aspose.barcode.BarCodeBuilder').new

\# Set the Code text for the barcode

bb.setCodeText("1234567")

\# Set the symbology type to Code128

bb.setSymbologyType(Rjb::import('com.aspose.barcode.Symbology').Code128)

\# Create an instance of resolution

bb.setResolution(Rjb::import('com.aspose.barcode.Resolution').new(200,400,Rjb::import('com.aspose.barcode.ResolutionMode').Graphics))

bb.save(data_dir + "CustomizeBarcodeImageResolution.jpg")

\# Display Status.

puts "Customized Barcode Image Resolution, please check the output file."

{{< /highlight >}}
## **Download Running Code**
Download **Customize Barcode Image Resolution (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_Ruby/lib/asposebarcodejava/BarcodeImage/customizebarcodeimageresolution.rb)
