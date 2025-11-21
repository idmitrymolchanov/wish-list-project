package ru.newgor.wishlist.adapter.output.persistence.image;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.newgor.wishlist.adapter.output.persistence.image.entity.ImageEntity;
import ru.newgor.wishlist.adapter.output.persistence.image.repository.ImageRepository;
import ru.newgor.wishlist.domain.exception.ImageNotFoundException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import static org.apache.commons.io.FilenameUtils.getExtension;

@Service
@RequiredArgsConstructor
public class ImagePersistence {

    @Value("${image.data-path}")
    private String imagesPath;

    private final ImageRepository repository;

    @SneakyThrows
    @Transactional
    public void saveImage(MultipartFile file, UUID itemId) {
        var extension = getExtension(file.getOriginalFilename());
        var fileName = itemId + "." + extension;

        Path uploadPath = Paths.get(imagesPath);
        Files.createDirectories(uploadPath);

        Path filePath = uploadPath.resolve(fileName);
        file.transferTo(filePath.toFile());

        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setId(itemId);
        imageEntity.setName(fileName);
        imageEntity.setPath(filePath.toString());
        repository.save(imageEntity);
    }

    @SneakyThrows
    public Resource getImage(UUID id) {
        var item = repository.findById(id).orElseThrow();
        FileSystemResource resource = new FileSystemResource(item.getPath());

        if (!resource.exists()) {
            throw new ImageNotFoundException(id);
        }

        return resource;
    }
}
