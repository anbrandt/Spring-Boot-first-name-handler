package pl.sda.name;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.hello.Greeting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by andrzej on 11.08.17.
 */

@RestController
public class NameController {


	private final AtomicLong counterId = new AtomicLong();
	private NameService nameService;

	@Autowired
	public NameController(NameService nameService) {
		this.nameService = nameService;
	}



	@RequestMapping("/names")
	public List<Name> allNames() throws IOException {
		return nameService.getListofAllNames();

	}

	@RequestMapping("names/female")
	public List<Name> allFemaleNames() throws IOException {

		return nameService.getListOfAllFemaleNames();
	}

	@RequestMapping("names/male")
	public List<Name> allMaleNames() throws IOException {

		return nameService.getListOfAllMen();
	}

	@RequestMapping("names/all")
	public List<Name> findTheNamesWithLetters(@RequestParam(value = "length") int number) {

		return nameService.getNamesWithNumberLetters(number);
	}

	@RequestMapping("names/cont")
	public List<Name> findTheNamesWithLetters(@RequestParam(value = "contains") String letter) {

		return nameService.getNamesContains(letter);
	}
}
