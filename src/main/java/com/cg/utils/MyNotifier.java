package com.cg.utils;

import com.intellij.notification.NotificationGroupManager;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nullable;

/**
 * @ClassName MyNotifier
 * @Description 消息
 */
public class MyNotifier {
    /**
     * 错误
     * @param project
     * @param content
     */
    public static void notifyError(@Nullable Project project, String content) {
        NotificationGroupManager.getInstance().getNotificationGroup("message")
                .createNotification(content, NotificationType.ERROR)
                .notify(project);
    }

    /**
     * 信息
     * @param project
     * @param content
     */
    public static void notifyInformation(@Nullable Project project, String content) {
        NotificationGroupManager.getInstance().getNotificationGroup("message")
                .createNotification(content, NotificationType.INFORMATION)
                .notify(project);
    }
}
