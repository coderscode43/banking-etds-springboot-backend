package domain.in.rjsa.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import domain.in.rjsa.model.form.Ajax;


public interface TDSControllerInterface <K, E> {
	public ResponseEntity<?> listAll(@PathVariable Long clientId,HttpServletRequest request ,@PathVariable int pageNo, @PathVariable int resultPerPage);
	public ResponseEntity<?> search(@PathVariable String json,@PathVariable Long clientId);
	public ResponseEntity<?> getDetailController(@PathVariable K id,@PathVariable Long clientId);
	public ResponseEntity<?> ajax(@RequestBody Ajax ajax,@PathVariable Long clientId);

}
