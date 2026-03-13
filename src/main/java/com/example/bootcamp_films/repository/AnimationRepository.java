package com.example.bootcamp_films.repository;

import com.example.bootcamp_films.entity.Animation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimationRepository extends JpaRepository<Animation, Long> {
}