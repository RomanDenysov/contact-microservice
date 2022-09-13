package com.contact.model.dto;

import lombok.*;

@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactDTO {
    private Long id;

    private String phone;

    private String email;

    private String telegram;

    private Long userId;
}
