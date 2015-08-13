module Asposebarcodejava
  module SetBarcodeImageMargins
    def initialize()
        data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'
                
        # Instantiate barcode object
        symbology = Rjb::import('com.aspose.barcode.Symbology')
        bb = Rjb::import('com.aspose.barcode.BarCodeBuilder').new("12345678", symbology.Code128)

        # sets the top margin
        bb.getMargins().setTop(4)

        # sets the bottom margin
        bb.getMargins().setBottom(5)

        # sets the left margin
        bb.getMargins().setLeft(2)

        # sets the right margin
        bb.getMargins().setRight(3)

        bb.save(data_dir + "SetBarcodeImageMargins.jpg")

        # Display Status.
        puts "Set Barcode Image Margins, please check the output file."
    end
  end
end
