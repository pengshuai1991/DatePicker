package jorchi.datepickertest;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;

import jorchi.datepickertest.wheel.ArrayWheelAdapter;
import jorchi.datepickertest.wheel.OnWheelChangedListener;
import jorchi.datepickertest.wheel.OnWheelScrollListener;
import jorchi.datepickertest.wheel.WheelView;

/**
 * 时间选择器
 * Created by yml on 16/6/2.
 */
public class DatePicker extends PopupWindow implements OnWheelChangedListener {
    private Context context;
    private View rootView;
    private DatePickerCallBack callBack;

    private TextView confirmBtn, cancelBtn;
    //TODO
    private WheelView yearWV, monthWV, dayWV,hourWV,minuteWV;

    /** 是否在滚动 **/
    private boolean                 isScrolling;
    private static final int        visibleItem  = 5;
    private ArrayList<String> yearlist;
    private ArrayList<String> monthlist;
    private ArrayList<String> daylist;
    private ArrayList<String> hourlist;
    private ArrayList<String> minutelist;

    public DatePicker(Context context, DatePickerCallBack callBack) {
        super();
        this.context = context;
        this.callBack = callBack;
        initView();
        initRegistClickListener();
        initData();
    }


    private void initView() {
        rootView = LayoutInflater.from(context).inflate(R.layout.pop_datetime_layout, null);
        setContentView(rootView);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0x61000000);
        setBackgroundDrawable(dw);
        confirmBtn = (TextView) rootView.findViewById(R.id.confirm_btn);
        cancelBtn = (TextView) rootView.findViewById(R.id.cancel_btn);

        yearWV = (WheelView) rootView.findViewById(R.id.year_wv);
        monthWV = (WheelView) rootView.findViewById(R.id.month_wv);
        dayWV = (WheelView) rootView.findViewById(R.id.day_wv);
        hourWV = (WheelView) rootView.findViewById(R.id.hour_wv);
        minuteWV =  (WheelView)rootView.findViewById(R.id.minute_wv);

        yearWV.setVisibleItems (visibleItem);
        monthWV.setVisibleItems (visibleItem);
        dayWV.setVisibleItems (visibleItem);
        hourWV.setVisibleItems(visibleItem);
        minuteWV.setVisibleItems(visibleItem);

        yearWV.setLabel("年");
        monthWV.setLabel("月");
        dayWV.setLabel("日");
        hourWV.setLabel("时");
        minuteWV.setLabel("分");

