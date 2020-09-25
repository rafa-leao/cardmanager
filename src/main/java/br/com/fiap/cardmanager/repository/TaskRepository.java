package br.com.fiap.cardmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.cardmanager.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {}
