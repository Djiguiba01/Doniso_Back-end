package com.doniso.Doniso.Service;

import com.doniso.Doniso.Models.Notification;
import com.doniso.Doniso.Repository.NotificationRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NotificationServiceImpl implements  NotificationService{

    @Autowired
    private  final NotificationRepo notificationRepo;


    @Override
    public Notification creer(Notification notification) {

        return notificationRepo.save(notification);
    }

    @Override
    public List<Notification> lire() {
        return notificationRepo.findAll();
    }


    //    //
    @Override
    public Notification modifier(Long idNotif, Notification notification) {
        return notificationRepo.findById(idNotif)
                .map(n-> {
                    n.setTitre(notification.getTitre());
                    n.setLieu(notification.getLieu());
                    n.setContact(notification.getContact());
                    return  notificationRepo.save(n);
                }).orElseThrow(() -> new RuntimeException("Notification non trouvée !!"));
    }

    @Override
    public String supprimer(Long idNotif) {
        notificationRepo.deleteById(idNotif);
        return "Notification supprimer !";
    }
}
