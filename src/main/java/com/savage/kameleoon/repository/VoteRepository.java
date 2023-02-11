package com.savage.kameleoon.repository;

import com.savage.kameleoon.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote,Long> {
}
