package pl.sda.name;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrzej on 11.08.17.
 */


//logika aplikacji -
	//TODO - 1) zwróć wszysktie imiona
	//TODO - zwróć wszystkie żeńskie imiona

@Service
public class NameService {

	private NameLoader nameLoader;


	@Autowired
	public NameService(NameLoader nameLoader) {
		this.nameLoader = nameLoader;
	}

	public List<Name> getListofAllNames() {
		return nameLoader.mapToNamesList();

	}

	public List<Name> getListOfAllFemaleNames() {

		List<Name> names = nameLoader.mapToNamesList();
		List<Name> listOfAllFemaleNames = new ArrayList<>();


		for (Name name : names) {
			if(name.getName().endsWith("a")) {
				listOfAllFemaleNames.add(name);
			}
		}
		return listOfAllFemaleNames;
	}


	public List<Name> getListOfAllMen() {
		List<Name> names = nameLoader.mapToNamesList();
		List<Name> listMen = new ArrayList<>();

		for (Name name : names) {
			if(!name.getName().endsWith("a")) {
				listMen.add(name);
			}
		}
		return listMen;
	}

	public List<Name> getNamesWithNumberLetters(int number) {
		List<Name> names = nameLoader.mapToNamesList();
		List<Name> listName = new ArrayList<>();

		for (Name name: names) {
			if(name.getName().length() == number) {
				listName.add(name);
			}
		}
		return listName;
	}

	public List<Name> getNamesContains(String letter) {
		List<Name> names = nameLoader.mapToNamesList();
		List<Name> listName = new ArrayList<>();

		for (Name name: names) {
			if(name.getName().contains(letter)) {
				listName.add(name);
			}
		}
		return listName;
	}


}
