---
title: 1.2.1.4. Generating QR Code for an SMS
type: docs
weight: 40
url: /java/1-2-1-4-generating-qr-code-for-an-sms/
---

## **How to generate QR Code for an SMS?**
To generate a QR Code for a URL, please refer to the following steps:

1. Select the Type: **SMS**
1. Fill out the content section: SMS
1. Customize QR Code Settings (Optional)
1. Click **Generate Preview** button

That's It. You will see the generated QR Code under the preview section.
## **How it works?**
**SMS Format**

SMSTO:<Phone Number>:<MESSAGE>
#### **API**
{{< highlight java >}}

 GET /qrcodegen/api/qrcode/generate?data=<SMSTO:Phone Number:MESSAGE>&foreColor=&bgColor=&ecc=&size=&format=

Sample SMS Message

\------------------

SMSTO:+61280035926:This is an SMS from Aspose.BarCode for Java library.

API Example:

\------------

http://localhost:8080/qrcodegen/api/qrcode/generate?data=SMSTO%3A%2B61280035926%3AThis is an SMS from Aspose.BarCode for Java library.&foreColor=&bgColor=&ecc=&size=&format=


{{< /highlight >}}

**Generated SMS QR Code**

|<p>![todo:image_alt_text](1-2-1-4-generating-qr-code-for-an-sms_1.png)</p><p></p>|
| :- |
#### **Java Script**
JS code spinet to generate SMS format from content input fields.

{{< highlight java >}}

 if(page == 'sms'){



     var requestString= "SMSTO:";

     requestString = requestString + this.$('.input-sms-countrycode').val() + this.$('.input-sms-areacode').val() +  this.$('.input-sms-phonenumber').val();

     requestString = requestString + ":" + this.$('.input-sms-content').val();

     requestString = encodeURIComponent(requestString);

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
**Core API Method - QRCodeManagementController.generateQRCode** 

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
