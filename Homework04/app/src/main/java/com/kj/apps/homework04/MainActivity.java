package com.kj.apps.homework04;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    ProgressBar proMonster1, proMonster2, proGrandma, proClock;
    TextView tvMonster1, tvMonster2, tvGrandma, tvClock;
    Button btnStart, btnCancel;
    CountDownTimer countDowntimer;
    CountDownTimer grandmaIimer;
    CountDownTimer monster1Timer;
    CountDownTimer monster2Timer;
    Runnable run1;
    Runnable run2;
    int total1;
    int total2;
    int totalGrandma;
    boolean isStarting = false;
    final Random rd = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addControls() {
        proMonster1 = (ProgressBar) findViewById(R.id.proMonster1);
        proMonster1.setScaleY(5);
        proMonster2 = (ProgressBar) findViewById(R.id.proMonster2);
        proMonster2.setScaleY(5);

        proGrandma = (ProgressBar) findViewById(R.id.proGrandma);
        proGrandma.setVisibility(View.INVISIBLE);
        proGrandma.setMax(5);
        proGrandma.setProgress(0);

        proClock = (ProgressBar) findViewById(R.id.proClock);
        proClock.setScaleY(5);
        proClock.setMax(120);
        proClock.setProgress(120);
        tvMonster1 = (TextView) findViewById(R.id.tvMonster1Eaten);
        tvMonster2 = (TextView) findViewById(R.id.tvMonster2Eaten);
        tvGrandma = (TextView) findViewById(R.id.tvGrandmaEaten);
        tvClock = (TextView) findViewById(R.id.tvClock);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnStart = (Button) findViewById(R.id.btnStart);
        thietLapBanDau();

        run1 = new Runnable() {
            @Override
            public void run() {
                if (totalGrandma > 1) {
                    final int rd_time = rd.nextInt(5) + 1;
                    proMonster1.setMax(rd_time);
                    proMonster1.setProgress(rd_time);
                    monster1Timer = new CountDownTimer(rd_time * 1000, 990) {
                        int i = rd_time;

                        @Override
                        public void onTick(long millisUntilFinished) {
                            --i;
                            try {
                                Thread.sleep(150);
                                proMonster1.setProgress(i);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFinish() {
                            updateTotalCookies();
                            total1++;
                            String cookie_eaten = String.format(getString(R.string.total_monster_1).toString(), total1);
                            tvMonster1.setText(cookie_eaten);
                            proMonster1.setProgress(0);
                            proMonster1.postDelayed(run1, 500);
                        }
                    }.start();
                } else {
                    proMonster1.postDelayed(run1, 500);
                }
            }
        };

        run2 = new Runnable() {
            @Override
            public void run() {
                if (totalGrandma > 1) {
                    final int rd_time = rd.nextInt(5) + 1;
                    proMonster2.setMax(rd_time);
                    proMonster2.setProgress(rd_time);
                    monster2Timer = new CountDownTimer(rd_time * 1000, 990) {
                        int i = rd_time;

                        @Override
                        public void onTick(long millisUntilFinished) {
                            --i;
                            try {
                                Thread.sleep(150);
                                proMonster2.setProgress(i);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFinish() {
                            updateTotalCookies();
                            total2++;
                            String cookie_eaten = String.format(getString(R.string.total_monster_2).toString(), total2);
                            tvMonster2.setText(cookie_eaten);
                            proMonster2.setProgress(0);
                            proMonster2.postDelayed(run2, 500);
                        }
                    }.start();
                } else {
                    proMonster2.postDelayed(run2, 500);
                }
            }
        };
    }

    private void addEvents() {
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyBatDau();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyKetThuc();
            }
        });
    }

    private void thietLapBanDau() {
        String clock_time = String.format(getString(R.string.clock_time).toString(), 120);
        tvClock.setText(clock_time);
        proClock.setProgress(120);

        String bakes = String.format(getString(R.string.total_grandma).toString(), 0);
        tvGrandma.setText(bakes);
        totalGrandma = 0;
        proGrandma.setProgress(0);

        String total_1 = String.format(getString(R.string.total_monster_1).toString(), 0);
        tvMonster1.setText(total_1);
        total1 = 0;
        proMonster1.setMax(100);
        proMonster1.setProgress(100);

        String total_2 = String.format(getString(R.string.total_monster_2).toString(), 0);
        tvMonster2.setText(total_2);
        total2 = 0;
        proMonster2.setMax(100);
        proMonster2.setProgress(100);


    }

    private void xuLyKetThuc() {
        proGrandma.setVisibility(View.INVISIBLE);
        isStarting = false;
        thietLapBanDau();
        if (countDowntimer != null) {
            countDowntimer.cancel();
        }
        if (grandmaIimer != null) {
            grandmaIimer.cancel();
        }
        if (monster1Timer != null) {
            monster1Timer.cancel();
        }
        if (monster2Timer != null) {
            monster2Timer.cancel();
        }
    }

    private void xuLyBatDau() {
        proGrandma.setVisibility(View.VISIBLE);
        if (isStarting == false) {
            isStarting = true;
            thietLapBanDau();
            startSimulation(120);
        } else {
            Toast.makeText(this, "Cuộc thi đang diễn ra....!", Toast.LENGTH_SHORT).show();
        }
    }

    private void startSimulation(final int time) {
        startBakeCookie();
        startEating();
        countDowntimer = new CountDownTimer(time * 1000, 992) {
            int i = time;

            @Override
            public void onTick(long millisUntilFinished) {
                --i;
                proClock.setProgress(i);
                String clock_time = String.format(getString(R.string.clock_time).toString(), i);
                tvClock.setText(clock_time);
            }

            @Override
            public void onFinish() {
                if (total1 == 100)
                    Toast.makeText(MainActivity.this, "Monster 1 is WINNER !", Toast.LENGTH_LONG).show();
                else if (total2 == 100)
                    Toast.makeText(MainActivity.this, "Monster 2 is WINNER !", Toast.LENGTH_LONG).show();
                else if (total1 < total2)
                    Toast.makeText(MainActivity.this, "Monster 2 is WINNER !", Toast.LENGTH_LONG).show();
                else if (total1 > total2)
                    Toast.makeText(MainActivity.this, "Monster 1 is WINNER !", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "The match is DRAW!", Toast.LENGTH_LONG).show();
                xuLyKetThuc();
            }
        }.start();
    }

    private void startBakeCookie() {
        grandmaIimer = new CountDownTimer(5000, 996) {
            int i = 0;

            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                if (totalGrandma < 50) {
                    totalGrandma += 10;
                    String cookie_baked = String.format(getString(R.string.total_grandma).toString(), totalGrandma);
                    tvGrandma.setText(cookie_baked);
                }
                startBakeCookie();
            }
        }.start();
    }

    private void updateTotalCookies() {
        totalGrandma--;
        String cookie_baked = String.format(getString(R.string.total_grandma).toString(), totalGrandma);
        tvGrandma.setText(cookie_baked);
    }

    private void startEating() {
        proMonster1.postDelayed(run1,1000);
        proMonster2.postDelayed(run2,1000);
    }
}
