package com.example.servico;

import com.example.Dao.VendaDAO;
import com.example.Dto.VendaDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServicoVenda {
    private List<VendaDTO> vendas;
    private final VendaDAO vendaDAO;

    public ServicoVenda(VendaDAO vendaDAO) {
        this.vendaDAO = vendaDAO;
        this.vendas = this.vendaDAO.getAlls();
    }
    
    @GetMapping("/servico/venda")
    public ResponseEntity<List<VendaDTO>> listar(){
        return ResponseEntity.ok(vendas);
    }
    
    @GetMapping ("/servico/venda/{id}")
    public ResponseEntity<VendaDTO> listarPorId(@PathVariable int id) {
        Optional<VendaDTO> vendaEncontrada = vendas.stream().filter(p -> p.getId() == id).findAny();
        return ResponseEntity.of(vendaEncontrada);
    }

    @PostMapping ("/servico/venda")
    public ResponseEntity<VendaDTO> criar (@RequestBody VendaDTO venda) {

        vendaDAO.insert(venda);
        
        return ResponseEntity.status(201).body(venda);
    }

    @DeleteMapping ("/servico/venda/{id}")
    public ResponseEntity excluir (@PathVariable long id) {
        
        if (vendas.stream().filter(v -> v.getId() == id).findAny().isPresent()){
            vendaDAO.delete(id);
            return ResponseEntity.notFound().build();
        }
        else
            return ResponseEntity.noContent().build();
    }
}
