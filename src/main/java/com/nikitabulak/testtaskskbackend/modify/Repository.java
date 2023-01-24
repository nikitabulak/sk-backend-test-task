package com.nikitabulak.testtaskskbackend.modify;

import com.nikitabulak.testtaskskbackend.modify.model.DefaultModel;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface Repository extends JpaRepository<DefaultModel, Integer> {
}
