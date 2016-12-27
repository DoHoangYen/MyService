package com.myservice.admin.myservice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * @author Yen Do
 * @owner Moca.vn
 * @since 12/19/2016
 */
public class Constants {
    public interface ACTION {
        String MAIN_ACTION = "com.myservice.admin.myservice.action.main";
        String INIT_ACTION = "com.myservice.admin.myservice.action.init";
        String PREV_ACTION = "com.myservice.admin.myservice.action.prev";
        String PLAY_ACTION = "com.myservice.admin.myservice.action.play";
        String NEXT_ACTION = "ccom.myservice.admin.myservice.action.next";
        String STARTFOREGROUND_ACTION = "com.myservice.admin.myservice.action.startforeground";
        String STOPFOREGROUND_ACTION = "com.myservice.admin.myservice.action.stopforeground";

    }

    public interface NOTIFICATION_ID {
        int FOREGROUND_SERVICE = 101;
    }

    public static Bitmap getDefaultAlbumArt(Context context) {
        Bitmap bm = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        try {
            bm = BitmapFactory.decodeResource(context.getResources(),
                    R.drawable.avt, options);
        } catch (Error ee) {
        } catch (Exception e) {
        }
        return bm;
    }
}