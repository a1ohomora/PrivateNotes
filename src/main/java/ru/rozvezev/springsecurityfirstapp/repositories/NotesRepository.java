package ru.rozvezev.springsecurityfirstapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.rozvezev.springsecurityfirstapp.models.Note;

import java.util.List;

@Repository
public interface NotesRepository extends JpaRepository<Note, Integer> {

    @Query(value = "select n.* from note n inner join person p on n.person_id = p.id and p.username = :ownerUsername", nativeQuery = true)
    List<Note> findAllByOwnerUsername(@Param("ownerUsername") String ownerUsername);

    @Modifying
    @Query("update Note n set n.title = :title, n.body = :body, n.updatedAt=current_timestamp where n.id = :id")
    void saveTitleAndBody(@Param("id") int id, @Param("title") String title, @Param("body") String body);
}
