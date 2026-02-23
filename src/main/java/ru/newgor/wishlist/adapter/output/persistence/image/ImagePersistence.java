package ru.newgor.wishlist.adapter.output.persistence.image;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Async;
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

    private static final Logger log = LoggerFactory.getLogger(ImagePersistence.class);
    @Value("${image.data-path}")
    private String imagesPath;

    private final ImageRepository repository;

    @SneakyThrows
    public Resource getImage(UUID itemId) {
        var item = repository.findByItemId(itemId).orElse(null);
        if (item == null) {
            return null;
        }
        FileSystemResource resource = new FileSystemResource(item.getPath());

        if (!resource.exists()) {
            throw new ImageNotFoundException(itemId);
        }

        return resource;
    }

    @SneakyThrows
    @Transactional
    public void saveImage(MultipartFile file, UUID itemId) {
        var id = UUID.randomUUID();
        var oldImage = repository.findByItemId(itemId).orElse(null);
        var extension = getExtension(file.getOriginalFilename());
        var fileName = id + "." + extension;

        String folder = itemId.toString().substring(0, 2);
        Path dir = Paths.get(imagesPath, folder);
        Files.createDirectories(dir);
        Path filePath = dir.resolve(id + "." + extension);
        file.transferTo(filePath.toFile());

        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setId(id);
        imageEntity.setItemId(itemId);
        imageEntity.setName(fileName);
        imageEntity.setPath(filePath.toString());
        repository.save(imageEntity);

        if (oldImage != null) {
            deleteImageAsync(oldImage.getPath(), oldImage.getId());
        }
    }

    public void deleteImage(UUID itemId) {
        repository.findByItemId(itemId).ifPresent(item -> deleteImageAsync(item.getPath(), item.getId()));
    }

    @Async
    public void deleteImageAsync(String path, UUID id) {
        try {
            Files.deleteIfExists(Paths.get(path));
            repository.deleteById(id);
            log.debug("Deleted file: {}", path);
        } catch (Exception e) {
            log.error("Failed to delete file: {}", path, e);
        }
    }
}
