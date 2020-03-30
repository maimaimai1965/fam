package ua.mai.fam.restclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.mai.fam.model.person.Person;

import java.util.List;

public class FamRestClientApplication {

    @Autowired
    FamClient famClient;

    public FamClient getFamClient() {
        return famClient;
    }

    public void autowireAppBeans() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(RestClientConfiguration.class);
        ctx.getAutowireCapableBeanFactory().autowireBean(this);
    }

    public static void main(String[] args) {
        FamRestClientApplication app = new FamRestClientApplication();
        app.autowireAppBeans();

        Person person = app.getFamClient().findPerson(50_000L);
        System.out.println("findPerson(): " + person);

        List<Person> persons = app.getFamClient().findAllPersons();
        System.out.println("findAllPersons(): " + persons);
    }

}
