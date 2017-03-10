package moe.xing.vedioplayerdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import moe.xing.videoplayer.PlayerActivity;


public class MainActivity extends AppCompatActivity {

    private Button playBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playBtn = (Button) findViewById(R.id.play_btn);
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(PlayerActivity.getStartIntent(MainActivity.this,"http://omfeajc2u.bkt.clouddn.com/%E4%B8%AD%E7%8F%AD1%E3%80%81%E5%90%89%E5%A7%86%E7%86%8A%E5%92%8C%E4%BB%96%E7%9A%84%E6%9C%8B%E5%8F%8B%EF%BC%88%E4%BA%8C%EF%BC%89%E2%80%94%E8%81%9A%E4%BC%9A%E4%B8%8A%E7%9A%84%E6%84%8F%E5%A4%96.m4v"));
            }
        });
    }
}
