---
title: Colorize any Part of the BarCode Image in Ruby
type: docs
weight: 10
url: /java/colorize-any-part-of-the-barcode-image-in-ruby/
---

## **Aspose.BarCode - Colorize any Part of the Barcode Image**
To Colorize any Part of the Barcode Image using **Aspose.Barcode Java for Ruby**, simply invoke **SetBarcodeImageColor** module. Here you can see example code.

**Ruby Code**

{{< highlight ruby >}}

 data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'



\# Instantiate barcode object

bb = Rjb::import('com.aspose.barcode.BarCodeBuilder').new

color = Rjb::import('java.awt.Color')

\# Set the background color of the barcode

bb.setBackColor(color.YELLOW)

\# Set the foreground color of the barcode

bb.setForeColor(color.BLUE)

\# Set border's color

bb.setBorderColor(color.RED)

\# Set the code text's color

bb.setCodeTextColor(color.RED)

\# Caption's color

bb.getCaptionAbove().setForeColor(color.darkGray)

bb.getCaptionBelow().setForeColor(color.CYAN)

bb.save(data_dir + "color.jpg")

\# Display Status.

puts "Applied color to barcode image, please check the output file."

{{< /highlight >}}
## **Download Running Code**
Download **Colorize any Part of the Barcode Image (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_Ruby/lib/asposebarcodejava/BarcodeImage/setbarcodeimagecolor.rb)
