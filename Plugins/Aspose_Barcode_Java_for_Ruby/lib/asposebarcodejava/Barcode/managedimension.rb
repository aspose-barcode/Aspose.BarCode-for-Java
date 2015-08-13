module Asposebarcodejava
  module ManageDimension
    def initialize()
        data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'
                
        # Instantiate barcode object
        bb = Rjb::import('com.aspose.barcode.BarCodeBuilder').new

        # Set up code text (data to be encoded)
        bb.setCodeText("1234567")

        # Set the symbology type to Code128
        bb.setSymbologyType(Rjb::import('com.aspose.barcode.Symbology').Code128)

        # Save the image to file
        bb.save(data_dir + "barcode.jpg")

        # Set the x-dimension for the bars of the barcode
        bb.setxDimension(0.5)
        # Save the image to file
        bb.save(data_dir + "barcodeXDimensionChanged.jpg")


        # Instantiate barcode object
        bb1 = Rjb::import('com.aspose.barcode.BarCodeBuilder').new

        # Set the code text of the barcode
        bb1.setCodeText("12345678")

        # Set the symbology type to code128
        bb1.setSymbologyType(Rjb::import('com.aspose.barcode.Symbology').Pdf417)

        # Set the x-dimension for the bars of the barcode
        bb1.setxDimension(0.5)

        # Save the image to file
        bb1.save(data_dir + "barcodeYDimensionChanged.jpg")

        # Display Status.
        puts "BarCodes with different dimensions have been created successfully."
    end
  end
end
