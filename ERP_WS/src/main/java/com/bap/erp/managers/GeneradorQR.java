package com.bap.erp.managers;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

public class GeneradorQR {
    
    private static final int qr_image_width = 400;
    private static final int qr_image_height = 400;
    private static final String IMAGE_FORMAT = "jpg";

    public static void generaCodeQr(String mensaje, String nombreQR) {
        try {
            BitMatrix matrix;
            Writer writer = new QRCodeWriter();
            try {
                matrix = writer.encode(mensaje, BarcodeFormat.QR_CODE, qr_image_width, qr_image_height);
            } catch (WriterException e) {
                e.printStackTrace(System.err);
                return;
            }
            BufferedImage image = new BufferedImage(qr_image_width, qr_image_height, BufferedImage.TYPE_INT_RGB);
            for (int y = 0; y < qr_image_height; y++) {
                for (int x = 0; x < qr_image_width; x++) {
                    int grayValue = (matrix.get(x, y) ? 0 : 1) & 0xff;
                    image.setRGB(x, y, (grayValue == 0 ? 0 : 0xFFFFFF));
                }
            }
//            String qrPath = (String) servletContext.getRealPath("/WEB-INF/uploaded/");
//            FileOutputStream qrCode = new FileOutputStream(qrPath + File.separator + nombreQR + ".jpg");
//            ImageIO.write(image, IMAGE_FORMAT, qrCode);
//            qrCode.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
