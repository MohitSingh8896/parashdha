package project.namramuni.UIDesign;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.widget.ImageView;

import com.google.android.youtube.player.YouTubeThumbnailView;

import androidx.cardview.widget.CardView;
import project.namramuni.Adapter.RoundCornersDrawable;
import project.namramuni.R;

public class ImageCorner {
    public static void setCorner(Context context, ImageView imageView, CardView cardView, Integer img) {
        Bitmap mBitmap = BitmapFactory.decodeResource(context.getResources(), img);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            //Default
            imageView.setBackgroundResource(img);
        } else {
            //RoundCorners
            RoundCornersDrawable round = new RoundCornersDrawable(mBitmap, context.getResources().getDimension(R.dimen._1sdp), 0); //or your custom radius
//            cardView.setPreventCornerOverlap(false); //it is very important
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
                imageView.setBackground(round);
            else
                imageView.setBackgroundDrawable(round);
        }
    }

    public static void setYoutubCorner(Context context, YouTubeThumbnailView imageView, CardView cardView, Integer img) {
        Bitmap mBitmap = BitmapFactory.decodeResource(context.getResources(), img);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //Default
            imageView.setBackgroundResource(img);
        } else {
            //RoundCorners
            RoundCornersDrawable round = new RoundCornersDrawable(mBitmap, context.getResources().getDimension(R.dimen._5sdp), 0); //or your custom radius
            cardView.setPreventCornerOverlap(false); //it is very important
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
                imageView.setBackground(round);
            else
                imageView.setBackgroundDrawable(round);
        }
    }
}
