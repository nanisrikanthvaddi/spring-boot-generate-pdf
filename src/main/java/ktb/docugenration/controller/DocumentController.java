package ktb.docugenration.controller;


import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import ktb.docugenration.entity.Reconcile;
import ktb.docugenration.service.PdfGenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController

public class DocumentController {

    @Autowired
    private PdfGenerateService pdfGenerateService;


    @GetMapping(value = "/getPdf")

    public ResponseEntity<?> getTermsConditions() {

        // File file = storageService.load(filename).getFile();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("content-disposition", "inline;filename=" + "Reconcole.pdf");




            /*Setup converter properties. */


        /*   // File f = getPdf();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            baos.writeTo(getPdf());
            ByteArrayInputStream bs= new ByteArrayInputStream(baos.toByteArray());*/

            /*InputStream is = (ByteArrayInputStream)baos;

                    File f= new File();*/



          /*  InputStreamResource resource = new InputStreamResource(bs);*/


            return ResponseEntity.ok()
                    .headers(headers)
                   //.contentLength(f.length())
                    .contentType(MediaType.parseMediaType("application/pdf"))
                    .body(getPdf());

        } catch (Exception exception) {
            exception.printStackTrace();

        }
        return null;
    }

        public    byte[]   getPdf(){

            Map<String, Object> data = new HashMap<>();

            List<Reconcile> reconcile = new ArrayList<>();
            Reconcile rec1= new Reconcile();
            rec1.setCustomerId("12");
            rec1.setTxnId("20220121312");

            rec1.setMerchantId("merch1");
            rec1.setMerchantName("BKT gold shop");
            rec1.setTxnAmount("100BHT");
            rec1.setTxnSource("Tungern");
            rec1.setCustomerName("ABCD");

            Reconcile rec2= new Reconcile();
            rec2.setCustomerId("45");
            rec2.setTxnId("202201988");
            rec2.setMerchantId("merch2");
            rec2.setMerchantName("Avani hotel");
            rec2.setTxnAmount("4000BHT");
            rec2.setTxnSource("Poatang");
            rec2.setCustomerName("XYZ");
            reconcile.add(rec1);
            reconcile.add(rec2);
            data.put("reconcile", reconcile);

            try {
                return pdfGenerateService.generatePdfFiles("Reconcile", data, "Reconcile.pdf");
            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }
}
