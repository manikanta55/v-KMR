package com.virtusa.kmrsrv.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.virtusa.kmrsrv.model.CaptureModel;
import com.virtusa.kmrsrv.model.ProbSol;
import com.virtusa.kmrsrv.service.CaptureService;


@RestController
@RequestMapping(value = "/api")
public class KmrController {
	
	@Autowired
	private CaptureService capService;
	
	@PostMapping(value = "/upload")
	public void addKnowledge(@RequestBody CaptureModel capture) {
		
	}
	
	@PostMapping(value = "/addProject")
	public void addProject(@RequestBody CaptureModel capture) {
	capService.addProject(capture);
		
	}
	
	@GetMapping(value = "/getProject/{projectCode}")
	public CaptureModel getProject(@PathVariable String projectCode) {
	  return capService.getProject(projectCode);
	}
	
	@PostMapping(value = "/addKnowledge/{projectCode}",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public void addKnowledge(@RequestBody MultipartFile file,
			@RequestParam String problem ,@RequestParam String solution, @PathVariable String projectCode
			
			){
		ProbSol probSol = new ProbSol();
		 try {
				probSol.setFile(
				          new Binary(BsonBinarySubType.BINARY, file.getBytes()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		probSol.setProblem(problem);
		probSol.setSolution(solution);
		capService.addKnowledge(projectCode,probSol);
	}
	
	@GetMapping(value = "/getKnowledge/{searchKey}")
	public List<ProbSol> getKnowledge(@PathVariable String searchKey){
		return capService.getKnowledge(searchKey);
		
	}
	
	@GetMapping("/hello")
	@ResponseBody
	public byte[] sayHello() {
		return capService.getFile();
	}

}