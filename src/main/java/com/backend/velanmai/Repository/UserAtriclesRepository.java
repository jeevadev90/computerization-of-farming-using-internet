package com.backend.velanmai.Repository;

import com.backend.velanmai.Entity.User;
import com.backend.velanmai.Entity.UserArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAtriclesRepository extends JpaRepository<UserArticle,Long> {
    List<UserArticle> findByUser(User user);

    UserArticle findUserById(Long id);
}
