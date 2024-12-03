package technikal.task.fishmarket.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotEmpty;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FishDto {

	@NotEmpty(message = "потрібна назва рибки")
	private String name;
	@DecimalMin(value = "0.0", inclusive = false)
	@NotNull(message = "Потрібно ввести ціну")
	private BigDecimal price;
	private List<MultipartFile> imageFiles;
}
