package jorchi.datepickertest;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements DatePicker.DatePickerCallBack {

    private TextView dateTv;
    private Button datebtn;
    private DatePicker datePicker;
    private View rootView;
    private String selectYearStr = "", selectMonthStr = "", selectDayStr = "",selectMinuteStr = "",selectHourStr="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootView=findViewById(R.id.rootview);

        datebtn=(Button)findViewById(R.id.getdate_btn);//按钮

        dateTv=(TextView)findViewById(R.id.date_tv);//写字板

        datebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (datePicker == null) {
                    datePicker = new DatePicker(MainActivity.this, MainActivity.this);
                }
                datePicker.showdate(rootView, selectYearStr, selectMonthStr, selectDayStr,selectHourStr,selectMinuteStr);
            }
        });
    }


    public void datePickerConfirmOnclick(String yearStr, String monthStr, String dayStr,String HourStr,String MinuteStr) {
        if((selectDayStr+selectMonthStr+selectDayStr+selectHourStr+selectMinuteStr).equals(yearStr + monthStr + dayStr+HourStr+MinuteStr)){
            return;
        }
        selectYearStr = yearStr;
        selectMonthStr = monthStr;
        selectDayStr = dayStr;
        selectHourStr = HourStr;
        selectMinuteStr = MinuteStr;
        dateTv.setText(yearStr + monthStr + dayStr+HourStr+MinuteStr);
    }

    @Override
    public void datePickerCancelOnclick() {

    }
}
