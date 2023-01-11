package com.doniso.Doniso.Controlleurs;

import com.doniso.Doniso.Models.Notification;
import com.doniso.Doniso.Service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notification")
@AllArgsConstructor
public class NotificationControl {
    @Autowired
    private final NotificationService notificationService;

    @PostMapping("/ajout")
    public Notification create(@RequestBody Notification notification) {
        return  notificationService.creer(notification);
    }

    @GetMapping("/voir")
    public List<Notification> read(){
        return notificationService.lire();
    }

    @PutMapping("/update/{idNotif}")
    public Notification update(@PathVariable Long idNotif, @RequestBody Notification notification) {
        return notificationService.modifier(idNotif, notification);
    }

    @DeleteMapping("supprimer/{idNotif}")
    public String delete(@PathVariable Long idNotif){
        return notificationService.supprimer(idNotif);
    }
}
