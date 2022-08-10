package pl.mateuszkolodziejczyk.simplecrm.company.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mateuszkolodziejczyk.simplecrm.company.api.request.CompanyRequest;
import pl.mateuszkolodziejczyk.simplecrm.company.api.response.CompanyResponse;
import pl.mateuszkolodziejczyk.simplecrm.company.service.CompanyService;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyApi {

    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<CompanyResponse> saveCompany(@RequestBody CompanyRequest companyRequest) {
        CompanyResponse companyResponse = companyService.saveCompany(companyRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(companyResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> findCompany(@PathVariable Long id) {
        CompanyResponse companyResponse = companyService.findCompanyById(id);
        return ResponseEntity.status(HttpStatus.OK).body(companyResponse);
    }

    @GetMapping
    public ResponseEntity<List<CompanyResponse>> findAll() {
        List<CompanyResponse> allCompanies = companyService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(allCompanies);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyResponse> updateCompany(
            @PathVariable Long id, @RequestBody CompanyRequest companyRequest) {
        CompanyResponse companyResponse = companyService.updateCompany(id, companyRequest);
        return ResponseEntity.status(HttpStatus.OK).body(companyResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}