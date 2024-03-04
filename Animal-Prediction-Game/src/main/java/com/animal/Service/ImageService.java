package com.animal.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.animal.Entity.Image;

@Service
public interface ImageService {
	
	Image saveImage(String imageno ,String imagename, MultipartFile file);
	
	List<Image> getAllImages();
	
	String deleteImage(Long id);
	
	Image getById(Long id);
	
	Image updateImage(Long id, String imageno, String imagename, MultipartFile file);
	
	boolean getimageno(Long imageno);
	
	boolean getimagename(String imagename);

	
}
