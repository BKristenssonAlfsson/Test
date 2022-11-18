package com.test.demo.message;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "message")
public class Message implements Serializable {

    private static final long serialVersionUUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "message")
    private String test;

    public Message() {
    }

    public Message(Long id, String message) {
        this.id = id;
        this.test = message;
    }

    public void setTest(String message) {
        this.test = message;
    }

    public String getTest() {
        return test;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", test='" + test + '\'' +
                '}';
    }
}
