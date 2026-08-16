

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ngoconnect.ngo_service.entity.Ngo;
import com.ngoconnect.ngo_service.service.NgoService;

@RestController
@RequestMapping("/api/ngos")
public class NgoController {

    private final NgoService ngoService;

    public NgoController(NgoService ngoService) {
        this.ngoService = ngoService;
    }

    @PostMapping
    public ResponseEntity<Ngo> createNgo(
            @RequestBody Ngo ngo) {

        Ngo createdNgo = ngoService.createNgo(ngo);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdNgo);
    }

    @GetMapping
    public ResponseEntity<List<Ngo>> getAllNgos() {

        return ResponseEntity.ok(
                ngoService.getAllNgos()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ngo> getNgoById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ngoService.getNgoById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ngo> updateNgo(
            @PathVariable Long id,
            @RequestBody Ngo ngo) {

        return ResponseEntity.ok(
                ngoService.updateNgo(id, ngo)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNgo(
            @PathVariable Long id) {

        ngoService.deleteNgo(id);

        return ResponseEntity.ok(
                "NGO deleted successfully"
        );
    }
}