package com.mosk.testjwtmessenger.entity;



import lombok.Data;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import javax.persistence.*;


@Entity
@Table(name = "messages")
@Data
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_message;

    private String text_message;


    @CreatedBy
    @ManyToOne
    @JoinColumn(name = "login")
    private UserEntity login;

}
