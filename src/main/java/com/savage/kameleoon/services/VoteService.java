package com.savage.kameleoon.services;

import com.savage.kameleoon.models.Vote;
import com.savage.kameleoon.repository.VoteRepository;
import com.savage.kameleoon.util.vote.VoteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class VoteService {

    private final VoteRepository voteRepository;

    @Autowired
    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public Vote findOne(Long id) {
        Optional<Vote> foundVote = voteRepository.findById(id);
        return foundVote.orElseThrow(VoteNotFoundException::new);
    }

    public void upVote(Long id) {
        Vote vote = findOne(id);
        vote.setVotes(vote.getVotes() + 1);
        voteRepository.save(vote);
    }

    public void downVote(Long id) {
        Vote vote = findOne(id);
        vote.setVotes(vote.getVotes() - 1);
        voteRepository.save(vote);
    }

    public List<Vote> bestVotes() {
        return voteRepository.findAll().stream()
                .sorted(Comparator.comparingLong(Vote::getVotes).reversed().thenComparing(Vote::getId))
                .toList();
    }

    public List<Vote> worseVotes(){
        return voteRepository.findAll().stream()
                .sorted(Comparator.comparingLong(Vote::getVotes).thenComparing(Vote::getId))
                .toList();
    }
}
