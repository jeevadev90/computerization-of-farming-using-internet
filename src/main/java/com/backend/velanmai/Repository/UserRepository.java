package com.backend.velanmai.Repository;

import com.backend.velanmai.Entity.Role;
import com.backend.velanmai.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    UserDetails findByEmail(String email);

    User findByName(String name);
    User findByEmailAndPassword(String email, String password);

    User findByPassword(String password);

    User findByRole(Role role);
}
