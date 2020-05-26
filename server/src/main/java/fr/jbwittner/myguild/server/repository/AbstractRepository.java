package fr.jbwittner.myguild.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Abstract part of repositories
 */
public interface AbstractRepository<E, K> extends JpaRepository<E, K> {
    
}