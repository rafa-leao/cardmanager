package br.com.fiap.cardmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.cardmanager.model.User;

public interface UserRepository extends JpaRepository<User, Long> {}
