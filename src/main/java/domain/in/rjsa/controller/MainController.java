package domain.in.rjsa.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
	static final Logger logger = LoggerFactory.getLogger(MainController.class);
	// This will get the all the UI required pages
	@GetMapping(value = "/Access_Denied")
	public ResponseEntity<?>  accessDenied(@PathVariable String page,ModelMap model) {
		//Add userName in the home page
		logger.info("Get page " + page);
		return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
	}

	
	
}
