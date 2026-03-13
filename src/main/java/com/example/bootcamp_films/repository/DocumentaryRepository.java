package com.example.bootcamp_films.repository;

import com.example.bootcamp_films.entity.Documentary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentaryRepository extends JpaRepository<Documentary, Long> {}