---
title: Set Start and Stop Symbols of Codabar BarCode in Ruby
type: docs
weight: 50
url: /java/set-start-and-stop-symbols-of-codabar-barcode-in-ruby/
---

## **Aspose.BarCode - Set Start and Stop Symbols of Codabar Barcode**
To Set Start and Stop Symbols of Codabar Barcode using **Aspose.Barcode Java for Ruby**, simply invoke **StartStopSymbol** module. Here you can see example code.

**Ruby Code**

{{< highlight ruby >}}

 data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'

\# Create instance of BarCodeBuilder, specify codetext and symbology in the constructor

builder = Rjb::import('com.aspose.barcode.BarCodeBuilder').new("$ 123:50", Rjb::import('com.aspose.barcode.Symbology').Codabar)

\# Set the codabar start symbol to A

builder.setCodabarStartSymbol(Rjb::import('com.aspose.barcode.CodabarSymbol').A)

\# Set the codabar stop symbol to D

builder.setCodabarStopSymbol(Rjb::import('com.aspose.barcode.CodabarSymbol').D)

\# Save the image to disk in PNG format

builder.save(data_dir + "barcode.out.png")

\# Print message

puts "Barcode image generated successfully."

{{< /highlight >}}
## **Download Running Code**
Download **Set Start and Stop Symbols of Codabar Barcode (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_Ruby/lib/asposebarcodejava/Barcode/startstopsymbol.rb)
