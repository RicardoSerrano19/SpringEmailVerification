package com.serrano.app.helpdesk.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.ColumnDefault;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailValidator{
    @SequenceGenerator(
        name = "email_sequence",
        sequenceName = "email_sequence",
        allocationSize = 1
    )
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "email_sequence"
    )
    private Long id;
    @ColumnDefault("false")
    private Date confirmed_at;
    private Date created_at;
    private Date expires_at;
    private String token;
    @ManyToOne
    @JoinColumn(name = "user_id",
        foreignKey = @ForeignKey(name = "user_id_fk"))
    private User user;
}