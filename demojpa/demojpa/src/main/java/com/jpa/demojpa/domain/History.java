package com.jpa.demojpa.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class History {
    @Id
    @GeneratedValue
    private Date date;
    private String rgb;
    private String image;
}
