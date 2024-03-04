package com.animal.ServiceImpl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.animal.Entity.Image;
import com.animal.Repository.ImageRepository;
import com.animal.Service.ImageService;

@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	private ImageRepository imagerepo;

	@Override
	public Image saveImage(String imageno, String imagename, MultipartFile file) {
		try {
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			Image image = new Image();
			image.setImageno(imageno);
			image.setImagename(imagename);
			image.setImage(file.getBytes());
			return imagerepo.save(image);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Image> getAllImages() {
		// TODO Auto-generated method stub
		return imagerepo.findAll();
	}

	@Override
	public String deleteImage(Long id) {
		// TODO Auto-generated method stub
		imagerepo.deleteById(id);
		return "Deleted";
	}

	@Override
	public Image getById(Long id) {
		// TODO Auto-generated method stub
		Image image = imagerepo.findById(id).get();
		return image;
	}

	@Override
	public Image updateImage(Long id, String imageno, String imagename, MultipartFile file) {
		Image existingImage = imagerepo.findById(id).orElse(null);

		if (existingImage != null) {
			try {
				existingImage.setImageno(imageno);
				existingImage.setImagename(imagename);

				if (file != null && !file.isEmpty()) {
					// If a new file is provided, update the image

					existingImage.setImage(file.getBytes());
				}

				return imagerepo.save(existingImage);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	@Override
	public boolean getimageno(Long imageno) {
		// TODO Auto-generated method stub

		return imagerepo.existsByImageno(imageno);

	}

	@Override
	public boolean getimagename(String imagename) {
		// TODO Auto-generated method stub
		return imagerepo.existsByImagename(imagename);
	}
}
