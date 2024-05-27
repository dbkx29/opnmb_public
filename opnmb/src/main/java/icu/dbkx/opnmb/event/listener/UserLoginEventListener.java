package icu.dbkx.opnmb.event.listener;

import icu.dbkx.opnmb.event.UserLoginEvent;
import org.springframework.context.event.EventListener;

public class UserLoginEventListener {
    @EventListener(classes = UserLoginEvent.class)
    public void onApplicationEvent(UserLoginEvent event) {

    }
}
