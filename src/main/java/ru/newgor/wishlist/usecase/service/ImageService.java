package ru.newgor.wishlist.usecase.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.newgor.wishlist.adapter.output.persistence.image.ImagePersistence;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImagePersistence imagePersistence;

    @SneakyThrows
    public void saveFile(MultipartFile file, UUID itemId) {
        imagePersistence.saveImage(file, itemId);
        log.info("file saved: {}, item-id: {}", file.getOriginalFilename(), itemId);
    }

    public Resource getImage(UUID id) {
        return imagePersistence.getImage(id);
    }

    public void deleteImage(UUID itemId) {
        imagePersistence.deleteImage(itemId);
    }
}
