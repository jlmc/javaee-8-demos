package org.costajlmpp.book.boundary;

import org.costajlmpp.book.control.ReportGenerator;

import javax.ejb.Schedule;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@Startup
public class BookReporter {

    @Inject
    ReportGenerator reportGenerator;

    @Schedule(dayOfWeek = "*", hour = "1")
    public void generateReport() {
        this.reportGenerator.generate();
    }

}
