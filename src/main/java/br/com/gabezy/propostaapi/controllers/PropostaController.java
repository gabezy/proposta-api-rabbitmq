package br.com.gabezy.propostaapi.controllers;

import br.com.gabezy.propostaapi.domain.dtos.PropostaRequestDTO;
import br.com.gabezy.propostaapi.domain.dtos.PropostaResponseDTO;
import br.com.gabezy.propostaapi.services.PropostaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/proposta")
@RequiredArgsConstructor
public class PropostaController {

    private final PropostaService propostaService;

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PropostaResponseDTO> save(@RequestBody PropostaRequestDTO request) {
        PropostaResponseDTO propostaResponse = propostaService.save(request);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(propostaResponse.id())
                .toUri();

        return ResponseEntity.created(uri).body(propostaResponse);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PropostaResponseDTO>> obterPropostas() {
        return ResponseEntity.ok(propostaService.obterPropostas());
    }


}
