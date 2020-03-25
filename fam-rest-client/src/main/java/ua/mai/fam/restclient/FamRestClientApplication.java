package ua.mai.fam.restclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.mai.fam.model.person.Person;

public class FamRestClientApplication {

    @Autowired
    FamClient famClient;

    public FamClient getFamClient() {
        return famClient;
    }

    public void autowire() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(RestClientConfiguration.class);
        ctx.getAutowireCapableBeanFactory().autowireBean(this);
    }

    public static void main(String[] args) {
        FamRestClientApplication app = new FamRestClientApplication();
        app.autowire();
        Person person = app.getFamClient().find(50_000L);
    }

}
