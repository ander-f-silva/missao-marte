package br.com.mm.infraestrutura.repositorio;

import br.com.mm.dominio.Planalto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlanaltoRepositorio extends JpaRepository<UUID, Planalto> {
}
