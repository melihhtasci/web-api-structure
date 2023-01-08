package com.project.webapi.core.repository.jpa;

import com.project.webapi.core.data.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
