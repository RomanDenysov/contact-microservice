package com.contact.model.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(name = "contacts")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Contact extends PanacheEntityBase {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "phone")
    public String phone;

    @Column(name = "email")
    public String email;

    @Column(name = "telegram")
    public String telegram;

    @Column(name = "user_id", unique = true, nullable = false)
    @NonNull
    public Long userId;

    public static Optional<Contact> findContactInfoByUserIdOptional(Long userId) {
        EntityManager em = getEntityManager();
        Query query = em.createNativeQuery("SELECT * FROM contacts c WHERE c.user_id=:userId", Contact.class);
        query.setParameter("userId", userId);
        Contact contact = (Contact) query.getSingleResult();
        return Optional.ofNullable(contact);
    }
}
