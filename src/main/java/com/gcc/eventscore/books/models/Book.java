package com.gcc.eventscore.books.models;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
public class Book implements Serializable {
    private Long id;
    private String name;
}
