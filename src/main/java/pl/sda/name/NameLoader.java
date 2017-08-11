package pl.sda.name;

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

/**
 * Created by andrzej on 11.08.17.
 */
@Component
public class NameLoader {

	private ResourceLoader resourceLoader;
	private List<String> listOfStrings;

	@Autowired
	public NameLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	//every time constructor is used fire this annotation - Post Construct
	@PostConstruct
	public void loadFile() throws IOException {
		Resource resource = resourceLoader.getResource("classpath:imiona.txt");
		Scanner scanner = new Scanner(resource.getFile());
		listOfStrings = new ArrayList<>();

		while(scanner.hasNext()) {
			listOfStrings.add(scanner.nextLine());
		}
		scanner.close();
	}

	public List<Name> mapToNamesList() {
		AtomicLong atomicLong = new AtomicLong(0);
		return listOfStrings
				.stream()
				.map(s -> new Name(atomicLong.incrementAndGet(), s))
				.collect(Collectors.toList());
	}


}
