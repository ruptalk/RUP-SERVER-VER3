package com.rup.rup_backend.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="NOTICE")
@NoArgsConstructor
@Getter
@Data
@ToString
public class Notice {
    @Id
    private int id;
    private String date;
    private String notice;
}
