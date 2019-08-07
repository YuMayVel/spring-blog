package demo.example.blogdemo.model;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

public class PdfReport {
    public static ByteArrayInputStream postPdfViews(List<Post> postList){

        ByteArrayOutputStream out=new ByteArrayOutputStream();

        Document document=new Document();


        try {

            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(80);
            table.setWidths(new int[]{1,3,3,3});


            PdfPCell hcell;
            Font font= FontFactory.getFont(FontFactory.HELVETICA);

            hcell=new PdfPCell(new Phrase("Id",font));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell=new PdfPCell(new Phrase("Tag",font));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);


            hcell=new PdfPCell(new Phrase("Body",font));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell=new PdfPCell(new Phrase("Last Updated",font));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);



            for(Post post:postList){

                PdfPCell cell = new PdfPCell(new Phrase(post.getId().toString()));
                cell.setPaddingLeft(4);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell=new PdfPCell(new Phrase(String.valueOf(post.getTag().toString())));
                cell.setPaddingLeft(4);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);


                cell=new PdfPCell(new Phrase(post.getBody().toString()));
                cell.setPaddingLeft(4);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);


                cell=new PdfPCell(new Phrase(post.getLastUpdated().toString()));
                cell.setPaddingLeft(4);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);



            }

            PdfWriter.getInstance(document,out);
            document.open();

            document.add(table);

            document.close();


        }catch (Exception e){
            e.printStackTrace();
        }

        return  new ByteArrayInputStream(out.toByteArray());
    }
}
