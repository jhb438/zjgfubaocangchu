package com.basic.zjgfbcc.common.JPush;

import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

import java.util.HashMap;
import java.util.Map;

/**
 * jpush推送相关类
 */
public class pushService {

    public static PushPayload buildPushObject_android_tag_alertWithTitle(String alias,String ALERT,String TITLE) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.tag(""+alias+""))
                .setNotification(Notification.android(ALERT, TITLE, null))
                .build();
    }

    public static PushPayload buildPushObject_all_alias_alert(String alias,String ALERT) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(""+alias+""))
                .setNotification(Notification.alert(ALERT))
                .build();
    }

    public static PushPayload buildPushObject_android_and_ios(String alias,String Alert,String rowGuid) {
        Map<String, String> extras = new HashMap<String, String>();
        extras.put("RowGuid", "wttx|"+rowGuid+"");
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.alias(""+alias+""))
                .setNotification(Notification.newBuilder()
                        .setAlert(Alert)
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setTitle("")
                                .addExtras(extras).build())
                        .addPlatformNotification(IosNotification.newBuilder()
                                .incrBadge(1)
                                .addExtra("RowGuid", ""+rowGuid+"").build())
                        .build())
                .build();
    }


}
