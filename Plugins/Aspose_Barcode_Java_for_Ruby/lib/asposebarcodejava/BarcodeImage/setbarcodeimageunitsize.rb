module Asposebarcodejava
  module SetBarcodeImageUnitSize
    def initialize()
        data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'
                
        # Instantiate barcode object
        symbology = Rjb::import('com.aspose.barcode.Symbology')
        bb = Rjb::import('com.aspose.barcode.BarCodeBuilder').new("12345678", symbology.Code128)

        # Measurement is Milimeter
        bb.setGraphicsUnit(Rjb::import('com.aspose.barcode.GraphicsUnit').Point)

        # Set the bar height to 3 points
        bb.setBarHeight(3.0)

        bb.save(data_dir + "SetBarcodeImageUnitSize.jpg")

        # Display Status.
        puts "Set Barcode Image Unit Size, please check the output file."
    end
  end
end
