package project.namramuni.UIDesign;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ssaurel on 29/09/2016.
 */
public class BlurBuilder {

    private static final float BITMAP_SCALE = 0.9f;
    private static final float BLUR_RADIUS = 25f;

    public static Bitmap blur(Context context, String image) {
        try {
            URL url = new URL(image);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            int width = Math.round(myBitmap.getWidth() * BITMAP_SCALE);
            int height = Math.round(myBitmap.getHeight() * BITMAP_SCALE);
            Bitmap inputBitmap = Bitmap.createScaledBitmap(myBitmap, width, height, false);
            Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);
            RenderScript rs = RenderScript.create(context);
            ScriptIntrinsicBlur intrinsicBlur = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
            Allocation tmpIn = Allocation.createFromBitmap(rs, inputBitmap);
            Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);
            intrinsicBlur.setRadius(BLUR_RADIUS);
            intrinsicBlur.setInput(tmpIn);
            intrinsicBlur.forEach(tmpOut);
            tmpOut.copyTo(outputBitmap);
            return outputBitmap;
        }catch (Exception e ){
            return null;
        }
    }
}
