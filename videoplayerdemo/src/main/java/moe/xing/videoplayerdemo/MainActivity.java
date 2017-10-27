package moe.xing.videoplayerdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import moe.xing.baseutils.Init;
import moe.xing.videoplayer.PlayerActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText urlText = findViewById(R.id.video_url);
        Button playBtn = findViewById(R.id.play_btn);
        Init.init(getApplication(), true, "1.0", "videoPlayer");
        playBtn.setOnClickListener(view -> startActivity(PlayerActivity.getStartIntent(MainActivity.this, urlText.getText().toString(), false)));
    }
}
