package domain;

import lombok.Data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Members {
    @Id
    @GeneratedValue
    private Date DATE;
    private String RGB;
    private String IMAGE;
}
