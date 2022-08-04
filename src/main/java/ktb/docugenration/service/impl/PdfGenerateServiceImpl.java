package ktb.docugenration.service.impl;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.lowagie.text.DocumentException;
import ktb.docugenration.service.PdfGenerateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.util.Map;

@Service
public class PdfGenerateServiceImpl implements PdfGenerateService {
    private Logger logger = LoggerFactory.getLogger(PdfGenerateServiceImpl.class);

    @Autowired
    private TemplateEngine templateEngine;




    @Override
     public byte[] generatePdfFiles(String templateName, Map<String, Object> data, String pdfFileName)throws Exception {
        Context context = new Context();
        context.setVariables(data);
        byte[] bytes=null;

        String htmlContent = templateEngine.process(templateName, context);
        String out =null;
        try  {

            ByteArrayOutputStream target = new ByteArrayOutputStream();

            ConverterProperties converterProperties = new ConverterProperties();
            converterProperties.setBaseUri("http://localhost:8080");

            HtmlConverter.convertToPdf(htmlContent, target, converterProperties);
         bytes = target.toByteArray();



        } catch (java.lang.Exception  e) {
            logger.error("Exception while generating pdf : {}", e);
        }
        return bytes;


    }
}