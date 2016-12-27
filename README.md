# MyService
Music player control in notification

In AndroidManifest

```javascript
<service
            android:name=".MyService"
            android:enabled="true"
            android:exported="true"></service>
```

```javascript
 @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getAction().equals(Constants.ACTION.STARTFOREGROUND_ACTION)) {
            showNotification();
            mediaPlayer.start();
            Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();
        } else if (intent.getAction().equals(Constants.ACTION.PREV_ACTION)) {
            Toast.makeText(this, "Clicked Previous", Toast.LENGTH_SHORT).show();
        } else if (intent.getAction().equals(Constants.ACTION.PLAY_ACTION)) {
            if(!isPause){
                isPause = true;
                mediaPlayer.pause();
            }else {
                isPause = false;
                mediaPlayer.start();
            }
            Toast.makeText(this, "Clicked Play", Toast.LENGTH_SHORT).show();
        } else if (intent.getAction().equals(Constants.ACTION.NEXT_ACTION)) {
            Toast.makeText(this, "Clicked Next", Toast.LENGTH_SHORT).show();
        } else if (intent.getAction().equals(
                Constants.ACTION.STOPFOREGROUND_ACTION)) {
            Toast.makeText(this, "Service Stopped", Toast.LENGTH_SHORT).show();
            stopForeground(true);
            stopSelf();
        }
        return START_STICKY;
    }
```

```javascript
@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void showNotification() {
// Using RemoteViews to bind custom layouts into Notification
        RemoteViews views = new RemoteViews(getPackageName(),
                R.layout.status_bar);
        RemoteViews bigViews = new RemoteViews(getPackageName(),
                R.layout.status_bar_expanded);

// showing default album image
        views.setViewVisibility(R.id.status_bar_icon, View.VISIBLE);
        views.setViewVisibility(R.id.status_bar_album_art, View.GONE);
        bigViews.setImageViewBitmap(R.id.status_bar_album_art,
                Constants.getDefaultAlbumArt(this));

        Intent notificationIntent = new Intent(this, MainActivity.class);
        notificationIntent.setAction(Constants.ACTION.MAIN_ACTION);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);

        Intent previousIntent = new Intent(this, MyService.class);
        previousIntent.setAction(Constants.ACTION.PREV_ACTION);
        PendingIntent ppreviousIntent = PendingIntent.getService(this, 0,
                previousIntent, 0);

        Intent playIntent = new Intent(this, MyService.class);
        playIntent.setAction(Constants.ACTION.PLAY_ACTION);
        PendingIntent pplayIntent = PendingIntent.getService(this, 0,
                playIntent, 0);

        Intent nextIntent = new Intent(this, MyService.class);
        nextIntent.setAction(Constants.ACTION.NEXT_ACTION);
        PendingIntent pnextIntent = PendingIntent.getService(this, 0,
                nextIntent, 0);

        Intent closeIntent = new Intent(this, MyService.class);
        closeIntent.setAction(Constants.ACTION.STOPFOREGROUND_ACTION);
        PendingIntent pcloseIntent = PendingIntent.getService(this, 0,
                closeIntent, 0);

        views.setOnClickPendingIntent(R.id.status_bar_play, pplayIntent);
        bigViews.setOnClickPendingIntent(R.id.status_bar_play, pplayIntent);

        views.setOnClickPendingIntent(R.id.status_bar_next, pnextIntent);
        bigViews.setOnClickPendingIntent(R.id.status_bar_next, pnextIntent);

        views.setOnClickPendingIntent(R.id.status_bar_prev, ppreviousIntent);
        bigViews.setOnClickPendingIntent(R.id.status_bar_prev, ppreviousIntent);

        views.setOnClickPendingIntent(R.id.status_bar_collapse, pcloseIntent);
        bigViews.setOnClickPendingIntent(R.id.status_bar_collapse, pcloseIntent);

//        views.setImageViewResource(R.id.status_bar_play,
//                R.drawable.next);
//        bigViews.setImageViewResource(R.id.status_bar_play,
//                R.drawable.next);

        views.setTextViewText(R.id.status_bar_track_name, "Song Title");
        bigViews.setTextViewText(R.id.status_bar_track_name, "Song Title");

        views.setTextViewText(R.id.status_bar_artist_name, "Artist Name");
        bigViews.setTextViewText(R.id.status_bar_artist_name, "Artist Name");

        bigViews.setTextViewText(R.id.status_bar_album_name, "Album Name");

        status = new Notification.Builder(this).build();
        status.contentView = views;
        status.bigContentView = bigViews;
        status.flags = Notification.FLAG_ONGOING_EVENT;
        status.icon = R.drawable.doremon;
        status.contentIntent = pendingIntent;
        startForeground(Constants.NOTIFICATION_ID.FOREGROUND_SERVICE, status);
    }
```

![alt tag](https://github.com/DoHoangYen/MyService/blob/master/Screenshot_20161227-103458%5B1%5D.png)

![alt tag](https://github.com/DoHoangYen/MyService/blob/master/main.png)

![alt tag](https://github.com/DoHoangYen/MyService/blob/master/Screenshot_20161227-103508%5B1%5D.png)
