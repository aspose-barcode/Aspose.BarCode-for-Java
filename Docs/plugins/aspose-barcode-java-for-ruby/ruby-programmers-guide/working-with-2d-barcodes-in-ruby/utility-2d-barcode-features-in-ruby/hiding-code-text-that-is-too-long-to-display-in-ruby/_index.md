---
title: Hiding Code Text that is too Long to Display in Ruby
type: docs
weight: 20
url: /java/hiding-code-text-that-is-too-long-to-display-in-ruby/
---

## **Aspose.BarCode - Hiding Code Text that is too Long to Display**
To Hide Code Text that is too Long to Display using **Aspose.Barcode Java for Ruby**, simply invoke **HideCodeText** module. Here you can see example code.

**Ruby Code**

{{< highlight ruby >}}

 data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'



\# Instantiate barcode object

builder = Rjb::import('com.aspose.barcode.BarCodeBuilder').new

builder.setSymbologyType(Rjb::import('com.aspose.barcode.Symbology').DataMatrix)

builder.setCodeText("The quick brown fox jumps over the lazy dog\n The quick brown fox jumps over the lazy dog\n");

builder.setCodeLocation(Rjb::import('com.aspose.barcode.CodeLocation').None)

font = Rjb::import('java.awt.Font')

builder.setCodeTextFont(font.new("Serif", font.BOLD + font.ITALIC, 20))

\# Save the image

builder.save(data_dir + "HideCodeText.jpg")

\# Display Status

puts "Hide Code Text Successfully."

{{< /highlight >}}
## **Download Running Code**
Download **Hiding Code Text that is too Long to Display (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_Ruby/lib/asposebarcodejava/2DBarcode/hidecodetext.rb)
