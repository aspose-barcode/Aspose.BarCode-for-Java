---
title: Set Code Text for BarCode in Ruby
type: docs
weight: 30
url: /java/set-code-text-for-barcode-in-ruby/
---

## **Aspose.BarCode - Set Code Text for Barcode**
To Set Code Text for Barcode using **Aspose.Barcode Java for Ruby**, call **set_codetext** method of **CodeText** module. Here you can see example code.

**Ruby Code**

{{< highlight ruby >}}

 def set_codetext()

    data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'



    # Instantiate barcode object

    bb = Rjb::import('com.aspose.barcode.BarCodeBuilder').new

    # Set the code text for the barcode

    bb.setCodeText("Aspose-123")

    # Set the symbology type to Code128

    bb.setSymbologyType(Rjb::import('com.aspose.barcode.Symbology').Code128)

    # Set the width of the bars to 0.5 millimeter

    bb.setxDimension(0.5)



    # save the barcode image to file

    bb.save(data_dir + "codetext.out.jpg")



    # Print message

    puts "Barcode image generated successfully."

end

{{< /highlight >}}
## **Download Running Code**
Download **Set Code Text for Barcode (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_Ruby/lib/asposebarcodejava/Barcode/codetext.rb)
