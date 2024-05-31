package com.br.jg.PortfolioFaculdade.Repositories;

import com.br.jg.PortfolioFaculdade.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}