        rootView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int topheight = rootView.findViewById(R.id.win_content_rl).getTop();
                int leftwidth = rootView.findViewById(R.id.win_content_rl).getLeft();
                int bottomheight = rootView.findViewById(R.id.win_content_rl).getBottom();
                int rightwidth = rootView.findViewById(R.id.win_content_rl).getRight();
                int y = (int) event.getY();
                int x = (int) event.getX();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < topheight || y > bottomheight) {
                        dismiss();
                    }
                    if (x < leftwidth || x > rightwidth) {
                        dismiss();
                    }
                }

                return true;
            }
        });
    }

    public interface DatePickerCallBack {
        void datePickerConfirmOnclick(String yearStr, String monthStr, String dayStr,String hourStr,String minuteStr);

        void datePickerCancelOnclick();
    }

    private void initRegistClickListener() {
        yearWV.addChangingListener (this);
        monthWV.addChangingListener (this);
        dayWV.addChangingListener (this);
        minuteWV.addChangingListener(this);
        hourWV.addChangingListener(this);

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    //TODO
                    callBack.datePickerConfirmOnclick(yearlist.get (yearWV.getCurrentItem ()),
                            monthlist.get (monthWV.getCurrentItem ()),
                            daylist.get (dayWV.getCurrentItem ()),
                            hourlist.get(hourWV.getCurrentItem()),
                            minutelist.get(minuteWV.getCurrentItem())
                            );
                }
                dismiss();
            }
        });


        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void initData() {
        yearlist = DateUtils.getYeasList();
        monthlist=DateUtils.getMonthList(DateUtils.getCurrentYear()+"",false);
        daylist=DateUtils.getDayList(DateUtils.getCurrentYear()+"",DateUtils.getCurrentMonth()+"");
        Log.i(Config.TAG,"daylist="+daylist.size()+" "+monthlist.size()+" "+yearlist.size()+"");
        //TODO
        hourlist=DateUtils.getHourList();
        minutelist = DateUtils.getMinuteList();

        Log.i(Config.TAG,"hourlist"+hourlist.size()+" "+minutelist.size());

        updateYearWheelView (DateUtils.getCurrentYear()+"");

        updateMonthWheelView (DateUtils.getCurrentYear()+"", DateUtils.getCurrentMonth()+"");
        updateDayWheelView (DateUtils.getCurrentYear()+"",DateUtils.getCurrentMonth()+"", null);
        updateHourWheelView(DateUtils.getCurrentYear()+"",DateUtils.getCurrentMonth()+"",DateUtils.getCurrentDay()+"",DateUtils.getHour());
        updateMinuteWheelView(DateUtils.getCurrentYear()+"",DateUtils.getCurrentMonth()+"",DateUtils.getCurrentDay()+"",
                DateUtils.getHour()+"",DateUtils.getMinutes()+"");

        Log.i(Config.TAG,"year"+DateUtils.getCurrentYear()+"month"+DateUtils.getCurrentMonth()+"day"+DateUtils.getCurrentDay()+"hour"+DateUtils.getHour()+"minutes"+DateUtils.getMinutes()+"");



    }


    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        if (wheel == yearWV && !isScrolling) {
            updateMonthWheelView (yearlist.get (newValue), null);
        } else if (wheel == monthWV && !isScrolling) {
            updateDayWheelView (yearlist.get (yearWV.getCurrentItem ()),monthlist.get (newValue), null);
        }
    }
    private void updateYearWheelView(String selectedyearStr){
        if (yearlist != null) {
            yearWV.setAdapter(new ArrayWheelAdapter<String>(yearlist.toArray(new String[yearlist.size()]), yearlist.size()));
            yearWV.setCyclic (false);
            yearWV.addScrollingListener (new OnWheelScrollListener() {

                @Override
                public void onScrollingStarted(WheelView wheel){
                    isScrolling = true;
                }

                @Override
                public void onScrollingFinished(WheelView wheel){
                    isScrolling = false;
                    int index = wheel.getCurrentItem ();
                    updateMonthWheelView (yearlist.get (index), null);
                }
            });

            int selected = yearlist.size ()-2;
            if (!StringUtils.isEmpty (selectedyearStr)) {
                selected = yearlist.indexOf (selectedyearStr);
                if (selected == -1) {
                    selected = 0;
                }
            }
            yearWV.setCurrentItem (selected);
            updateMonthWheelView (yearlist.get (selected), null);
        }

    }

    private void updateMonthWheelView(final String selectedyearStr,final String selectedmonthStr){
        monthlist=DateUtils.getMonthList(selectedyearStr,false);
        if (monthlist != null) {
            monthWV.setAdapter(new ArrayWheelAdapter<String>(monthlist.toArray(new String[monthlist.size()]), monthlist.size()));
            monthWV.setCyclic (false);
            monthWV.addScrollingListener (new OnWheelScrollListener () {

                @Override
                public void onScrollingStarted(WheelView wheel){
                    isScrolling = true;
                }

                @Override
                public void onScrollingFinished(WheelView wheel){
                    isScrolling = false;
                    int index = wheel.getCurrentItem ();
                    updateDayWheelView (selectedyearStr,monthlist.get (index), null);
                }
            });

            int selected = 0;
            if (!StringUtils.isEmpty (selectedmonthStr)) {
                selected = monthlist.indexOf (StringUtils.getDateFormateString(Integer.parseInt(selectedmonthStr)));
                if (selected == -1) {
                    selected = 0;
                }
            }
            monthWV.setCurrentItem (selected);
            updateDayWheelView (selectedyearStr,monthlist.get (selected), null);
        }
    }

    private void updateDayWheelView(String yearStr,String monthStr,String dateStr){
        daylist = DateUtils.getDayList(yearStr,monthStr);
        if (daylist != null) {
            dayWV.setAdapter(new ArrayWheelAdapter<String>(daylist.toArray(new String[daylist.size()]), daylist.size()));
            dayWV.setCyclic (false);

            int selected = 0;
            if (!StringUtils.isEmpty (dateStr)) {
                selected = daylist.indexOf (StringUtils.getDateFormateString(Integer.parseInt(dateStr)));
                if (selected == -1) {
                    selected = 0;
                }
            }
            dayWV.setCurrentItem (selected);
        }
    }
    //填充小时栏适配器
    private void updateHourWheelView(String yearStr,String monthStr,String dateStr,String hourStr){

        hourlist = DateUtils.getHourList();
        if(hourlist != null){
            hourWV.setAdapter(new ArrayWheelAdapter<String>(hourlist.toArray(new String[hourlist.size()]),daylist.size()));
            hourWV.setCyclic(false);

            int selected = 0;
            if(!StringUtils.isEmpty(hourStr)){
                selected = hourlist.indexOf(StringUtils.getDateFormateString(Integer.parseInt(hourStr)));
                if(selected == -1){
                    selected = 0;
                }
            }
            hourWV.setCurrentItem(selected);
        }
    }


 //  填充分钟适配器
    private void updateMinuteWheelView(String yearStr,String monthStr,String dateStr,String hourStr,String minuteStr){
        minutelist = DateUtils.getMinuteList();
        Log.i(Config.TAG,minutelist.size()+minutelist.get(5));
        if(minutelist != null){
            minuteWV.setAdapter(new ArrayWheelAdapter<String>(minutelist.toArray(new String[minutelist.size()]),minutelist.size()));
            minuteWV.setCyclic(false);

            int selected = 0;
            if(!StringUtils.isEmpty(hourStr)){
                selected = minutelist.indexOf(StringUtils.getDateFormateString(Integer.parseInt(minuteStr)));
                if(selected == -1){
                    selected = 0;
                }
            }
            Log.i(Config.TAG,"selected="+selected);
            minuteWV.setCurrentItem(selected);
        }
    }

    public void show(View mRootView, String yearStr, String monthStr, String dayStr) {
        if(StringUtils.isEmpty(yearStr)){
            yearStr=DateUtils.getCurrentYear()+"";
        }
        updateYearWheelView (yearStr);

        if(StringUtils.isEmpty(monthStr)){
            monthStr=DateUtils.getCurrentMonth()+"";
        }

        updateMonthWheelView (yearStr, monthStr);

        if(StringUtils.isEmpty(dayStr)){
            dayStr=DateUtils.getCurrentDay()+"";
        }

        updateDayWheelView (yearStr,monthStr, dayStr);

        showAtLocation(mRootView, Gravity.CENTER, 0, 0);
    }

    public void showdate(View mRootView,String yearStr,String monthStr,String dayStr,String hourStr,String minuteStr){
        if(StringUtils.isEmpty(yearStr)){
            yearStr = DateUtils.getCurrentYear()+"";
        }
        updateYearWheelView(yearStr);

        if(StringUtils.isEmpty(monthStr)){
            monthStr = DateUtils.getCurrentMonth()+"";
        }
        updateMonthWheelView(yearStr,monthStr);

        if(StringUtils.isEmpty(dayStr)){
            dayStr = DateUtils.getCurrentDay()+"";
        }
        updateDayWheelView(yearStr,monthStr,dayStr);

        if(StringUtils.isEmpty(hourStr)){
            hourStr = DateUtils.getHour()+"";
        }
        updateHourWheelView(yearStr,monthStr,dayStr,hourStr);


        if(StringUtils.isEmpty(minuteStr)){
            minuteStr = DateUtils.getMinutes()+"";
        }
        updateMinuteWheelView(yearStr,monthStr,dayStr,hourStr,minuteStr);

        showAtLocation(mRootView,Gravity.CENTER,0,0);

    }

}

