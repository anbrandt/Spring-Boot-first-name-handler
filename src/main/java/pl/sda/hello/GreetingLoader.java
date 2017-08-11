package pl.sda.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Component
public class GreetingLoader {

    private ResourceLoader resourceLoader;
    private List<String> stringList;

    //resource loader to domyślny interfejs Springa, który ładuje np. plik (jak w tym przypadku)
    //lub zdjęcie
    @Autowired
    public GreetingLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @PostConstruct
    public void loadFile() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:file.txt");
        Scanner scanner = new Scanner(resource.getFile());
        stringList = new ArrayList<>();
        while (scanner.hasNext()) {
            stringList.add(scanner.nextLine());
        }
        scanner.close();
    }

    public List<Greeting> loadFromFile() {
        AtomicLong atomicLong = new AtomicLong(0);
        //mapowanie jednego typu obiektów na na inne obiekty - mapowanie stringów na obiekty typu Greetings
        return stringList
                .stream()
                .map(s -> new Greeting(atomicLong.getAndIncrement(), s))
                .collect(Collectors.toList());
    }
}
