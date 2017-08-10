package com.pearson.projectone.bundle.resource;

import com.pearson.projectone.bundle.entity.Response;
import com.pearson.projectone.bundle.service.ItemEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("itementry")
public class BundleLocalStorageResource {

//	@Autowired
//	private ItemEntryService itemEntryService;
//
//	@GetMapping("{name}")
//	public ResponseEntity<?> getBundleDescription(@PathVariable("name") final String name) {
//		return ResponseEntity.ok(itemEntryService.readBundleDescription(name));
//	}
//
//	@GetMapping("/html/{name}")
//	public ResponseEntity<?> getItemEntryHTML(@PathVariable("name") final String name) {
//		return ResponseEntity.ok(itemEntryService.retrieveHTML(name));
//	}
//
//	@GetMapping("/js/{name}")
//	public ResponseEntity<?> getItemEntryJS(@PathVariable("name") final String name) {
//		return ResponseEntity.ok(itemEntryService.retrieveJS(name));
//	}
//
//	@GetMapping("/css/{name}")
//	public ResponseEntity<?> getItemEntryCSS(@PathVariable("name") final String name) {
//		return ResponseEntity.ok(itemEntryService.retrieveCSS(name));
//	}
//
//	@GetMapping("products")
//	public ResponseEntity<?> getProductCodes() {
//		return ResponseEntity.ok(itemEntryService.getAllProductCodes());
//	}
//
//	@GetMapping("options/{name}")
//	public ResponseEntity<?> getOptionsHTML(@PathVariable("name") final String name) {
//		return ResponseEntity.ok(itemEntryService.getReportsOptions(name));
//	}
//
//	@GetMapping("demographics/check/{name}")
//	public ResponseEntity<?> checkDemographics(@PathVariable("name") final String name) {
//		return ResponseEntity.ok(itemEntryService.checkDemographics(name));
//	}
//
//	@GetMapping("/lib/js/{name}")
//	public ResponseEntity<?> getDojoJSFileUrls(@PathVariable("name") final String jsFileName) {
//		String fileContents = itemEntryService.getDojoJSLibraryFile(jsFileName);
//		return (fileContents == null) ? ResponseEntity.notFound().build() :
//				ResponseEntity.ok(fileContents);
//	}
//
//	@GetMapping("/lib/css/{name}")
//	public ResponseEntity<?> getDojoCSSFileUrls(@PathVariable("name") final String cssFileName) {
//		String fileContents = itemEntryService.getDojoCSSLibraryFile(cssFileName);
//		return (fileContents == null) ? ResponseEntity.notFound().build() :
//				ResponseEntity.ok(fileContents);
//	}
}
