package technikal.task.fishmarket.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fish")
public class Fish {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private BigDecimal price;
	@Column(name = "catch_date")
	private Date catchDate;

	@ElementCollection
	@Column(name = "file_name")
	@CollectionTable(name = "fish_file_name", joinColumns = @JoinColumn(name = "fish_id"))
	private List<String> imageFileNames;

}
