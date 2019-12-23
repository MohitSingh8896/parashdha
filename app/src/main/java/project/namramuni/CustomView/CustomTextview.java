package project.namramuni.CustomView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import project.namramuni.R;


@SuppressLint("AppCompatCustomView")
public class CustomTextview extends TextView {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomTextview(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    public CustomTextview(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    public CustomTextview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);

    }

    public CustomTextview(Context context) {
        super(context);
        init(null);
    }


    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CustomView);
            String fontName = a.getString(R.styleable.CustomView_font1);

          /*  TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.DTextView, 0, 0);
            justify = ta.getBoolean(R.styleable.DTextView_justify, false);
*/

            try {
                if (fontName != null) {
                    Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), fontName);
                    setTypeface(myTypeface);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

//            ta.recycle();
            a.recycle();
        }
    }
}