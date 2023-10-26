package vn.aptech.powerofspeed.service;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;
import java.io.ByteArrayOutputStream;

@Service
public class PdfGeneratorService {

    private final TemplateEngine templateEngine;

    public PdfGeneratorService(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public byte[] generatePdfFromHtml(String htmlContent) throws Exception {
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(htmlContent);
        renderer.layout();

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            renderer.createPDF(outputStream);
            return outputStream.toByteArray();
        }
    }

    public byte[] generatePdfFromThymeleafTemplate(String templateName, Context context) throws Exception {
        String htmlContent = templateEngine.process(templateName, context);
        return generatePdfFromHtml(htmlContent);
    }
}
