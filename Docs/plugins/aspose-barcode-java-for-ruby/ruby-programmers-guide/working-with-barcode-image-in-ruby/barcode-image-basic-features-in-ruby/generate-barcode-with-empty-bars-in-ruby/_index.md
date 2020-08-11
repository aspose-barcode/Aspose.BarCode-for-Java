---
title: Generate BarCode with Empty Bars in Ruby
type: docs
weight: 30
url: /java/generate-barcode-with-empty-bars-in-ruby/
---

## **Aspose.BarCode - Generate Barcode with Empty Bars**
To Generate Barcode with Empty Bars using **Aspose.Barcode Java for Ruby**, simply invoke **GenerateBarcodeWithEmptyBars** module. Here you can see example code.

**Ruby Code**

{{< highlight ruby >}}

 data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'



\# Instantiate barcode object

symbology = Rjb::import('com.aspose.barcode.Symbology')

bb = Rjb::import('com.aspose.barcode.BarCodeBuilder').new("TEXT", symbology.Code128)

\# Set the FilledBars property to false

bb.setFilledBars(false)

bb.save(data_dir + "GenerateBarcodeWithEmptyBars.jpg")

\# Display Status.

puts "Generate Barcode With Empty Bars, please check the output file."

{{< /highlight >}}
## **Download Running Code**
Download **Generate Barcode with Empty Bars (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_Ruby/lib/asposebarcodejava/BarcodeImage/generatebarcodewithemptybars.rb)
