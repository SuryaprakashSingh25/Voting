package com.voting.tutorialvoting.services;

import com.voting.tutorialvoting.model.OptionVote;
import com.voting.tutorialvoting.model.Poll;
import com.voting.tutorialvoting.repositories.PollRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PollService {
    private final PollRepository pollRepository;

    public PollService(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    public Poll createPoll(Poll poll) {
        return pollRepository.save(poll);
    }

    public List<Poll> getAllPolls() {
        return pollRepository.findAll();
    }

    public Optional<Poll> getPollById(Long id) {
        return pollRepository.findById(id);
    }

    public void vote(Long pollId, int optionIndex) {
        Poll poll=pollRepository.findById(pollId)
                .orElseThrow(() -> new RuntimeException("Poll not found"));
        List<OptionVote> options=poll.getOptions();
        if(optionIndex<0 || optionIndex>=options.size()){
            throw new IllegalArgumentException("Invalid Option Index");
        }
        OptionVote selectedOption=options.get(optionIndex);
        selectedOption.setVotesCount(selectedOption.getVotesCount()+1);
        pollRepository.save(poll);
    }
}
