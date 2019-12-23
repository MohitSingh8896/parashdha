package project.namramuni.UIDesign;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.Button;
import android.widget.TextView;

public class SetFont {
    public static String font8 = "roboto/Roboto-Medium.ttf";
    public static String font11 = "roboto/Roboto-Thin.ttf";
//    public static String bold = "roboto/Roboto-Medium.ttf";
//    public static String normal = "roboto/Roboto-Thin.ttf";
    public static String bold = "cambria/FedraSerifPro A Bold.otf";
    public static String normal = "cambria/FedraSerifPro A Book.otf";
    public static String signature = "bedtime stories.ttf";

    public static void setFont(TextView txttitle, Context context, String font){
        Typeface tf = Typeface.createFromAsset(context.getAssets(), font);
        txttitle.setTypeface(tf);
    }
    public static void setFont1(Button txttitle, Context context, String font){
        Typeface tf = Typeface.createFromAsset(context.getAssets(), font);
        txttitle.setTypeface(tf);
    }

}
