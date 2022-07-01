package ru.rozvezev.springsecurityfirstapp.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rozvezev.springsecurityfirstapp.models.Note;
import ru.rozvezev.springsecurityfirstapp.repositories.NotesRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class NotesService {

    private final NotesRepository notesRepository;

    public NotesService(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    public List<Note> getAll(){
        return notesRepository.findAll();
    }

    public Note getById(int id){
        return notesRepository.findById(id).orElse(null);
    }

    public List<Note> getAllByOwnerUsername(String ownerUsername){
        return notesRepository.findAllByOwnerUsername(ownerUsername);
    }

    @Transactional
    public void delete(int id) {
        notesRepository.deleteById(id);
    }

    @Transactional
    public void save(Note note) {
        note.setCreatedAt(LocalDateTime.now());
        note.setUpdatedAt(LocalDateTime.now());
        notesRepository.save(note);
    }

    @Transactional
    public void update(Note note, int id) {
        notesRepository.saveTitleAndBody(id, note.getTitle(), note.getBody());
    }
}
