package com.contact.service;

import com.contact.mapper.ContactMapper;
import com.contact.model.dto.ContactDTO;
import com.contact.model.entity.Contact;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import java.util.Objects;

@ApplicationScoped
public class ContactService {

    public ContactDTO getContactById(Long contactId) {
        Contact contact = (Contact) Contact.findByIdOptional(contactId)
                .orElseThrow(() -> new WebApplicationException(404));
        return ContactMapper.INSTANCE.contactToContactDTO(contact);
    }

    @Transactional
    public void createContact(Long userId) {
        new Contact(userId).persist();
    }

    public ContactDTO getCreatedContact(Long userId) {
        Contact contact = Contact.findContactInfoByUserIdOptional(userId)
                .orElseThrow(() -> new WebApplicationException(404));
        return ContactMapper.INSTANCE.contactToContactDTO(contact);
    }

    @Transactional
    public void updateContact(Long contactId, ContactDTO contactDTO) {
        Contact contact = (Contact) Contact.findByIdOptional(contactId)
                .orElseThrow(() -> new WebApplicationException(404));

        contact.email = Objects.isNull(contactDTO.getEmail()) ? contact.email : contactDTO.getEmail();
        contact.phone = Objects.isNull(contactDTO.getPhone()) ? contact.phone : contactDTO.getPhone();
        contact.telegram = Objects.isNull(contactDTO.getTelegram()) ? contact.telegram : contactDTO.getTelegram();
    }

    @Transactional
    public void deleteContact(Long contactId) {
        Contact contact = (Contact) Contact.findByIdOptional(contactId)
                .orElseThrow(() -> new WebApplicationException(404));
        contact.delete();
    }
}
