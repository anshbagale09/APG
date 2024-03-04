package com.animal.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.animal.Entity.Image;
import com.animal.Service.ImageService;

@Controller
public class ImageController {

	@Autowired
	private ImageService imageservice;
	
	
	

	public ImageController(ImageService imageservice) {
		super();
		this.imageservice = imageservice;
	}

	
	 @PostMapping("/insert")
	    public String uploadImage(@RequestParam("imageno") String imageno,
	    		                  @RequestParam("imagename") String imagename,
	                              @RequestPart("file") MultipartFile file) {
		 imageservice.saveImage(imageno,imagename, file);
		 
		
	        return "admin/add-images";
	    }
	 
	 @GetMapping("/getall")
	 @ResponseBody
		public ResponseEntity<List<Image>> getAll()
		{
			List<Image> st1=imageservice.getAllImages();
			
			return new ResponseEntity<>(st1,HttpStatus.OK);
		}
	 
	 @DeleteMapping("/delete/{id}")
		public ResponseEntity<String> deleteImages(@PathVariable("id") Long id) {
			imageservice.deleteImage(id);

			return new ResponseEntity<>("Deleted......!!!!", HttpStatus.OK);
		}
	 
	 @GetMapping("/getbyid/{id}")
	 @ResponseBody
		public ResponseEntity<Image> getImagesById(@PathVariable("id") Long id)
		{
		 Image img=imageservice.getById(id);
		 return new ResponseEntity<>(img,HttpStatus.OK);
			
		}
	 
	 
	 @PostMapping("/update")
	 public String updateImage(@RequestParam("id") Long id,
	                           @RequestParam("imageno") String imageno,
	                           @RequestParam("imagename") String imagename,
	                           @RequestPart("file") MultipartFile file) {
	     imageservice.updateImage(id, imageno, imagename, file);

	     
	     return "admin/add-images"; 
	 }
	 
	 @GetMapping("/exist/{imageno}")
	 @ResponseBody
	 public ResponseEntity<String> imagenoExist(@PathVariable("imageno") Long imageno) {
	     if (imageservice.getimageno(imageno)) {
	         return ResponseEntity.ok("Image no allready exist");
	     } else {
	         return ResponseEntity.ok("Image no valid");
	     }
	 }
	 @GetMapping("/existby/{imagename}")
	 @ResponseBody
	 public ResponseEntity<String> imagenameExist(@PathVariable("imagename") String imagename) {
	     if (imageservice.getimagename(imagename)) {
	         return ResponseEntity.ok("Image name allready exist");
	     } else {
	         return ResponseEntity.ok("Image name valid");
	     }
	 }
}

