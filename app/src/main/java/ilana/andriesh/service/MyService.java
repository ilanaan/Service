package ilana.andriesh.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.media.MediaParser;
import android.media.MediaPlayer;
import android.os.IBinder;

import java.util.ArrayList;

public class MyService extends Service {
    MediaPlayer player;
    public int counter;
    ArrayList<Integer> media;
    public MyService() {
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @SuppressLint("WrongConstant")
    public int onStartCommand(Intent intent,int flags,int startId)
    {
        this.counter=0;
        media=new ArrayList<Integer>();
        media.add(R.raw.rain);
        media.add(R.raw.sea);
        media.add(R.raw.happy);
        player = new MediaPlayer();
        player = MediaPlayer.create(getApplicationContext(),media.get(this.counter));
        this.counter++;
        player.start();
        player.setOnCompletionListener(this::OnComp);
        return 0;
    }
    public void OnComp(MediaPlayer md){
        if (this.counter>2) {
            counter =0;
        }
        player = MediaPlayer.create(getApplicationContext(),media.get(this.counter));
        player.start();
        counter++;
        player.setOnCompletionListener(this::OnComp);
    }
    public void onDestroy(){
        player.stop();
        player.release();
    }
}

