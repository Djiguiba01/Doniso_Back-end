package com.doniso.Doniso.Service;

import com.doniso.Doniso.Models.Notification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotificationService {

    Notification creer(Notification notification);
    List<Notification> lire();
    Notification modifier(Long idNotif, Notification notification);
    String supprimer(Long idNotif);
}
