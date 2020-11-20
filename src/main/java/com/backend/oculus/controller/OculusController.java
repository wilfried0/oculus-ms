package com.backend.oculus.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.backend.oculus.models.Imei;
import com.backend.oculus.models.Oculus;
import com.backend.oculus.payload.MessageResponse;
import com.backend.oculus.repository.ImeiRepository;
import com.backend.oculus.repository.OculusRepository;
import com.backend.oculus.storage.StorageException;
import com.backend.oculus.storage.StorageService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/oculus")
public class OculusController {
	
	Oculus oculus = new Oculus();
	
	@Autowired
	OculusRepository oculusRepository;
	
	@Autowired
	ImeiRepository imeiRepository ;
	
	private final StorageService storageService;

	@Autowired
	public OculusController(StorageService storageService) {
		this.storageService = storageService;
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createOculus(@Validated @RequestBody Oculus oculus) {
		System.out.println("******************* oculus ********** "+oculus.toString());
		Oculus newoculus = new Oculus(
				oculus.getVille(), 
				oculus.getIncident(), 
				oculus.getDescription(), 
				oculus.getPhone(), 
				oculus.getLatitude(), 
				oculus.getLongitude(), 
				oculus.getCreated_at(), 
				oculus.getImage1(), 
				oculus.getImage2(),
				oculus.getMultiImei()
		);
		//Set<String> strImeis = oculus.getImeis();
		Set<Imei> imeis = oculus.getMultiImei();

		System.out.print("******************* Mes imeis ********** "+imeis.toString());
		newoculus.setMultiImei(imeis);
		newoculus = oculusRepository.save(newoculus);
		
		for (Imei imei : imeis) {
			imei.setOculus(newoculus);
			imeiRepository.save(imei);
		}
		
		System.out.println(imeis);
		return ResponseEntity.ok(new MessageResponse("Oculus registered successfully!"));
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Oculus>> getOculus(){
		return ResponseEntity.ok(oculusRepository.findAll());
	}
	
	@GetMapping("/imeis")
	public Iterable<Imei>getImeis() {
		return imeiRepository.findAll();
	}
	
	@GetMapping("/imei")
	public ResponseEntity<List<Imei>> getImeiByOculusId(@RequestParam(name = "id", required = false) Long id) {
		return ResponseEntity.ok(imeiRepository.findImeiByOculus(id));
	}
	
	/** FILE UPLOAD AND DOWNLOAD **/
	
	
	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}

	@PostMapping("/")
	public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

		String tmp = storageService.store(file);
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + file.getOriginalFilename() + "!");
		String path = ServletUriComponentsBuilder.fromCurrentContextPath().path("api/oculus/files/"+tmp).toUriString();
		
		return path;
	}

	@ExceptionHandler(StorageException.class)
	public ResponseEntity<?> handleStorageFileNotFound(StorageException exc) {
		return ResponseEntity.notFound().build();
	}

}