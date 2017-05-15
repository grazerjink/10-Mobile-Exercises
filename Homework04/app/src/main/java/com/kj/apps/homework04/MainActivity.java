package com.kj.apps.homework04;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int total1 = 0;
    int total2 = 0;
    int totalGrandma = 0;
    boolean isStarting = false;
    Random rd = new Random();

    ProgressBar proMonster1, proMonster2, proGrandma, proClock;
    TextView tvMonster1, tvMonster2, tvGrandma, tvClock;
    Button btnStart, btnCancel;

    CountDownTimer countDowntimer;
    CountDownTimer grandmaIimer;
    M1Thread t1;
    M2Thread t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    void addControls() {
        tvMonster1 = (TextView) findViewById(R.id.tvMonster1Eaten);
        tvMonster2 = (TextView) findViewById(R.id.tvMonster2Eaten);
        tvGrandma = (TextView) findViewById(R.id.tvGrandmaEaten);
        tvClock = (TextView) findViewById(R.id.tvClock);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnStart = (Button) findViewById(R.id.btnStart);
        proMonster1 = (ProgressBar) findViewById(R.id.proMonster1);
        proMonster2 = (ProgressBar) findViewById(R.id.proMonster2);
        proGrandma = (ProgressBar) findViewById(R.id.proGrandma);
        proGrandma.setVisibility(View.INVISIBLE);
        proClock = (ProgressBar) findViewById(R.id.proClock);

        proMonster1.setScaleY(5);
        proMonster2.setScaleY(5);
        proGrandma.setMax(5);
        proClock.setScaleY(5);

        thietLapBanDau();
    }

    void addEvents() {
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

    void thietLapBanDau() {
        proGrandma.setVisibility(View.INVISIBLE);

        String clock_time = String.format(getString(R.string.clock_time).toString(), 120);
        tvClock.setText(clock_time);
        proClock.setMax(120);
        proClock.setProgress(120);

        String bakes = String.format(getString(R.string.total_grandma).toString(), 0);
        tvGrandma.setText(bakes);
        totalGrandma = 0;
        proGrandma.setProgress(0);

        String total_1 = String.format(getString(R.string.total_monster_1).toString(), 0);
        tvMonster1.setText(total_1);
        total1 = 0;
        proMonster1.setMax(1);
        proMonster1.setProgress(1);

        String total_2 = String.format(getString(R.string.total_monster_2).toString(), 0);
        tvMonster2.setText(total_2);
        total2 = 0;
        proMonster2.setMax(1);
        proMonster2.setProgress(1);
    }

    void xuLyKetThuc() {
        if (countDowntimer != null) countDowntimer.cancel();
        if (grandmaIimer != null) grandmaIimer.cancel();
        isStarting = false;
        t1.dunglai();
        t2.dunglai();
        thietLapBanDau();
        Toast.makeText(this, "Cuộc thi đã dừng lại....!", Toast.LENGTH_SHORT).show();
    }

    void xuLyBatDau() {
        if (isStarting == false) {
            isStarting = true;
            thietLapBanDau();
            startSimulation();
        } else {
            Toast.makeText(this, "Cuộc thi đang diễn ra....!", Toast.LENGTH_SHORT).show();
        }
    }

    void startSimulation() {
        proGrandma.setVisibility(View.VISIBLE);
        startBakeCookie();
        t1 = new M1Thread();
        t2 = new M2Thread();
        t1.tienhanh();
        t2.tienhanh();
        t1.start();
        t2.start();
        countDowntimer = new CountDownTimer(120 * 1000, 992) {
            int i = 120;

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

    void startBakeCookie() {
        grandmaIimer = new CountDownTimer(5000, 996) {
            int i = 0;

            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                synchronized ((Integer) totalGrandma) {
                    totalGrandma += 10;
                }
                String cookie_baked = String.format(getString(R.string.total_grandma).toString(), totalGrandma);
                tvGrandma.setText(cookie_baked);
                startBakeCookie();
            }
        }.start();
    }

    class M1Thread extends Thread {
        boolean isrunning = false;

        @Override
        public void run() {
            while (isrunning) {
                if (totalGrandma > 0) {
                    int time = rd.nextInt(4) + 1;
                    proMonster1.setMax(time);
                    proMonster1.setProgress(time);
                    for (int i = time; i >= 0; i--) {
                        try {
                            proMonster1.setProgress(i);
                            this.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    tvMonster1.post(new Runnable() {
                        @Override
                        public void run() {
                            if (!isrunning) {
                                thietLapBanDau();
                            } else {
                                String total = String.format(getString(R.string.total_monster_1).toString(), (total1 += 1));
                                tvMonster1.setText(total);
                                synchronized ((Integer) totalGrandma) {
                                    totalGrandma--;
                                }
                                String cookie_baked = String.format(getString(R.string.total_grandma).toString(), totalGrandma);
                                tvGrandma.setText(cookie_baked);
                            }
                        }
                    });
                }
            }
        }

        public void tienhanh() {
            isrunning = true;
        }

        public void dunglai() {
            isrunning = false;
        }
    }

    class M2Thread extends Thread {
        boolean isrunning = false;

        @Override
        public void run() {
            while (isrunning) {
                if (totalGrandma > 0) {
                    int time = rd.nextInt(4) + 1;
                    proMonster2.setMax(time);
                    proMonster2.setProgress(time);
                    for (int i = time; i >= 0; i--) {
                        try {
                            proMonster2.setProgress(i);
                            this.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    tvMonster1.post(new Runnable() {
                        @Override
                        public void run() {
                            if (!isrunning) {
                                thietLapBanDau();
                            } else {
                                String total = String.format(getString(R.string.total_monster_1).toString(), (total2 += 1));
                                tvMonster2.setText(total);
                                synchronized ((Integer) totalGrandma) {
                                    totalGrandma--;
                                }
                                String cookie_baked = String.format(getString(R.string.total_grandma).toString(), totalGrandma);
                                tvGrandma.setText(cookie_baked);

                            }
                        }
                    });
                }
            }
        }

        public void tienhanh() {
            isrunning = true;
        }

        public void dunglai() {
            isrunning = false;
        }
    }
}
