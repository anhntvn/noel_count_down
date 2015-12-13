package com.anhnt.noel_count_down;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MainActivity extends Activity {
    long mLngEndTime = 0;
    private TextView mTvDays;
    private TextView mTvHours;
    private ImageView mIvTuanLoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.count_down);
        mTvDays = (TextView) findViewById(R.id.tv_day);
        mTvHours = (TextView) findViewById(R.id.tv_hour);
        mIvTuanLoc = (ImageView) findViewById(R.id.iv_tuanloc);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
        try {

            mLngEndTime = sdf.parse("25122015000000").getTime();
            long timeLeft = mLngEndTime - System.currentTimeMillis();

            new CountDownTimer(mLngEndTime / 1000, 1000) {
                SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
                long mLngEndTime = sdf.parse("25122015000000").getTime();
                long pos = 0;

                public void onTick(long millisUntilFinished) {
                    int screenWidth = getBaseContext().getResources().getDisplayMetrics().widthPixels;
                    long timeLeft = mLngEndTime - System.currentTimeMillis();
                    long days = timeLeft / 1000 / 60 / 60 / 24;
                    long hours = timeLeft / 1000 / 60 / 60;
                    long mins = timeLeft / 1000 / 60;
                    long secs = timeLeft / 1000;
                    mTvDays.setText(String.valueOf(days) + " Ngày");
                    String h = "0" + (hours % 24);
                    String m = "0" + (mins % 60);
                    String si = "0" + (secs % 60);
                    String r = h.substring(h.length() - 2) + ":" + m.substring(m.length() - 2) + ":" + si.substring(si.length() - 2);
                    mTvHours.setText(r);

                    if (pos > (screenWidth - mIvTuanLoc.getWidth()))
                        pos = 0;

                    mIvTuanLoc.setX(pos);

                    pos += 20;
                }

                public void onFinish() {
                    mIvTuanLoc.setX(getBaseContext().getResources().getDisplayMetrics().widthPixels - mIvTuanLoc.getWidth());
                    MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.jinglebell);
                    try {
                        mp.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }.start();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
