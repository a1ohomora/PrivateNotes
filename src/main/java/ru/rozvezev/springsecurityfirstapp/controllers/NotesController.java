package ru.rozvezev.springsecurityfirstapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.rozvezev.springsecurityfirstapp.models.Note;
import ru.rozvezev.springsecurityfirstapp.models.Person;
import ru.rozvezev.springsecurityfirstapp.security.PersonDetails;
import ru.rozvezev.springsecurityfirstapp.services.NotesService;

import javax.validation.Valid;
import java.security.Principal;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/notes")
public class NotesController {

    private final NotesService notesService;

    @Autowired
    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }

    @GetMapping()
    public String notes(Model model, Principal principal){
        String username = principal.getName();
        model.addAttribute("notes", notesService.getAllByOwnerUsername(username));
        return "/note/notes_view";
    }

    @GetMapping("/{id}")
    public String noteInfo(@PathVariable int id, Model model){
        model.addAttribute("note", notesService.getById(id));
        model.addAttribute("formatter", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return "/note/note_view";
    }

    @GetMapping("/new")
    public String newNotePage(@ModelAttribute("note") Note note){
        return "/note/new_note_view";
    }

    @PostMapping()
    public String createNote(@ModelAttribute("note") @Valid Note note, Authentication auth, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "/note/new_note_view";

        Person owner = ((PersonDetails)auth.getPrincipal()).getPerson();
        note.setOwner(owner);
        notesService.save(note);
        return "redirect:/notes";
    }

    @GetMapping("/{id}/edit")
    public String editPage(@PathVariable int id, Model model){
        model.addAttribute("note", notesService.getById(id));
        return "/note/edit_note_view";
    }

    @PatchMapping("/{id}")
    public String editNote(@PathVariable int id, @ModelAttribute("note") @Valid Note note, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "/note/edit_note_view";

        notesService.update(note, id);
        return "redirect:/notes/";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id){
        notesService.delete(id);
        return "redirect:/notes";
    }


}
