package com.savage.kameleoon.repository;

import com.savage.kameleoon.models.Quote;
import com.savage.kameleoon.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;


public interface QuoteRepository extends JpaRepository<Quote,Long> {
    public Quote findQuoteByVotes(Vote vote);
}
