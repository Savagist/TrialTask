package com.savage.kameleoon.controller;

import com.savage.kameleoon.dto.VoteDTO;
import com.savage.kameleoon.models.Vote;
import com.savage.kameleoon.services.VoteService;
import com.savage.kameleoon.util.client.ClientErrorResponse;
import com.savage.kameleoon.util.vote.VoteErrorResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/vote")
public class VoteController {

    private final VoteService voteService;
    private final ModelMapper modelMapper;

    @Autowired
    public VoteController(VoteService voteService, ModelMapper modelMapper) {
        this.voteService = voteService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/{id}")
    public VoteDTO getVote(@PathVariable("id") Long id) {
        return convertVoteDTO(voteService.findOne(id));
    }

    @PostMapping("/up/{id}")
    public ResponseEntity<HttpStatus> up(@PathVariable("id") Long id) {
        voteService.upVote(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/down/{id}")
    public ResponseEntity<HttpStatus> down(@PathVariable("id") Long id) {
        voteService.downVote(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<VoteErrorResponse> handleException() {
        VoteErrorResponse response = new VoteErrorResponse("Vote with this id wasn't found!");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    private Vote convertToVote(VoteDTO voteDTO) {
        return modelMapper.map(voteDTO, Vote.class);
    }

    private VoteDTO convertVoteDTO(Vote vote) {
        return modelMapper.map(vote, VoteDTO.class);
    }

}
