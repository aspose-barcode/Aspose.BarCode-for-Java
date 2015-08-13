module Asposebarcodejava
  module SpecifySymbologies
    def initialize()
        data_dir = File.dirname(File.dirname(File.dirname(File.dirname(__FILE__)))) + '/data/'
                
        # Instantiate barcode object
        builder = Rjb::import('com.aspose.barcode.BarCodeBuilder').new

        # ============ Code39Standard ===================== 
        # set symbology type
        builder.setSymbologyType(Rjb::import('com.aspose.barcode.Symbology').Code39Standard)
        
        # Save image to disk
        builder.save(data_dir + "code39Standard.out.png")

        # ================== QR =========================== 
        # set symbology type
        builder.setSymbologyType(Rjb::import('com.aspose.barcode.Symbology').QR)
        
        # Save image to disk
        builder.save(data_dir + "QR.out.png")
        
        # =============== Code128 ========================= 
        # set symbology type
        builder.setSymbologyType(Rjb::import('com.aspose.barcode.Symbology').Code128)
        
        # Save image to disk
        builder.save(data_dir + "code128.out.png")
        
        # Print message
        puts "Barcode image(s) generated successfully."
    end
  end
end
