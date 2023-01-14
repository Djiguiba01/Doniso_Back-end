package com.doniso.Doniso.Repository;

import com.doniso.Doniso.Models.Notification;
import com.doniso.Doniso.Models.Utilisateurs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepo extends JpaRepository<Notification, Long> {

    List<Notification> findByUtilisateurs(Utilisateurs utilisateurs);
}
