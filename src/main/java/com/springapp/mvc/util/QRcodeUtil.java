package com.springapp.mvc.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.springapp.mvc.constant.SignConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: 刘菊
 * Date: 14-8-13
 * Time: 下午2:34
 */
public class QRcodeUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(QRcodeUtil.class);

    private static final String IMG_PATH = "G:\\images\\qrCode";

    private static final int LARGE_HEIGHT = 178;
    private static final int LARGE_WIDTH = 178;

    public static String generateDimensionalCode(String url,String productId) {
        StringBuilder largePic = new StringBuilder();
        StringBuffer imageName = new StringBuffer();
        imageName.append(productId);
        largePic.append(IMG_PATH).append(LARGE_WIDTH).append(imageName);
        largePic.append(SignConstants.DOT).append("png");
        encode(url, LARGE_WIDTH, LARGE_HEIGHT, largePic.toString());
        return imageName.toString();
    }

    /**
     * 编码
     *
     * @param contents
     * @param width
     * @param height
     * @param imgPath
     */
    private static void encode(String contents, int width, int height, String imgPath) {
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        // 指定纠错等级
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        // 指定编码格式
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        hints.put(EncodeHintType.MARGIN, 1);
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,
                    BarcodeFormat.QR_CODE, width, height, hints);

            MatrixToImageWriter
                    .writeToFile(bitMatrix, "png", new File(imgPath));

        } catch (Exception e) {
            LOGGER.error("生成二维码出现异常," + e);
        }
    }
}
