package com.voting.tutorialvoting.repositories;

import com.voting.tutorialvoting.model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollRepository extends JpaRepository<Poll,Long> {
}
