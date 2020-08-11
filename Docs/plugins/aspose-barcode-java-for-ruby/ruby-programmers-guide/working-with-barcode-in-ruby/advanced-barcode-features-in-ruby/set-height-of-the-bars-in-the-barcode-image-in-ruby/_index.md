---
title: Set Height of the Bars in the BarCode Image in Ruby
type: docs
weight: 40
url: /java/set-height-of-the-bars-in-the-barcode-image-in-ruby/
---

## **Aspose.BarCode - Set Height of the Bars**
To Set Height of the Bars using **Aspose.Barcode Java for Ruby**, simply invoke **SetBarsHeight** module. Here you can see example code.

**Ruby Code**

{{< highlight ruby >}}

 data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'



\# Instantiate barcode object

bb = Rjb::import('com.aspose.barcode.BarCodeBuilder').new

\# Set up code text (data to be encoded)

bb.setCodeText("1234567")

\# Set the symbology type to Code128

bb.setSymbologyType(Rjb::import('com.aspose.barcode.Symbology').Code128)

\# Set the bar height to be 3 milimeter

bb.setBarHeight(3.0)

bb.save(data_dir + "barcode3.jpg")

\# Set the bar height to be 7 milimeter

bb.setBarHeight(7.0)

bb.save(data_dir + "barcode7.jpg")

\# Set the bar height to be 11 milimeter

bb.setBarHeight(11.0)

bb.save(data_dir + "barcode11.jpg")

\# Display Status.

puts "Barcode with different heights has been created successfully."

{{< /highlight >}}
## **Download Running Code**
Download **Set Height of the Bars (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_Ruby/lib/asposebarcodejava/Barcode/setbarsheight.rb)
