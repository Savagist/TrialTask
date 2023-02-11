package com.savage.kameleoon.services;

import com.savage.kameleoon.models.Quote;
import com.savage.kameleoon.models.Vote;
import com.savage.kameleoon.repository.QuoteRepository;
import com.savage.kameleoon.repository.VoteRepository;
import com.savage.kameleoon.util.qoute.QuoteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class QuoteService {

    private final QuoteRepository quoteRepository;
    private final VoteService voteService;

    @Autowired
    public QuoteService(QuoteRepository quoteRepository, VoteRepository voteRepository, VoteService voteService) {
        this.quoteRepository = quoteRepository;
        this.voteService = voteService;
    }

    public List<Quote> bestQuotes() {
        List<Vote> topVotes = voteService.bestVotes();
        List<Quote> topQuotes = new ArrayList<>();
        for (Vote vote : topVotes) {
            if (topQuotes.size() < 10) {
                topQuotes.add(quoteRepository.findQuoteByVotes(vote));
            }
            else {
                break;
            }
        }
        return topQuotes;
    }

    public List<Quote> worseQuotes() {
        List<Vote> worseVotes = voteService.worseVotes();
        List<Quote> worseQuotes = new ArrayList<>();
        for (Vote vote : worseVotes) {
            if (worseQuotes.size() < 10) {
                worseQuotes.add(quoteRepository.findQuoteByVotes(vote));
            }
            else {
                break;
            }
        }
        return worseQuotes;
    }

    public List<Quote> findAll() {
        return quoteRepository.findAll();
    }

    public Quote findOne(Long id) {
        Optional<Quote> foundQuote = quoteRepository.findById(id);
        return foundQuote.orElseThrow(QuoteNotFoundException::new);
    }

    public Quote findRandom() {
        return findOne(getRandomId());
    }

    @Transactional
    public void save(Quote quote) {
        initialUpdate(quote);
        quoteRepository.save(quote);
    }

    @Transactional
    public void delete(Long id) {
        quoteRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id, Quote newQuote) {
        Quote quote = findOne(id);
        quote.setContent(newQuote.getContent());
        quote.setUpdateTime(LocalDateTime.now());
        quoteRepository.save(quote);
    }

    private Long getRandomId() {
        long min = 1L;
        long max = this.findAll().size();
        return min + (long) (Math.random() * (max - min));
    }

    private void initialUpdate(Quote quote) {
        quote.setCreateTime(LocalDateTime.now());
        quote.setUpdateTime(LocalDateTime.now());
        quote.setVotes(new Vote());
    }


}
