package fr.jbwittner.myguild.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Abstract part of repositories
 */
public interface AbstractRepository<T, ID> extends JpaRepository<T, ID> {
    
}