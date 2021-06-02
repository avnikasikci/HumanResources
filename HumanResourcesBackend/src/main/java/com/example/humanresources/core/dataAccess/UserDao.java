package com.example.humanresources.core.dataAccess;

import com.example.humanresources.core.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer>{
	User findByEmail(String email);
}
