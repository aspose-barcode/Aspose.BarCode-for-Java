module Asposebarcodejava
  module Creating2DBarcode
    def initialize()
        data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'
                
        # Instantiate barcode object
        builder = Rjb::import('com.aspose.barcode.BarCodeBuilder').new

        builder.setSymbologyType(Rjb::import('com.aspose.barcode.Symbology').Pdf417)

        # Width of each module
        builder.setxDimension(0.6)

        # Height of each module
        builder.setyDimension(1.2)
        builder.setCodeText("this is some test code text. \n Second line \n third line.")

        # Save the image to your system and set its image format to Jpeg
        builder.save(data_dir + "Creating2DBarcode.jpg")

        # Display Status
        puts "Created 2D Barcode Successfully."
    end
  end
end
