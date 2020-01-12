package domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class Customer implements Serialize {
	private Integer id;
	private String firstName;
	private String lastName;
}