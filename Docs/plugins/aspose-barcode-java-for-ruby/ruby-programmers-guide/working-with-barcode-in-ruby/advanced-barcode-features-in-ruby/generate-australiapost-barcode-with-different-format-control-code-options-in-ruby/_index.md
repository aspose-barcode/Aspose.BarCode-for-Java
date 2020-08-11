---
title: Generate AustraliaPost BarCode with Different Format Control Code Options in Ruby
type: docs
weight: 10
url: /java/generate-australiapost-barcode-with-different-format-control-code-options-in-ruby/
---

## **Aspose.BarCode - Generate AustraliaPost Barcode**
To Generate AustraliaPost Barcode using **Aspose.Barcode Java for Ruby**, simply invoke **AustraliaPostBarcode** module. Here you can see example code.

**Ruby Code**

{{< highlight ruby >}}

 data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'



\# Instantiate barcode object

symbology = Rjb::import('com.aspose.barcode.Symbology')

builder = Rjb::import('com.aspose.barcode.BarCodeBuilder').new("1234567890", symbology.AustraliaPost)

\# Set format control code to standard

builder.setAustraliaPostFormatControlCode(Rjb::import('com.aspose.barcode.AustraliaPostFormatControlCode').Standard)

\# Save the image to your system and set its image format to Jpeg

builder.save(data_dir + "AusraliaPost-Barcode.jpg")

\# Display Status

puts "Generated Austrailia Post Barcode Successfully."

{{< /highlight >}}
## **Download Running Code**
Download **Generate AustraliaPost Barcode (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_Ruby/lib/asposebarcodejava/Barcode/australiapostbarcode.rb)
