package ktb.docugenration.service;

import java.io.File;
import java.io.OutputStream;
import java.util.Map;

public interface PdfGenerateService {


    byte[]  generatePdfFiles(String templateName, Map<String, Object> data, String pdfFileName) throws Exception;

}