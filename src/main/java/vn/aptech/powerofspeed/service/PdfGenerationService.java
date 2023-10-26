package vn.aptech.powerofspeed.service;

import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;


@Service
public class PdfGenerationService {

    public byte[] generatePdfFromHtml(String htmlContent) {
        try {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            renderer.createPDF(outputStream);
            renderer.finishPDF();
            return outputStream.toByteArray();
        } catch (Exception e) {
            // Handle exceptions, e.g., IOException or DocumentException
            e.printStackTrace();
            return null;
        }
    }
}