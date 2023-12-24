package br.com.kyw.project_kyw.adapters.dtos.response;

import br.com.kyw.project_kyw.core.entities.Notification;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO implements Serializable {

    private UUID id;
    private String nickname;

    private String email;
    private String phone;
    @Setter(AccessLevel.NONE)
    private List<Notification> notification;
    public void addNotification(Notification notification) {
        this.notification.add(notification);
    }


}
