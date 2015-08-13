module Asposebarcodejava
  module PrintingBarcodeImage
    def initialize()
        data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'
                
        # Instantiate barcode object
        symbology = Rjb::import('com.aspose.barcode.Symbology')
        builder = Rjb::import('com.aspose.barcode.BarCodeBuilder').new("12345678", symbology.Code128)

        # Set printer name
        builder.setPrinterName("ML-1640 Series")
        
        # Start a print job
        builder.print()
    end
  end
end
