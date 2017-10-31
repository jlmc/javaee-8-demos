package org.costajlmpp.reminders.boundary;

import org.costajlmpp.reminders.entity.ToDo;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Singleton
public class ToDoManager {

    private Map<Long, ToDo> toDos;
    private AtomicLong sequence;

    @PostConstruct
    void startUp() {
        this.toDos = new HashMap<>();
        this.sequence = new AtomicLong(0);
    }

    public ToDo findById(long id) {
        return this.toDos.get(id);
    }

    public void delete(long id) {
        this.toDos.remove(id);
    }

    public ToDo save(ToDo toDo) {
        if (toDo.getId() > 0) {
            this.toDos.put(toDo.getId(), toDo);
            return toDo;
        } else {
            long newId = sequence.incrementAndGet();
            toDo.setId(newId);
            this.toDos.put(newId, toDo);
            return toDo;
        }

    }

    public List<ToDo> search() {
        return Collections.unmodifiableList(this.toDos.values().
                stream().
                sorted(Comparator.comparingLong(ToDo::getId)).
                collect(Collectors.toList()));
    }
}
