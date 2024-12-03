package technikal.task.fishmarket.mapper;

import org.mapstruct.*;
import technikal.task.fishmarket.entity.Fish;
import technikal.task.fishmarket.dto.FishDto;

import java.util.List;

@Mapper(imports = java.util.Date.class)
public interface FishMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "catchDate", expression = "java(new Date())")
    @Mapping(target = "imageFileNames", source = "fileNames")
    Fish toEntity(FishDto dto, List<String> fileNames);

}
