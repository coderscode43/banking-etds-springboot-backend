package domain.in.rjsa.controller;

import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import domain.in.rjsa.model.Ajax;
import domain.in.rjsa.model.Model;


public interface ControllerInterface <K, E> {
	public ResponseEntity<?> listAll(@PathVariable Long clientId,HttpServletRequest request ,@PathVariable int pageNo, @PathVariable int resultPerPage);
	public ResponseEntity<?> search(@PathVariable String json,@PathVariable Long clientId);
	public ResponseEntity<?> createEntity(@RequestBody LinkedHashMap<String, Object> entity);
	public ResponseEntity<?> getDetailController(@PathVariable K id,@PathVariable Long clientId);
	public ResponseEntity<?> update(@RequestBody LinkedHashMap<String, Object>  entity,HttpServletResponse response, UriComponentsBuilder ucBuilder);
	public ResponseEntity<?> delete(@PathVariable K id,@PathVariable Long clientId);
	public ResponseEntity<?> ajax(@RequestBody Ajax ajax,@PathVariable Long clientId);

}
