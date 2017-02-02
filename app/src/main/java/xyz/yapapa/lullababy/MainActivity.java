package xyz.yapapa.lullababy;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private Button mStartButton;
    private Button mStopButton;
    private ProgressBar progressBar;
    public TextView mTextField;
    CountDownTimer cTimer=null;
    private ObjectAnimator animation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        animation = ObjectAnimator.ofInt (progressBar, "progress", 0, 500); //
        animation.setDuration (30000); //in milliseconds
        animation.setInterpolator (new DecelerateInterpolator());


        mTextField = (TextView) findViewById(R.id.textfield);

        mStartButton = (Button) findViewById(R.id.btn_start);
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // нажимаем старт
                cancelTimer();
                startTimer();

            }
        });

        mStopButton = (Button) findViewById(R.id.btn_stop);
        mStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //нажимаем стоп
                cancelTimer();
            }
        });
    }

    

    void startTimer() {

        cTimer = new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);

            }

            @Override
            public void onFinish() {
                mTextField.setText("done!");
            }

        };
        cTimer.start();
        animation.start ();
    }

    void cancelTimer(){
        if (cTimer!=null)
            cTimer.cancel();
        progressBar.clearAnimation();
    }
}
