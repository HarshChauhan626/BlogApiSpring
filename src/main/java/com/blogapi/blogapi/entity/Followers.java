package com.blogapi.blogapi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "followers")
public class Followers {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name="source_id")
    private User source;

    @ManyToOne
    @JoinColumn(name="target_id")
    private User target;

}
