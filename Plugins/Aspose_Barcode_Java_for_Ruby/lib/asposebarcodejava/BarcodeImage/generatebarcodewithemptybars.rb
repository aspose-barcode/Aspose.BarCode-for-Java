module Asposebarcodejava
  module GenerateBarcodeWithEmptyBars
    def initialize()
        data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'
                
        # Instantiate barcode object
        symbology = Rjb::import('com.aspose.barcode.Symbology')
        bb = Rjb::import('com.aspose.barcode.BarCodeBuilder').new("TEXT", symbology.Code128)

        # Set the FilledBars property to false
        bb.setFilledBars(false)

        bb.save(data_dir + "GenerateBarcodeWithEmptyBars.jpg")

        # Display Status.
        puts "Generate Barcode With Empty Bars, please check the output file."
    end
  end
end
