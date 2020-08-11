---
title: 1.2.1.2. Generating QR Code for Some Text
type: docs
weight: 30
url: /java/1-2-1-2-generating-qr-code-for-some-text/
---

## **How to generate QR Code for Some Text?**
To generate a QR Code for a URL, please refer to the following steps:

1. Select the Type: **Text**
1. Input the Content Text
1. Customize QR Code Settings (Optional)
1. Click **Generate Preview** button

That's it. You will see the generated QR Code under the preview section.
## **How it works?**
#### **API**
{{< highlight java >}}

 GET /qrcodegen/api/qrcode/generate?data=<Some Text>&foreColor=&bgColor=&ecc=&size=&format=

Example:

http://localhost:8080/qrcodegen/api/qrcode/generate?data=AsposeBarCode&foreColor=&bgColor=&ecc=&size=&format=


{{< /highlight >}}
#### **Java Script**
JS code spinet to generate Text type QR Code from content input field. 

{{< highlight java >}}

  if(page == 'text'){



     var requestString= this.$('.input-textarea').val();

     requestString = this.addRequestSettings(requestString)



     var that = this;



     this.$('.qrcode-preview-image').load(function(){

     that.$('.qrcode-preview-processing').addClass('hide');

     that.$('.qrcode-preview-image').removeClass('hide');

     })

     .error(function(){

     that.$('.qrcode-preview-processing').attr('src', '/qrcodegen/resources/imgs/error.png');

     })

     .attr('src', '/qrcodegen/api/qrcode/generate?data='+ requestString);



     this.updateDownloadTag(requestString);



     } 



{{< /highlight >}}
#### **Java**
**Core API Method -** **QRCodeManagementController.generateQRCode** 

{{< highlight java >}}

 @RequestMapping(value = "generate", method = RequestMethod.GET,

    		produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_GIF_VALUE, MediaType_IMAGE_TIFF_VALUE, MediaType_IMAGE_BMP_VALUE})

    @ApiOperation(value = "Generate QR Code.")

    public ResponseEntity<byte[]>  generateQRCode(

    		@ApiParam( value = "data", name="data" , required = true)

    		@RequestParam("data") String data,

        HttpServletRequest request,HttpServletResponse response,

        @ApiParam( value = "A user-chosen password that can be used with password-based encryption (PBE) Algo PBEWITHMD5AND128BITAES-CBC-OPENSSL)", name="passKey", required=false) @RequestParam(required=false, value="passKey") String passKey,

        @ApiParam( value = "ForeColor e.g #000000 (Black - RGB(hex))", name="foreColor", required=false) @RequestParam(required=false, value="foreColor") String foreColor,

        @ApiParam( value = "BackgroundColor e.g #FFFFFF (White - RGB(hex))", name="bgColor", required=false) @RequestParam(required=false, value="bgColor") String bgColor,

        @ApiParam( value = "L|M|Q|H - Reed-Solomon error correctionCode Level(from low to high) default=Low", name="ecc", required=false) @RequestParam(required=false, value="ecc") String ecc,

        @ApiParam( value = "Image Size e.g #150x150", name="size", required=false) @RequestParam(required=false, value="size") String size,

        @ApiParam( value = "jpeg|tiff|gif|png|bmp - default=png", name="format", required=false) @RequestParam(required=false, value="format") String format,

        @ApiParam( value = "true|false default=false", name="download", required=false) @RequestParam(required=false, value="download") boolean download,

        @ApiIgnore @Value("#{request.getHeader('" +  ACCEPT_HEADER + "')}") String acceptHeaderValue) throws Exception {


{{< /highlight >}}
