package com.voting.tutorialvoting.controllers;

import com.voting.tutorialvoting.model.Poll;
import com.voting.tutorialvoting.request.Vote;
import com.voting.tutorialvoting.services.PollService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/polls")
@CrossOrigin(origins = "http://localhost:4200/")
public class PollController {
    private final PollService pollService;

    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    @PostMapping
    public Poll createPoll(@RequestBody Poll poll){
        return pollService.createPoll(poll);
    }

    @GetMapping
    public List<Poll> getAllPolls(){
        return pollService.getAllPolls();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Poll> getPoll(@PathVariable Long id){
        return pollService.getPollById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/vote")
    public void vote(@RequestBody Vote vote){
        pollService.vote(vote.getPollId(), vote.getOptionIndex());
    }
}
