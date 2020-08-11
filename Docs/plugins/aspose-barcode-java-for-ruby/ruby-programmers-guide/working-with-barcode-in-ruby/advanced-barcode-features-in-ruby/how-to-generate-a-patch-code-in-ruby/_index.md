---
title: How to Generate a Patch Code in Ruby
type: docs
weight: 20
url: /java/how-to-generate-a-patch-code-in-ruby/
---

## **Aspose.BarCode - How to Generate a Patch Code**
To Generate a Patch Code using **Aspose.Barcode Java for Ruby**, simply invoke **PatchCode** module. Here you can see example code.

**Ruby Code**

{{< highlight ruby >}}

 data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'



\# Instantiate barcode object

builder = Rjb::import('com.aspose.barcode.BarCodeBuilder').new

\# Set Symbology type

builder.setSymbologyType(Rjb::import('com.aspose.barcode.Symbology').PatchCode)

\# Set code text

builder.setCodeText("Patch T")

\# Save the image to your system and set its image format to Jpeg

builder.save(data_dir + "PatchCode.jpg")

\# Display Status

puts "Generated PatchCode Successfully."

{{< /highlight >}}
## **Download Running Code**
Download **How to Generate a Patch Code (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_Ruby/lib/asposebarcodejava/Barcode/patchcode.rb)
