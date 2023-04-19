package com.nikitabulak.testtaskskbackend.modify;

import com.nikitabulak.testtaskskbackend.modify.model.DefaultModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface Repository extends JpaRepository<DefaultModel, Integer> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "2000")})
    Optional<DefaultModel> findDefaultModelById(Integer integer);
}
