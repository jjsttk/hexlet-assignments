package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import exercise.model.Contact;
import exercise.repository.ContactRepository;
import exercise.dto.ContactDTO;
import exercise.dto.ContactCreateDTO;

@RestController
@RequestMapping("/contacts")
public class ContactsController {

    @Autowired
    private ContactRepository contactRepository;

    // BEGIN
    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDTO create(@RequestBody ContactCreateDTO contactCreateDTO) {
        return toDTO(contactRepository.save(toEntity(contactCreateDTO)));
    }

    private Contact toEntity (ContactCreateDTO contactCreateDTO) {
        var entity = new Contact();

        entity.setFirstName(contactCreateDTO.getFirstName());
        entity.setLastName(contactCreateDTO.getLastName());
        entity.setPhone(contactCreateDTO.getPhone());

        return entity;
    }

    private ContactDTO toDTO(Contact contact) {
        var dto = new ContactDTO();

        dto.setId(contact.getId());
        dto.setPhone(contact.getPhone());
        dto.setCreatedAt(contact.getCreatedAt());
        dto.setUpdatedAt(contact.getUpdatedAt());
        dto.setFirstName(contact.getFirstName());
        dto.setLastName(contact.getLastName());

        return dto;

    }
    // END
}
