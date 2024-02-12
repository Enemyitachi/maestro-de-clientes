package org.acme.curp.obtieneCurpDeArchivo.Controlador;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

@Path("/archivo")
@Consumes(MediaType.MULTIPART_FORM_DATA)
public class ObtieneCurpDeArchivoController {

    @POST
    @Path("/leer")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public String ObtieneCurpDeArchivo(InputStream input) throws IOException {
        try {
            PDDocument document = PDDocument.load(input);

            PDFTextStripper pdfStripper = new PDFTextStripper();

            String text = pdfStripper.getText(document);

            String regex = "[A-Z]{4}[0-9]{6}[H,M][A-Z]{5}[A-Z,0-9][0-9]";

            Pattern pattern = Pattern.compile(regex);

            Matcher matcher = pattern.matcher(text);
            if (matcher.find()) {
                System.out.println("CURP encontrada: " + matcher.group(0));
            } else {
                System.out.println("No se encontró ninguna CURP en el archivo.");
            }
            return matcher.group();
        }catch (IOException e){
            e.printStackTrace();
            return "error";
        }
    }
}
