package moe.xing.videoplayer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.devbrackets.android.exomedia.listener.OnErrorListener;
import com.devbrackets.android.exomedia.listener.OnPreparedListener;
import com.devbrackets.android.exomedia.ui.widget.EMVideoView;


public class PlayerActivity extends Activity implements OnPreparedListener, OnErrorListener {
    private EMVideoView emVideoView;
    private static final String VIDEO_URL = "VIDEO_URL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_player);

        emVideoView = (EMVideoView) findViewById(R.id.video_view);
        emVideoView.setOnPreparedListener(this);
        emVideoView.setOnErrorListener(this);
        emVideoView.setVideoURI(Uri.parse(getIntent().getStringExtra(VIDEO_URL)));
    }

    @Override
    public void onPrepared() {
        emVideoView.start();
    }


    @Override
    protected void onStop() {
        super.onStop();
        emVideoView.pause();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        emVideoView.start();
    }

    /**
     * <div class="en">get start intent</div>
     * <div class="zh-CN">取得启动的 Intent</div>
     *
     * @param context <div class="en">intent context</div>
     *                <div class="zh-CN">用于生成 Intent 的 Context</div>
     * @param url     <div class="en">video url</div>
     *                <div class="zh-CN">视频链接</div>
     */
    public static Intent getStartIntent(@NonNull Context context, @NonNull String url) {
        Intent intent = new Intent(context, PlayerActivity.class);
        intent.putExtra(VIDEO_URL, url);
        return intent;
    }

    /**
     * Called to indicate an error.
     *
     * @return True if the method handled the error, false if it didn't.
     */
    @Override
    public boolean onError() {
        Toast.makeText(this, "无法加载视频", Toast.LENGTH_LONG).show();
        return true;
    }
}
