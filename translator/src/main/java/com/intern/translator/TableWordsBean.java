package com.intern.translator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Words")
public class TableWordsBean implements Serializable {

    @Id
    @Column(name="word_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer word_id;

    @Column(name="language")
    private String language;

    @Column(name="word")
    private String word;
}
