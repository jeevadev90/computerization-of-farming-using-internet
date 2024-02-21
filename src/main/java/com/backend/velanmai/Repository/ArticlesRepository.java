package com.backend.velanmai.Repository;

import com.backend.velanmai.DTO.ArticlesDto;
import com.backend.velanmai.Entity.Articles;
import com.backend.velanmai.Entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticlesRepository extends JpaRepository<Articles,Long> {

    List<Articles> findByCategories(Categories categories);
}
