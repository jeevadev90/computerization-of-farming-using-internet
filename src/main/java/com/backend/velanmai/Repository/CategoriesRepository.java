package com.backend.velanmai.Repository;

import com.backend.velanmai.Entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories,Long> {

    Categories findByName(String name);

    Categories findCategoriesById(Long id);
}
