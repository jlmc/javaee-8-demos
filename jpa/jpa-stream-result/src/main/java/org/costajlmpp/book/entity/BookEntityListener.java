package org.jcosta.book.entity;

import javax.inject.Inject;
import javax.persistence.PostLoad;
import java.util.logging.Logger;

public class BookEntityListener {

    // TODO:: CDI Is not working for EntityListener
    @Inject Logger logger;

    @PostLoad
    public void onLoad(final Book entity) {
        // logger.fine("CDI is Working fine for EntityListener");
    }
}
