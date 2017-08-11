package pl.sda.hello;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();


    //autowired annotation is used for dependency injections
    @Autowired
    private GreetingLoader greetingLoader;

    //request mapping - main annotation in Spring, used for mapping URL to method
    @RequestMapping("/greeting")

	//request param for parameteres in HTTP like www.wp.pl/somethin?name=Jan
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

    //different way to map on ourl
    @RequestMapping("/hello/{name}")
    public Greeting hello(@PathVariable("name") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @RequestMapping("/file")
    public List<Greeting> greetingFromFile() {
        return greetingLoader.loadFromFile();
    }
}
