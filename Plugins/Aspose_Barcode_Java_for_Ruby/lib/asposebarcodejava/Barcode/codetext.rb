module Asposebarcodejava
  module CodeText
    def initialize()
        # Set appearance of the code text
        set_appearance()

        # Set code text for Barcode
        set_codetext()
    end

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
  end
end
