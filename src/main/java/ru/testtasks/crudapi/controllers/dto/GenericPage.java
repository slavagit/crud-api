package ru.testtasks.crudapi.controllers.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@ToString
public class GenericPage<T> {

    private final List<T> data;
    private final long total;

    public GenericPage() {
        this(Collections.emptyList(), 0);
    }

    public GenericPage(List<T> data, long total) {
        this.data = new ArrayList<>(data);
        this.total = total;
    }
}
