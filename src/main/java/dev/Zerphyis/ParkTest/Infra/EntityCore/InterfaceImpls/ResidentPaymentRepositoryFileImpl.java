package dev.Zerphyis.ParkTest.Infra.EntityCore.InterfaceImpls;

import dev.Zerphyis.ParkTest.Domain.Interfaces.ResidentPaymentRepository;
import dev.Zerphyis.ParkTest.Infra.Exceptions.FileNotGenerateException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;

public class ResidentPaymentRepositoryFileImpl implements ResidentPaymentRepository {
    @Override
    public void saveMonthlyReport(String fileName, String content) {
        try (PDDocument document = new PDDocument()) {

            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream stream = new PDPageContentStream(document, page)) {
                stream.beginText();
                stream.setFont(PDType1Font.HELVETICA, 12);
                stream.setLeading(14.5f);
                stream.newLineAtOffset(50, 750);

                for (String line : content.split("\n")) {
                    stream.showText(line);
                    stream.newLine();
                }

                stream.endText();
            }

            document.save(fileName);

        } catch (IOException e) {
            throw new FileNotGenerateException("Erro ao gerar relatório de residentes", e);
        }
    }
}
