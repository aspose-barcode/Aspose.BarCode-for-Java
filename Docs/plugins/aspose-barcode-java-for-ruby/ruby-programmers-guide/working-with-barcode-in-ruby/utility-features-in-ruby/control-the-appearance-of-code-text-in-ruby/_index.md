---
title: Control the Appearance of Code Text in Ruby
type: docs
weight: 10
url: /java/control-the-appearance-of-code-text-in-ruby/
---

## **Aspose.BarCode - Control the Appearance of Code Text**
To Control the Appearance of Code Text using **Aspose.Barcode Java for Ruby**, call **set_appearance** method of **CodeText** module. Here you can see example code.

**Ruby Code**

{{< highlight ruby >}}

 def set_appearance()

    data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'



    # Instantiate barcode object

    bb = Rjb::import('com.aspose.barcode.BarCodeBuilder').new

    # Set up code text (data to be encoded)

    bb.setCodeText("1234567")

    # Set up code text color

    bb.setCodeTextColor(Rjb::import('java.awt.Color').RED)

    # Set the location of the code text to above the barcode

    bb.setCodeLocation(Rjb::import('com.aspose.barcode.CodeLocation').Above)

    #Increase the space between code text and barcode to 1 point

    bb.setCodeTextSpace(1.0)

    # Set the symbology type to Code128

    bb.setSymbologyType(Rjb::import('com.aspose.barcode.Symbology').Code128)

    # Save the image to your system and set its image format to Jpeg

    bb.save(data_dir + "barcode.jpg", Rjb::import('com.aspose.barcode.BarCodeImageFormat').Jpeg)

    # Display Status

    puts "Barcode with custom appearance saved as JPEG image successfully."

end

{{< /highlight >}}
## **Download Running Code**
Download **Control the Appearance of Code Text (Aspose.BarCode)** from any of the below mentioned social coding sites:

- [GitHub](https://github.com/aspose-barcode/Aspose.BarCode-for-Java/blob/master/Plugins/Aspose_Barcode_Java_for_Ruby/lib/asposebarcodejava/Barcode/codetext.rb)
