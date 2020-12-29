package com.example.pbs.Fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.pbs.Activity.MainActivity;
import com.example.pbs.Modle.Invoice;
import com.example.pbs.R;
import com.example.pbs.utile.HeaderFooterPageEvent;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPTableHeader;
import com.itextpdf.text.pdf.PdfWriter;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import static android.icu.lang.UCharacter.WordBreak.NEWLINE;
import static android.os.Environment.DIRECTORY_DOCUMENTS;
import static android.os.Environment.DIRECTORY_DOWNLOADS;
import static android.os.Environment.DIRECTORY_MUSIC;
import static android.os.Environment.getExternalStorageDirectory;
import static android.os.Environment.getExternalStoragePublicDirectory;


public class ERBInvoice extends Fragment {
    Toolbar toolbar;
    private File pdfFile;
    private static final String TAG = "PdfCreatorActivity";
    ArrayList<Invoice> my_Invoice_List;
    Context context;
    Invoice invoice;
    Invoice Category,Size,Amount,Unite_price,Taxes,Discount,Total;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 111;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        context=getActivity();
        setHasOptionsMenu(true);
        my_Invoice_List=new ArrayList<>();
       // my_Invoice_List.add(new Invoice("Drink","Big","567","4332","2%","5%","$12345"));
       // my_Invoice_List.add(new Invoice("Drink","Big","567","4332","2%","5%","$12345"));
       // my_Invoice_List.add(new Invoice("Drink","Big","567","4332","2%","5%","$12345"));
       // my_Invoice_List.add(new Invoice("Drink","Big","567","4332","2%","5%","$12345"));

    }



    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.erb_invoice, null);
        toolbar=((MainActivity)this.getActivity()).getToolbar();
        toolbar.setTitle(getString(R.string.ERP_invoices));
        toolbar.setNavigationIcon(R.drawable.arrow_forward);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();


            }
        });
        return  view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }
    @Override
    public void onResume() {
        super.onResume();
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        getActivity().getMenuInflater().inflate(R.menu.mainactionbar, menu);
        MenuItem item =menu.findItem(R.id.indebt_invoice);
        MenuItem item1=menu.findItem(R.id.actionbar_search);
        item1.setVisible(false);
        item.setVisible(false);


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.generate_pdf:
                Toast.makeText(getActivity(),getString(R.string.paid),Toast.LENGTH_LONG).show();
                try {
                    createPdfWrapper();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
                return true;
     }
        return false;
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void createPdfWrapper() throws FileNotFoundException, DocumentException {

        int hasWriteStoragePermission = getActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (hasWriteStoragePermission != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_CONTACTS)) {
                    showMessageOKCancel("You need to allow access to Storage",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                                REQUEST_CODE_ASK_PERMISSIONS);
                                    }
                                }
                            });
                    return;
                }
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_CODE_ASK_PERMISSIONS);
            }
            return;
        } else {
           createPdf();
        }
                            }
    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }
    private void createPdf()throws FileNotFoundException, DocumentException {
        // create document
        String directory_path =getExternalStoragePublicDirectory(DIRECTORY_MUSIC).getPath() + "/fragment_invoice/";
        File docsFolder = new File(directory_path);
        if (!docsFolder.exists()) {
            docsFolder.mkdirs();
            Log.i(TAG, "Created a new directory for PDF");
        }
        String pdfname = "fragment_invoice.pdf";
        pdfFile = new File(docsFolder.getAbsolutePath(), pdfname);
        Document document = new Document(PageSize.A4, 36, 36, 90, 36);
        PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream(pdfFile));
        HeaderFooterPageEvent event = new HeaderFooterPageEvent();
         writer.setPageEvent(event);

