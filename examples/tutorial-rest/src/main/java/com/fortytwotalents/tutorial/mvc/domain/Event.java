package com.fortytwotalents.tutorial.mvc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Patrick Baumgartner
 * @since 1.0
 */
@Entity
@Data
public class Event implements Serializable {

    private static final long serialVersionUID = 0L;

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date eventDate = new Date();

    private String name;

    private String description;

}
