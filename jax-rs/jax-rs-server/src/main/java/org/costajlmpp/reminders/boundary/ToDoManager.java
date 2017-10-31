package org.costajlmpp.reminders.boundary;

import org.costajlmpp.reminders.entity.ToDo;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Singleton
public class ToDoManager {

    private Map<Long, ToDo> toDos;
    private AtomicLong sequence;

    @Inject
    @ChangeEvent(ChangeEvent.Type.CREATION)
    Event<ToDo> createdEvent;

    @Inject
    @ChangeEvent(ChangeEvent.Type.CREATION)
    Event<ToDo> updatedEvent;

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

            this.createdEvent.fireAsync(toDo);

            return toDo;
        } else {
            long newId = sequence.incrementAndGet();
            toDo.setId(newId);
            this.toDos.put(newId, toDo);

            this.updatedEvent.fireAsync(toDo);

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
