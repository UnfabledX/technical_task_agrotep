package technikal.task.fishmarket.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import technikal.task.fishmarket.entity.Fish;
import technikal.task.fishmarket.dto.FishDto;
import technikal.task.fishmarket.service.FishService;
import technikal.task.fishmarket.service.ImageService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/fish")
public class FishController {

	private final FishService service;
	private final ImageService imageService;
	
	@GetMapping({"", "/"})
	public String showFishList(Model model) {
		List<Fish> fishlist = service.findFishes();
		model.addAttribute("fishlist", fishlist);
		return "index";
	}
	
	@GetMapping("/create")
	public String showCreatePage(Model model) {
		FishDto fishDto = new FishDto();
		model.addAttribute("fishDto", fishDto);
		return "createFish";
	}
	
	@GetMapping("/delete")
	public String deleteFish(@RequestParam Long id) {
		service.deleteFishById(id);
		return "redirect:/fish";
	}
	
	@PostMapping("/create")
	public String addFish(@Valid @ModelAttribute FishDto fishDto, BindingResult result) {
		List<MultipartFile> imageFiles = fishDto.getImageFiles();
		if(imageFiles.isEmpty() || imageFiles.get(0).isEmpty()) {
			result.addError(new FieldError("fishDto", "imageFiles", "Потрібне хоча б одне фото рибки"));
		}
		if(result.hasErrors()) {
			return "createFish";
		}
		service.saveFish(fishDto);
		return "redirect:/fish";
	}

	@ResponseBody
	@GetMapping("/images/{name}")
	public byte[] getImageById(@PathVariable("name") String fileName) {
		return imageService.getFileContent(fileName);
	}

}
