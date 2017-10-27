package moe.xing.videoplayer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;

import com.jarvanmo.exoplayerview.ui.ExoVideoPlaybackControlView;
import com.jarvanmo.exoplayerview.ui.ExoVideoView;
import com.jarvanmo.exoplayerview.ui.SimpleMediaSource;

import moe.xing.baseutils.utils.IntentUtils;

import static com.jarvanmo.exoplayerview.ui.ExoVideoPlaybackControlView.SENSOR_LANDSCAPE;
import static com.jarvanmo.exoplayerview.ui.ExoVideoPlaybackControlView.SENSOR_PORTRAIT;


public class PlayerActivity extends Activity {
    private static final String VIDEO_URL = "VIDEO_URL";
    private static final String USE_3RD_APP = "USE_3RD_APP";
    @Nullable
    private ExoVideoView mVideoView;

    /**
     * <div class="en">get start intent</div>
     * <div class="zh-CN">取得启动的 Intent</div>
     *
     * @param context        <div class="en">intent context</div>
     *                       <div class="zh-CN">用于生成 Intent 的 Context</div>
     * @param url            <div class="en">video url</div>
     *                       <div class="zh-CN">视频链接</div>
     * @param firstUse3rdApp <div class="en">use 3rd apps first</div>
     *                       <div class="zh-CN">首先尝试使用第三方应用</div>
     */
    public static Intent getStartIntent(@NonNull Context context, @NonNull String url, boolean firstUse3rdApp) {
        Intent intent = new Intent(context, PlayerActivity.class);
        intent.putExtra(VIDEO_URL, url);
        intent.putExtra(USE_3RD_APP, firstUse3rdApp);
        return intent;
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
        return getStartIntent(context, url, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent().getBooleanExtra(USE_3RD_APP, false)) {
            Intent playIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getIntent().getStringExtra(VIDEO_URL)));
            playIntent.setDataAndType(Uri.parse(getIntent().getStringExtra(VIDEO_URL)), "video/*");
            if (IntentUtils.startIntent(playIntent)) {
                finish();
                return;
            }
        }

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_player);

        mVideoView = findViewById(R.id.video_view);
        assert mVideoView != null;
        mVideoView.play(new SimpleMediaSource(getIntent().getStringExtra(VIDEO_URL)));

        mVideoView.setOrientationListener(new ExoVideoPlaybackControlView.OrientationListener() {
            @Override
            public void onOrientationChange(@ExoVideoPlaybackControlView.SensorOrientationType int orientation) {
                if (orientation == SENSOR_PORTRAIT) {
                    mVideoView.setPortrait(true);
                } else if (orientation == SENSOR_LANDSCAPE) {
                    mVideoView.setPortrait(false);
                }
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mVideoView != null) {
            mVideoView.pause();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (mVideoView != null) {
            mVideoView.resume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mVideoView != null) {
            mVideoView.releaseSelfPlayer();
        }
    }


}
