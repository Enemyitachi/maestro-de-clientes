package org.acme.curp.obtieneCurpDeArchivo.Controlador;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jboss.resteasy.reactive.PartType;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.server.multipart.MultipartFormDataInput;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



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
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("leerArchivoExcel")
    public Response obtenerCurpDeArchivoExcel(@FormParam("someFile") File file) {
        try {
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0); // Asumiendo que queremos la primera hoja
            Row row = sheet.getRow(1); // Asumiendo que queremos la primera fila
            Cell cell = row.getCell(4); // Asumiendo que queremos la primera columna

            String cellValue = getCellValueAsString(cell);
            // Procesa aquí el valor de la celda

            return Response.ok("Celda leída: " + cellValue).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al leer el archivo").build();
        }
    }

    private String getCellValueAsString(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return Double.toString(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return Boolean.toString(cell.getBooleanCellValue());
            default:
                return "";
        }
    }
}




