package com.doniso.Doniso;

import com.doniso.Doniso.Models.ERole;
import com.doniso.Doniso.Models.Role;
import com.doniso.Doniso.Models.Sexe;
import com.doniso.Doniso.Models.Utilisateurs;
import com.doniso.Doniso.Repository.RoleRepository;
import com.doniso.Doniso.Repository.UtilisateursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;


@SpringBootApplication
public class DonisoApplication  implements CommandLineRunner{

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	UtilisateursRepository utilisateursRepository;
	@Autowired
	PasswordEncoder encoder;


	public static void main(String[] args) {
		SpringApplication.run(DonisoApplication.class, args);
	}


	public void run(String... args) throws Exception {

		//VERIFICATION DE L'EXISTANCE DU ROLE ADMIN AVANT SA CREATION
		if (roleRepository.findAll().size() == 0){
			roleRepository.save(new Role(ERole.ROLE_ADMIN));
			roleRepository.save(new Role(ERole.ROLE_FORMATEUR));
			roleRepository.save(new Role(ERole.ROLE_USER));
		}
		if (utilisateursRepository.findAll().size() == 0){
			Set<Role> roles = new HashSet<>();
			Role role = roleRepository.findByName(ERole.ROLE_ADMIN);
			roles.add(role);
			Utilisateurs utilisateurs = new Utilisateurs("djiguiba","effet", "Développeur", "73100973",  "photo.png" , Sexe.HOMME, encoder.encode( "123dji"),"djiguibabarema@gmail.com");
			utilisateurs.setRoles(roles);
			utilisateursRepository.save(utilisateurs);
		}
	}



}
