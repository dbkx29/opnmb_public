package icu.dbkx.opnmb.event;

import lombok.Data;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class UserLoginEvent extends ApplicationEvent{
    Integer user_id;
    public UserLoginEvent(Object source, Integer user_id) {
        super(source);
        this.user_id = user_id;
    }
}
