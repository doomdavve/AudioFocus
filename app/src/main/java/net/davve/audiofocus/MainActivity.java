package net.davve.audiofocus;

import android.content.Context;
import android.media.AudioManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

    class AFChangeListener implements AudioManager.OnAudioFocusChangeListener {
        @Override
        public void onAudioFocusChange(int focusChange) {
            TextView txtView = (TextView)findViewById(R.id.textView);
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT){
                txtView.append("AUDIOFOCUS_LOSS_TRANSIENT\n");
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                txtView.append("AUDIOFOCUS_GAIN\n");
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                txtView.append("AUDIOFOCUS_LOSS\n");
            }
        }
    }

    public void requestAudioFocus(View view) {
        TextView txtView = (TextView)findViewById(R.id.textView);
        AudioManager am = (AudioManager)getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        int result = am.requestAudioFocus(new AFChangeListener(),
                // Use the music stream.
                AudioManager.STREAM_MUSIC,
                // Request permanent focus.
                AudioManager.AUDIOFOCUS_GAIN);

        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            txtView.append("AUDIOFOCUS_REQUEST_GRANTED\n");
        } else if (result == AudioManager.AUDIOFOCUS_REQUEST_FAILED){
            txtView.append("AUDIOFOCUS_REQUEST_FAILED\n");
        } else {
            txtView.append("Unknown response from AudioManager\n");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
