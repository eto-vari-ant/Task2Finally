package gp.taskcrud.controller;

import gp.taskcrud.exception.DeveloperNotFoundException;
import gp.taskcrud.model.Developer;
import gp.taskcrud.service.DeveloperService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/developers")
@Tag(name = "Controller", description = "Controller for CRUD Operations")
public class MainController {

    private final DeveloperService developerService;

    public MainController(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @GetMapping
    @Operation(summary = "Show all the developers")
    public List<Developer> findAll(){
        return developerService.findAll();
    }

    @PostMapping
    @Operation(summary = "Create a new developer")
    public ResponseEntity<?> create(@RequestBody Developer developer) {
        return new ResponseEntity<>(developerService.saveOrUpdate(developer),HttpStatus.CREATED);
    }


    @Operation(summary = "Show the developer by id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Developer> read(@PathVariable(name = "id") @Parameter(description = "the id of the developer") Long id) {
        final Developer developer = developerService.findDeveloperById(id);
        return new ResponseEntity<>(developer, HttpStatus.ACCEPTED);
    }

    @PatchMapping
    @Operation(summary = "Update info for the developer")
    public ResponseEntity<?> update( @RequestBody Developer developer) {
        return new ResponseEntity<>(developerService.saveOrUpdate(developer),HttpStatus.OK);
    }

    @Operation(summary = "Delete the developer")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") @Parameter(description = "the id of the developer") Long id) {
        final boolean deleted = developerService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.RESET_CONTENT);
    }
}
