package com.mosk.testjwtmessenger.base.building.entity;


import lombok.Data;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;


@Entity
@Table(name = "messages")
@Data
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_message;

    @Column
    private String text_message;


    @CreatedBy
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity speaker;

}
