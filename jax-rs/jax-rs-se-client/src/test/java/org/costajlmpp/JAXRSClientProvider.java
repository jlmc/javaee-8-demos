package org.costajlmpp;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class JAXRSClientProvider  implements TestRule {

    private Client client = ClientBuilder.newClient();
    private WebTarget target;

    private JAXRSClientProvider(String uri) {
        /*
        Configuration config = new ClientConfig().
                property("jersey.config.client.httpUrlConnection.setMethodWorkaround", true);

        client = ClientBuilder.newClient(config);
        */
        this.target = this.client.target(uri);
    }

    public static final JAXRSClientProvider buildWithURI(String uri) {
        return new JAXRSClientProvider(uri);
    }

    public Client client() {
        return this.client;
    }

    public WebTarget target() {
        return this.target;
    }

    @Override
    public Statement apply(Statement statement, Description description) {
        return new Statement() {
            public void evaluate() throws Throwable {
                statement.evaluate();
                JAXRSClientProvider.this.client.close();
            }
        };
    }
}
