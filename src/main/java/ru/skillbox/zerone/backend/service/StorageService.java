package ru.skillbox.zerone.backend.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skillbox.zerone.backend.model.dto.response.CommonResponseDTO;
import ru.skillbox.zerone.backend.model.dto.response.StorageDTO;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.rmi.UnexpectedException;
import java.util.Map;


@RequiredArgsConstructor
@Service
@Slf4j
public class StorageService {
  private final Cloudinary cloudinary;
  private final static Map options = Map.of();

  public String uploadImage(MultipartFile file) {
    try {
      var uploadResult = cloudinary.uploader().upload(file.getBytes(), options);
      return (String) uploadResult.get("secure_url");
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }
    public CommonResponseDTO<StorageDTO> uploadFileUrl (MultipartFile file) {
      StorageDTO storageDTO = new StorageDTO();
      storageDTO.setUrl(uploadImage(file));
      return CommonResponseDTO.<StorageDTO>builder().data(storageDTO).build();
    }
  }