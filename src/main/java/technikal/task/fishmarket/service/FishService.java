package technikal.task.fishmarket.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import technikal.task.fishmarket.mapper.FishMapper;
import technikal.task.fishmarket.entity.Fish;
import technikal.task.fishmarket.dto.FishDto;
import technikal.task.fishmarket.repository.FishRepository;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class FishService {

    private final FishMapper mapper;
    private final ImageService imageService;
    private final FishRepository repository;

    @Transactional(readOnly = true)
    public List<Fish> findFishes() {
        //in the real project the pagination is the must.
        return repository.findAllWithFileNames();
    }

    @Transactional
    public void deleteFishById(Long id) {
        repository.findById(id).ifPresent(fish -> {
            fish.getImageFileNames().forEach(imageService::deleteImage);
            repository.delete(fish);
        });
    }

    @Transactional
    public void saveFish(FishDto fishDto) {
        List<String> fileNames = new ArrayList<>();
        fishDto.getImageFiles().forEach(image -> {
            String storageFileName = imageService.saveImage(image);
            fileNames.add(storageFileName);
        });
        Fish fish = mapper.toEntity(fishDto, fileNames);
        repository.save(fish);
    }
}
