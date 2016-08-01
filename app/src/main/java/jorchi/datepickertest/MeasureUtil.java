package jorchi.datepickertest;

import android.util.TypedValue;


public class MeasureUtil {

    public static int dip2px(int dip) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip,MyApplication.getInstance().getResources().getDisplayMetrics());
    }

}