//        Document  document = new Document(PageSize.A4);
//        PdfPTable pt = new PdfPTable(3);
//        pt.getDefaultCell().setFixedHeight(50);
//        pt.setWidthPercentage(100);
//        float[] fl = new float[]{50, 20, 20};
//        pt.setWidths(fl);
//        pt.getDefaultCell().setBorder(Rectangle.NO_BORDER);
//        pt.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
//        pt.getDefaultCell().setBackgroundColor(BaseColor.LIGHT_GRAY);
//        pt.deleteBodyRows();
//        pt.getFooter();
//        pt.addCell("company logo");
//        pt.addCell("another Details");
//        pt.addCell("Area:Sana'a");
//
//
//        PdfPTable table = new PdfPTable(new float[]{1,3, 3, 3, 3, 3,3,3});
//        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.getDefaultCell().setFixedHeight(50);
//
//        table.setTotalWidth(PageSize.A4.getWidth());
//        table.setWidthPercentage(100);
//        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
//        table.addCell("M");
//        table.addCell("Category");
//        table.addCell("Size");
//        table.addCell("Amount");
//        table.addCell("Unite_Price");
//        table.addCell("Taxes");
//        table.addCell("Discount");
//        table.addCell("Total");
//        table.setHeaderRows(1);
//        PdfPCell[] cells = table.getRow(0).getCells();
//        for (int j = 0; j < cells.length; j++) {
//            cells[j].setBackgroundColor(BaseColor.GRAY);}
//            for (int i = 0; i < my_Invoice_List.size(); i++) {
//                Category = my_Invoice_List.get(i);
//               Size = my_Invoice_List.get(i);
//               Amount = my_Invoice_List.get(i);
//                Unite_price = my_Invoice_List.get(i);
//                Taxes = my_Invoice_List.get(i);
//                Discount= my_Invoice_List.get(i);
//               Total = my_Invoice_List.get(i);
//
//                String category = Category.getCategory();
//                String size = Size.getSize();
//                String amount = Amount.getAmount();
//                String unite_price = Unite_price.getUnite_Price();
//                String taxes =  Taxes.getTaxes();
//               String discount= Discount.getDiscount();
//                String total=Total.getTotal();
//
//                table.addCell(String.valueOf(category));
//                table.addCell(String.valueOf(size));
//                table.addCell(String.valueOf(amount));
//                table.addCell(String.valueOf(unite_price));
//                table.addCell(String.valueOf(taxes));
//                table.addCell(String.valueOf(discount));
//                table.addCell(String.valueOf(total));
//            }
//               System.out.println("Done");
//        PdfWriter writer = PdfWriter.getInstance(document, output);
        // add header and footer

        // write to document
        document.open();
        document.add(new Paragraph("Adding a header to PDF Document using iText."));
        document.newPage();
        document.add(new Paragraph("Adding a footer to PDF Document using iText."));
        document.close();
//        Font f = new Font(Font.FontFamily.TIMES_ROMAN, 20.0f, Font.BOLD, BaseColor.BLUE);
//        Font g = new Font(Font.FontFamily.TIMES_ROMAN, 20.0f, Font.NORMAL, BaseColor.BLUE);
//        document.add(pt);
//        Paragraph paragraph=new Paragraph();
//        paragraph.setAlignment(120);
//        paragraph.size();
//
//        document.add(paragraph);
//        document.add(new Paragraph("\n\n"));
//        document.add(table);
//
////        for (int i = 0; i < MyList1.size(); i++) {
////            document.add(new Paragraph(String.valueOf(MyList1.get(i))));
////        }
//        document.close();
//        Log.e("safiya", my_Invoice_List.toString());
        previewPdf();

            }
    private void previewPdf() {

        PackageManager packageManager = context.getPackageManager();
        Intent testIntent = new Intent(Intent.ACTION_VIEW);
        testIntent.setType("application/pdf");
        List list = packageManager.queryIntentActivities(testIntent, PackageManager.MATCH_DEFAULT_ONLY);
        if (list.size() > 0) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            Uri uri = Uri.fromFile(pdfFile);
            intent.setDataAndType(uri, "application/pdf");
            context.startActivity(intent);
        } else {
            Toast.makeText(context, "Download a PDF Viewer to see the generated PDF", Toast.LENGTH_SHORT).show();
        }
    }



}
