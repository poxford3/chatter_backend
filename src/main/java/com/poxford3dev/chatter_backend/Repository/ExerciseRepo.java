package com.poxford3dev.chatter_backend.Repository;

import com.poxford3dev.chatter_backend.Entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepo extends JpaRepository<Exercise, Integer> {
}
