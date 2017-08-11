package pl.sda.name;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by andrzej on 11.08.17.
 */
public class Name {

	private long id;
	private String name;

	public Name() {
	}

	public Name(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
