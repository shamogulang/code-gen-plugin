package cn.oddworld;

import cn.oddworld.base.BaseHandler;
import com.intellij.notification.*;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.vfs.VirtualFile;

import java.io.IOException;
import java.util.List;

public class CodeGen extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {

        final VirtualFile file = CommonDataKeys.VIRTUAL_FILE.getData(e.getDataContext());
        try {
            VirtualFile parent = file.getParent().getParent().getParent().getParent().getParent();
            BaseHandler baseHandler = new BaseHandler();
            List<String> warnings = baseHandler.genCode(file.getInputStream(), parent.getCanonicalPath());
            for (String warning : warnings) {
                NotificationGroup notificationGroup = new NotificationGroup("error info", NotificationDisplayType.BALLOON, true);
                Notification notification = notificationGroup.createNotification(warning, NotificationType.WARNING);
                Notifications.Bus.notify(notification);
            }
        }catch (IOException exception){
            ;
        }
    }
}
