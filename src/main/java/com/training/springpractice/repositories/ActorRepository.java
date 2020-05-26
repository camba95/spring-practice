package com.training.springpractice.repositories;

import com.training.springpractice.models.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Long> {
    List<Actor> findByIdIn(List<Long> ids);
}
